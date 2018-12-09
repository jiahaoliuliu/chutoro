package com.jiahaoliuliu.chutoro.entity;

/**
 * The builder pattern for Transaction. This will allow more flexibility when the data is parsed
 * Note this class shouldn't be injected. In order to achieve that, all the methods need to
 * be tested thoughtly
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

    public TransactionBuilder() {
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
            float quantity = Float.parseFloat(quantityString.trim());
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

        this.quantity = quantity;
        return this;
    }

    public TransactionBuilder setCurrency(String currency) {
        checkTextNotEmptyOrThrowIllegalArgumentException(currency, "currency");

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
        checkTextNotEmptyOrThrowIllegalArgumentException(source, "source");

        for (Source sourceEnum: Source.values()) {
            if (sourceEnum.toString().equals(source)) {
                return setSource(sourceEnum);
            }
        }

        throw new IllegalArgumentException("The source cannot be recognized " + source);
    }

    public TransactionBuilder setSource(Source source) {
        this.source = source.toString();
        return this;
    }

    public TransactionBuilder setDestination(String destination) {
        checkTextNotEmptyOrThrowIllegalArgumentException(destination, "destination");
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
        checkTextNotEmptyOrThrowIllegalStateException(currency, "currency");

        // Source
        checkTextNotEmptyOrThrowIllegalStateException(source, "source");

        // Destination
        checkTextNotEmptyOrThrowIllegalStateException(destination, "destination");

        if (date == Transaction.DEFAULT_DATE) {
            throw new IllegalMonitorStateException("The date need to be set");
        }

        return new Transaction(smsId, quantity, currency, source, destination, date);
    }

    private boolean checkTextNotEmptyOrThrowIllegalArgumentException(String text, String fieldName) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("The " + fieldName + " cannot be empty");
        }

        return true;
    }

    private boolean checkTextNotEmptyOrThrowIllegalStateException(String text, String fieldName) {
        if (text == null || text.isEmpty()) {
            throw new IllegalStateException("The " + fieldName + " cannot be empty");
        }

        return true;
    }
}