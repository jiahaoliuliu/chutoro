package com.jiahaoliuliu.chutoro.devicelayer.smsparser;

import android.support.annotation.NonNull;

import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.SmsParserParameters;
import com.jiahaoliuliu.chutoro.entity.Transaction;
import com.jiahaoliuliu.chutoro.entity.TransactionBuilder;

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
    // Based on ISO_4217, all the currency should have 3 letters
    private static final int SIZE_OF_CURRENCY_SYMBOL = 3;

    public SmsParserHelper() {}

    public List<Transaction> mapSmsListToTransactionsList(
            @NonNull List<Sms> smsList,
            @NonNull List<SmsParserParameters> smsParsingParametersList) {

        // Preconditions
        if (smsList == null) {
            return new ArrayList<>();
        }

        if (smsParsingParametersList == null) {
            return new ArrayList<>();
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
            return new ArrayList<>();
        }

        if (smsParsingParameters == null) {
            return new ArrayList<>();
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
        float quantity = parseQuantity(smsParserParameters, matcher);

        // Currency
        String currency = parseCurrency(smsParserParameters, matcher);

        // Destination
        String destination = matcher.group(smsParserParameters.getPositionDestination());

        // Date
        long date = parseDate(sms, smsParserParameters, simpleDateFormatter, matcher);

        return new TransactionBuilder()
                .setSmsId(sms.getId())
                .setSource(smsParserParameters.getSource())
                .setDestination(destination)
                .setQuantity(quantity)
                .setCurrency(currency)
                .setDate(date)
                .build();
    }

    private float parseQuantity(SmsParserParameters smsParserParameters, Matcher matcher) {
        String quantityAndCurrencyString = matcher.group(smsParserParameters.getPositionQuantity());
        try {
            return Float.valueOf(quantityAndCurrencyString.substring(SIZE_OF_CURRENCY_SYMBOL).trim());
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("Error formatting the quantity " + quantityAndCurrencyString);
        }
    }

    private String parseCurrency(SmsParserParameters smsParserParameters, Matcher matcher) {
        String quantityAndCurrencyString = matcher.group(smsParserParameters.getPositionQuantity());

        return quantityAndCurrencyString.substring(0, SIZE_OF_CURRENCY_SYMBOL);
    }

    private long parseDate(Sms sms, SmsParserParameters smsParserParameters,
                           SimpleDateFormat simpleDateFormatter, Matcher matcher) {
        long date = sms.getDate();
        // If the position is unknown, then use the one that comes from the sms
        if (smsParserParameters.getPositionDate() == -1) {
            return date;
        }

        try {
            date = simpleDateFormatter.parse(matcher.group(smsParserParameters.getPositionDate())).getTime();
        } catch (ParseException parseException) {
            System.out.println("Error parsing the date. Using the default one");
        } catch (IndexOutOfBoundsException indexOutOfBoundException) {
            System.out.println("The date is not specified correctly. Using the one that comes from the sms");
        }

        return date;
    }
}
