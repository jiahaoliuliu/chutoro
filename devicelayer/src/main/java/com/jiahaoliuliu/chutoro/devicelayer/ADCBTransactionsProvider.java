package com.jiahaoliuliu.chutoro.devicelayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.entity.Sms;
import com.jiahaoliuliu.chutoro.entity.SmsParsingParameters;
import com.jiahaoliuliu.usecase.MapSmsUseCase;

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
    private static final String SOURCE = "ADCB";
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

    // Pattern parameters
    private static final String PATTERN_1 = "Your credit card (.*?) was used for AED(.*?) on (.*?) at (.*?)\\. ";
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final int POSITION_QUANTITY = 2;
    private static final int POSITION_DESTINATION = 4;
    private static final int POSITION_DATE = 3;

    /**
     * The context is needed to access to the content providers
     */
    private final Context context;
    private final MapSmsUseCase mapSmsUseCase;

    public ADCBTransactionsProvider(Context context, MapSmsUseCase mapSmsUseCase) {
        this.context = context;
        this.mapSmsUseCase = mapSmsUseCase;
    }

    @Override
    public Single<? extends List<? extends ITransaction>> provideTransactions() {
        Cursor cursor = context.getContentResolver()
                .query(Uri.parse("content://sms/inbox"), PROJECTION, SELECTION_CLAUSE,
                        SELECTION_ARGS, SORT_ORDER);

        List<? extends ITransaction> transactionsList = getDataFromCursor(cursor);
        // TODO: Implement this
        return Single.just(transactionsList);
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

        SmsParsingParameters smsParsingParameters = new SmsParsingParameters(PATTERN_1, DATE_FORMAT,
                POSITION_QUANTITY, POSITION_DESTINATION, POSITION_DATE, SOURCE);
        return mapSmsUseCase.mapSmsListToTransactionsList(smsList, smsParsingParameters);
    }
}
