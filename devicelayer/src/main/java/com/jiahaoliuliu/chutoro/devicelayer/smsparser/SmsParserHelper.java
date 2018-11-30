package com.jiahaoliuliu.chutoro.devicelayer.smsparser;


import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.entity.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use case created to map the list of sms to a list of any other data
 */
public class SmsParserHelper {
    public SmsParserHelper() {}

    public List<ITransaction> mapSmsListToTransactionsList(List<Sms> smsList,
                                                           SmsParserParameters smsParsingParameters) {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(smsParsingParameters.getDateFormat());
        List<ITransaction> transactionList = new ArrayList<>();
        for (Sms sms: smsList) {
            try {
                ITransaction transaction = parseSmsToTransaction(sms, smsParsingParameters, simpleDateFormatter);
                transactionList.add(transaction);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("Error mapping sms to transactions");
            }
        }
        return transactionList;
    }

    private ITransaction parseSmsToTransaction(Sms sms, SmsParserParameters smsParserParameters,
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
