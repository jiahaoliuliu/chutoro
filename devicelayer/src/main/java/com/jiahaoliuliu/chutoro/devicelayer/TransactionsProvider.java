package com.jiahaoliuliu.chutoro.devicelayer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.ISmsParametersFactory;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.Sms;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.SmsParserHelper;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.SmsParserParameters;
import com.jiahaoliuliu.chutoro.entity.Transaction;

import java.util.List;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import timber.log.Timber;

public class TransactionsProvider {

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_BODY = "body";

    private static final String[] PROJECTION = {
            COLUMN_ID,
            COLUMN_DATE,
            COLUMN_BODY
    };

    // Selection query
    private static final String SELECTION_CLAUSE = COLUMN_TYPE + "=? and " + COLUMN_ADDRESS + "=?";


    // Sort order
    private static final String SORT_ORDER = COLUMN_DATE + " DESC";

    /**
     * The context is needed to access to the content providers
     */
    private final Context context;
    private final SmsParserHelper smsParserHelper;
    private final String smsSender;
    private final List<SmsParserParameters> smsParserParametersList;

    public TransactionsProvider(Context context, SmsParserHelper smsParserHelper,
                                ISmsParametersFactory smsParametersFactory) {
        this.context = context;
        this.smsParserHelper = smsParserHelper;
        this.smsSender = smsParametersFactory.getSmsSender();
        this.smsParserParametersList = smsParametersFactory.createSmsParserParametersList();
    }

    public Observable<Transaction> provideTransactions() {
        return getSmsObservable()
                .filter(sms -> smsFilter(sms))
                .map(sms -> transactionMapper(sms));
    }

    private boolean smsFilter(Sms sms) {
        // TODO: Improve the filter so if there is any currency not accepted, it should
        // return false
        // If any patterns could be used, then let the sms pass
        for (SmsParserParameters smsParserParameters: smsParserParametersList) {
            Pattern pattern = Pattern.compile(smsParserParameters.getPattern());
            if (pattern.matcher(sms.getBody()).find()){
                return true;
            }
        }

        return false;
    }

    private Observable<Sms> getSmsObservable() {
        return Observable.create(emitter -> {
            // Selection arguments
            String[] Selection_args = {"1", smsSender};

            Cursor cursor = context.getContentResolver()
                    .query(Uri.parse("content://sms/inbox"), PROJECTION, SELECTION_CLAUSE,
                            Selection_args, SORT_ORDER);

            // Try to move the cursor to the first position
            if (!cursor.moveToFirst()) {
                Timber.v("The user does not have any sms");
                cursor.close();
                emitter.onComplete();
            }

            while(cursor.moveToNext()) {
                try {
                    Sms sms = new Sms(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BODY)),
                            cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                    emitter.onNext(sms);
                    // To catch any error on Getting the data from the cursor
                } catch (IllegalArgumentException illegalArgumentException) {
                    Timber.e(illegalArgumentException,"Error getting sms message from content resolver ");
                }
            }
            cursor.close();
            emitter.onComplete();
        });
    }

    private Transaction transactionMapper(Sms sms) {
        Timber.v("Checking the sms" + sms);
        return smsParserHelper.parseSmsToTransaction(sms, smsParserParametersList);
    }
}
