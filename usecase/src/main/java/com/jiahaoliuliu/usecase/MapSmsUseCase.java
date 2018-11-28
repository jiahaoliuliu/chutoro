package com.jiahaoliuliu.usecase;


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

    private static final String PATTERN_1 = "Your credit card (.*?) was used for AED(.*?) on (.*?) at (.*?)\\. ";
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private final SimpleDateFormat simpleDateFormatter;

    public MapSmsUseCase() {
        this.simpleDateFormatter = new SimpleDateFormat(DATE_FORMAT);
    }

    public List<Transaction> mapSmsListToTransactionsList(List<Sms> smsList, String source) {
        List<Transaction> transactionList = new ArrayList<>();
        for (Sms sms: smsList) {
            try {
                Transaction transaction = parseSmsToTransaction(sms, source);
                transactionList.add(transaction);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("Error mapping sms to transactions");
            }
        }
        return transactionList;
    }

    private Transaction parseSmsToTransaction(Sms sms, String source) {
        Pattern pattern = Pattern.compile(PATTERN_1);
        Matcher matcher = pattern.matcher(sms.getBody());
        // If the pattern is not correct
        if (!matcher.find()) {
            throw new IllegalArgumentException("Error parsing the sms. It cannot be recognized");
        }

        // Quantity
        String quantityString = matcher.group(2);
        int quantity;
        try {
            float quantityFloat = Float.valueOf(quantityString);
            // Get the quantity which is only with 2 zeros
            quantity = (int) (quantityFloat*100f);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("Error formating the quantity " + quantityString);
        }

        // Destination
        String destination = matcher.group(4);

        // Date
        long date;
        try {
            date = simpleDateFormatter.parse(matcher.group(3)).getTime();
        } catch (ParseException parseException) {
            throw new IllegalArgumentException("Error parsing the date");
        }

        return new Transaction(quantity, source, destination, date);
    }
}
