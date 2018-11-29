package com.jiahaoliuliu.chutoro.usecase.mapsmsusecase;


import com.jiahaoliuliu.chutoro.entity.Sms;
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
public class MapSmsUseCase {
    public MapSmsUseCase() {}

    public List<Transaction> mapSmsListToTransactionsList(List<Sms> smsList,
                                                          SmsMappingParameters smsMappingParameters) {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(smsMappingParameters.getDateFormat());
        List<Transaction> transactionList = new ArrayList<>();
        for (Sms sms: smsList) {
            try {
                Transaction transaction = parseSmsToTransaction(sms, smsMappingParameters, simpleDateFormatter);
                transactionList.add(transaction);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("Error mapping sms to transactions");
            }
        }
        return transactionList;
    }

    private Transaction parseSmsToTransaction(Sms sms, SmsMappingParameters smsMappingParameters,
                                              SimpleDateFormat simpleDateFormatter) {
        Pattern pattern = Pattern.compile(smsMappingParameters.getPattern());
        Matcher matcher = pattern.matcher(sms.getBody());
        // If the pattern is not correct
        if (!matcher.find()) {
            throw new IllegalArgumentException("Error parsing the sms. It cannot be recognized");
        }

        // Quantity
        String quantityString = matcher.group(smsMappingParameters.getPositionQuantity());
        int quantity;
        try {
            float quantityFloat = Float.valueOf(quantityString);
            // Get the quantity which is only with 2 zeros
            quantity = (int) (quantityFloat*100f);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("Error formating the quantity " + quantityString);
        }

        // Destination
        String destination = matcher.group(smsMappingParameters.getPositionDestination());

        // Date
        long date;
        try {
            date = simpleDateFormatter.parse(matcher.group(smsMappingParameters.getPositionDate())).getTime();
        } catch (ParseException parseException) {
            throw new IllegalArgumentException("Error parsing the date");
        }

        return new Transaction(quantity, smsMappingParameters.getSource(), destination, date);
    }
}
