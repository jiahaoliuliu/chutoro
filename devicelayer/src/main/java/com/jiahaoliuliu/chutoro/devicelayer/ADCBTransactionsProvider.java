package com.jiahaoliuliu.chutoro.devicelayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.jiahaoliuliu.chutoro.devicelayer.smsparser.Sms;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.SmsParserHelper;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.SmsParserParameters;
import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

/**
 * Specific transactions provider for ABCD Bank
 * (Abu Dhabi Commercial Bank)
 */
public class ADCBTransactionsProvider implements ITransactionsProvider{

    private static final String TAG = "ADCBTransactionsProvider";
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

    private static final String ADDRESS_ADCB = "ADCBAlert";

    // Selection arguments
    private static final String[] SELECTION_ARGS = {"1", ADDRESS_ADCB};

    // Sort order
    private static final String SORT_ORDER = COLUMN_DATE + " DESC";

    // Pattern parameters
    private static final String PATTERN_1 = "Your credit card (.*?) was used for AED(.*?) on (.*?) at (.*?)\\. ";
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final int POSITION_QUANTITY = 2;
    private static final int POSITION_DESTINATION = 4;
    private static final int POSITION_DATE = 3;
    private static final String SOURCE = "ADCB";

    /**
     * The context is needed to access to the content providers
     */
    private final Context context;
    private final SmsParserHelper smsParserHelper;

    public ADCBTransactionsProvider(Context context, SmsParserHelper smsParserHelper) {
        this.context = context;
        this.smsParserHelper = smsParserHelper;
    }

    @Override
    public Single<? extends List<? extends ITransaction>> provideTransactions() {
        Cursor cursor = context.getContentResolver()
                .query(Uri.parse("content://sms/inbox"), PROJECTION, SELECTION_CLAUSE,
                        SELECTION_ARGS, SORT_ORDER);

        return Single.just(getDataFromCursor(cursor));
    }

    @SuppressLint("LongLogTag")
    private List<? extends ITransaction> getDataFromCursor(Cursor cursor) {
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

        SmsParserParameters smsParserParameters = new SmsParserParameters(PATTERN_1, DATE_FORMAT,
                POSITION_QUANTITY, POSITION_DESTINATION, POSITION_DATE, SOURCE);
        return smsParserHelper.mapSmsListToTransactionsList(smsList, smsParserParameters);
    }
}
