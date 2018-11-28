package com.jiahaoliuliu.chutoro.devicelayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.entity.Sms;

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
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_BODY = "body";

    private static final String[] PROJECTION = {
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

    /**
     * The context is needed to access to the content providers
     */
    private final Context context;

    public ADCBTransactionsProvider(Context context) {
        this.context = context;
    }

    @Override
    public Single<List<ITransaction>> provideTransactions() {
        Cursor cursor = context.getContentResolver()
                .query(Uri.parse("content://sms/inbox"), PROJECTION, SELECTION_CLAUSE,
                        SELECTION_ARGS, SORT_ORDER);

        List<ITransaction> transactionsList = getDataFromCursor(cursor);
        // TODO: Implement this
        return Single.just(transactionsList);
    }

    @SuppressLint("LongLogTag")
    private List<ITransaction> getDataFromCursor(Cursor cursor) {
        // Try to move the cursor to the first position
        if (!cursor.moveToFirst()) {
            Log.v(TAG, "The user does not have any sms");
            cursor.close();
            return Collections.emptyList();
        }

        List<Sms> smsList = new ArrayList<>();
        while(cursor.moveToNext()) {
            try {
                Sms sms = new Sms(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BODY)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE)));

                Log.v(TAG, "Sms read " + sms.toString());
                smsList.add(sms);
                // To catch any error on Getting the data from the cursor
            } catch (IllegalArgumentException illegalArgumentException) {
                Log.w(TAG, "Error getting sms message from content resolver ", illegalArgumentException);
            }
        }
        cursor.close();

//        do {
//            try {
//                Sms sms = new Sms(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BODY)),
//                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
//
//                Log.v(TAG, "Sms read " + sms.toString());
//                smsList.add(sms);
//                // To catch any error on Getting the data from the cursor
//            } catch (IllegalArgumentException illegalArgumentException) {
//                Log.w(TAG, "Error getting sms message from content resolver ", illegalArgumentException);
//            }
//        } while (cursor.moveToNext());
//        cursor.close();

        return Collections.emptyList();
    }
}
