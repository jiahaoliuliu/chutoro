package com.jiahaoliuliu.chutoro.devicelayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.ISmsParametersFactory;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.Sms;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.SmsParserHelper;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.SmsParserParameters;
import com.jiahaoliuliu.chutoro.entity.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

public class TransactionsProvider {

    private static final String TAG = "AbsTransactionsProvider";

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
    private final ISmsParametersFactory smsParametersFactory;

    public TransactionsProvider(Context context, SmsParserHelper smsParserHelper,
                                ISmsParametersFactory smsParametersFactory) {
        this.context = context;
        this.smsParserHelper = smsParserHelper;
        this.smsParametersFactory = smsParametersFactory;
    }

    public Single<List<Transaction>> provideTransactions() {
        // Selection arguments
        String[] Selection_args = {"1", smsParametersFactory.getSmsSender()};

        Cursor cursor = context.getContentResolver()
                .query(Uri.parse("content://sms/inbox"), PROJECTION, SELECTION_CLAUSE,
                        Selection_args, SORT_ORDER);

        return Single.just(getDataFromCursor(cursor));
    }


    @SuppressLint("LongLogTag")
    private List<Transaction> getDataFromCursor(Cursor cursor) {
        // Try to move the cursor to the first position
        if (!cursor.moveToFirst()) {
            Log.v(TAG, "The user does not have any sms");
            cursor.close();
            return Collections.emptyList();
        }

        List<Sms> smsList = new ArrayList<>();
        while(cursor.moveToNext()) {
            try {
                Sms sms = new Sms(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BODY)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE)));

                smsList.add(sms);
                // To catch any error on Getting the data from the cursor
            } catch (IllegalArgumentException illegalArgumentException) {
                Log.w(TAG, "Error getting sms message from content resolver ", illegalArgumentException);
            }
        }
        cursor.close();

        List<SmsParserParameters> smsParserParametersList =
                smsParametersFactory.createSmsParserParametersList();
        return smsParserHelper.mapSmsListToTransactionsList(smsList, smsParserParametersList);
    }
}
