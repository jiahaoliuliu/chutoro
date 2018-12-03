package com.jiahaoliuliu.chutoro.entity;

/**
 * The builder pattern for Transaction. This will allow more flexibility when the data is parsed
 */
public class TransactionBuilder {

    // Optional
    private long smsId = Transaction.DEFAULT_SMS_ID;

    // Compulsory
    private int quantity = Transaction.DEFAULT_QUANTITY;
    private String currency;
    private String source;
    private String destination;
    private long date = Transaction.DEFAULT_DATE;

    public TransactionBuilder(String source, String destination, long date) {
        setSource(source);
        setDestination(destination);
        setDate(date);
    }

    public TransactionBuilder setSmsId(long smsId) {
        if (smsId < 0) {
            throw new IllegalArgumentException("The SMS id cannot be less than zero when" +
                    "building transaction");
        }

        this.smsId = smsId;
        return this;
    }

    public TransactionBuilder setQuantity(String quantityString) {
        try {
            int quantity = Integer.valueOf(quantityString.trim());
            return setQuantity(quantity);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("The quantity " + quantityString + " to be set " +
                    "is not correct");
        }
    }

    public TransactionBuilder setQuantity(float quantityFloat) {
        int quantity = (int) (quantityFloat * 100f);
        return setQuantity(quantity);
    }

    /**
     * Setting the quantity. Note that the quantity is set with two zeros, which makes the
     * quantity multiple per 100. i.e. 123.45 is saved as 12345
     * @param quantity
     * @return
     */
    public TransactionBuilder setQuantity(int quantity) {
        if (quantity == -1) {
            throw new IllegalArgumentException("The quantity cannot be less than zero when " +
                    "building transaction");
        }

        this.smsId = smsId;
        return this;
    }

    public TransactionBuilder setCurrency(String currency) {
        checkTextNotEmptyOrThrowIllegalArgumentException(currency);

        for (Currency currencyEnum : Currency.values()) {
            if (currencyEnum.toString().equals(currency)) {
                return setCurrency(currencyEnum);
            }
        }

        throw new IllegalArgumentException("The currency to be set cannot be recognized " + currency);
    }

    // This is the base method for the currency
    public TransactionBuilder setCurrency(Currency currency) {
        this.currency = currency.toString();
        return this;
    }

    public TransactionBuilder setSource(String source) {
        checkTextNotEmptyOrThrowIllegalArgumentException(source);
        this.source = source;
        return this;
    }

    public TransactionBuilder setDestination(String destination) {
        checkTextNotEmptyOrThrowIllegalArgumentException(source);
        this.destination = destination;
        return this;
    }

    public TransactionBuilder setDate(long date) {
        if (date < 0) {
            throw new IllegalArgumentException("The date cannot be lower than 0 when building " +
                    "transaction");
        }
        this.date = date;
        return this;
    }

    public Transaction build() {
        // Check the fields. For compulsory fields, if it is not filled, throw an
        // IllegalStateException
        // Quantity
        if (quantity == Transaction.DEFAULT_QUANTITY) {
            throw new IllegalStateException("The quantity need to be set");
        }

        // Currency
        checkTextNotEmptyOrThrowIllegalStateException(currency);

        // Source
        checkTextNotEmptyOrThrowIllegalStateException(source);

        // Destination
        checkTextNotEmptyOrThrowIllegalStateException(destination);

        if (date == Transaction.DEFAULT_DATE) {
            throw new IllegalMonitorStateException("The date need to be set");
        }

        return new Transaction(smsId, quantity, currency, source, destination, date);
    }

    // TODO: Improve the error message
    private boolean checkTextNotEmptyOrThrowIllegalArgumentException(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("The text cannot be empty");
        }

        return true;
    }

    // TODO: Improve the error message
    private boolean checkTextNotEmptyOrThrowIllegalStateException(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalStateException("The text cannot be empty");
        }

        return true;
    }
}