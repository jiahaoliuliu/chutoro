package com.jiahaoliuliu.chutoro.devicelayer.smsparser;


import android.support.annotation.NonNull;

import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.SmsParserParameters;
import com.jiahaoliuliu.chutoro.entity.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use case created to map the list of sms to a list of any other data
 */
public class SmsParserHelper {
    public SmsParserHelper() {}

    public List<Transaction> mapSmsListToTransactionsList(
            @NonNull List<Sms> smsList,
            @NonNull List<SmsParserParameters> smsParsingParametersList) {

        // Preconditions
        if (smsList == null) {
            return Collections.emptyList();
        }

        if (smsParsingParametersList == null) {
            return Collections.emptyList();
        }

        // FIXME: Optimize this
        List<Transaction> transactionsList = new ArrayList<>();

        for (SmsParserParameters smsParserParameters: smsParsingParametersList) {
            transactionsList.addAll(mapSmsListToTransactionsList(smsList, smsParserParameters));
        }
        return transactionsList;
    }

    private List<Transaction> mapSmsListToTransactionsList(
            @NonNull List<Sms> smsList,@NonNull SmsParserParameters smsParsingParameters) {
        // Preconditions
        if (smsList == null) {
            return Collections.emptyList();
        }

        if (smsParsingParameters == null) {
            return Collections.emptyList();
        }

        List<Transaction> transactionList = new ArrayList<>();
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(smsParsingParameters.getDateFormat());

        for (Sms sms: smsList) {
            try {
                Transaction transaction = parseSmsToTransaction(sms, smsParsingParameters, simpleDateFormatter);
                transactionList.add(transaction);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("Error mapping sms to transactions");
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Error mapping sms to transactions. The index requested is out of bounds");
            }
        }
        return transactionList;
    }

    private Transaction parseSmsToTransaction(Sms sms, SmsParserParameters smsParserParameters,
                                              SimpleDateFormat simpleDateFormatter) {
        Pattern pattern = Pattern.compile(smsParserParameters.getPattern());
        Matcher matcher = pattern.matcher(sms.getBody());
        // If the pattern is not correct
        if (!matcher.find()) {
            throw new IllegalArgumentException("Error parsing the sms. It cannot be recognized");
        }

        // Quantity
        String quantityString = matcher.group(smsParserParameters.getPositionQuantity());
        int quantity;
        try {
            float quantityFloat = Float.valueOf(quantityString);
            // Get the quantity which is only with 2 zeros
            quantity = (int) (quantityFloat*100f);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("Error formating the quantity " + quantityString);
        }

        // Destination
        String destination = matcher.group(smsParserParameters.getPositionDestination());

        // Date
        long date;
        try {
            date = simpleDateFormatter.parse(matcher.group(smsParserParameters.getPositionDate())).getTime();
        } catch (ParseException parseException) {
            throw new IllegalArgumentException("Error parsing the date");
        }

        return new Transaction(sms.getId(), quantity, smsParserParameters.getSource(), destination, date);
    }
}
