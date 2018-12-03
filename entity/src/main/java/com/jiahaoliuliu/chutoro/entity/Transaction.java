package com.jiahaoliuliu.chutoro.entity;

public class Transaction implements ITransaction {

    private static final long DEFAULT_SMS_ID = -1;
    private static final int DEFAULT_QUANTITY = -1;
    private static final long DEFAULT_DATE = -1l;

    private final long smsId;
    private final int quantity;
    private final String currency;
    private final String source;
    private final String destination;
    private final long date;

    public Transaction(long smsId, int quantity, String currency, String source, String destination,
                       long date) {
        this.smsId = smsId;
        this.quantity = quantity;
        this.currency = currency;
        this.source = source;
        this.destination = destination;
        this.date = date;
    }

    public Transaction(int quantity, String currency, String source, String destination, long date) {
        this(DEFAULT_SMS_ID, quantity, currency, source, destination, date);
    }

    // TODO: Extract this to an separate class
    /**
     * The builder pattern for Transaction. This will allow more flexibility when the data is parsed
     */
    public class Builder {

        // Optional
        private long smsId = DEFAULT_SMS_ID;

        // Compulsory
        private int quantity = DEFAULT_QUANTITY;
        private String currency;
        private String source;
        private String destination;
        private long date = DEFAULT_DATE;

        public Builder() {
            this.source = source;
            this.destination = destination;
            this.date = date;
        }

        public Builder setSmsId(long smsId) {
            if (smsId < 0) {
                throw new IllegalArgumentException("The SMS id cannot be less than zero when" +
                        "building transaction");
            }

            this.smsId = smsId;
            return this;
        }

        public Builder setQuantity(String quantityString) {
            try {
                int quantity = Integer.valueOf(quantityString.trim());
                return setQuantity(quantity);
            } catch (NumberFormatException numberFormatException) {
                throw new IllegalArgumentException("The quantity " + quantityString + " to be set " +
                        "is not correct");
            }
        }

        public Builder setQuantity(float quantityFloat) {
            int quantity = (int) (quantityFloat * 100f);
            return setQuantity(quantity);
        }

        /**
         * Setting the quantity. Note that the quantity is set with two zeros, which makes the
         * quantity multiple per 100. i.e. 123.45 is saved as 12345
         * @param quantity
         * @return
         */
        public Builder setQuantity(int quantity) {
            if (quantity == -1) {
                throw new IllegalArgumentException("The quantity cannot be less than zero when " +
                    "building transaction");
            }

            this.smsId = smsId;
            return this;
        }

        public Builder setCurrency(int currencyPosition) {
            if (currencyPosition < 0 || currencyPosition > Currency.values().length) {
                throw new IllegalArgumentException("The position for the currency is wrong " + currency);
            }

            return setCurrency(Currency.values()[currencyPosition]);
        }

        // This is the base method for the currency
        public Builder setCurrency(Currency currency) {
            this.currency = currency.toString();
            return this;
        }

        public Builder setSource(String source) {
            checkTextNotEmptyOrThrowIllegalArgumentException(source);
            this.source = source;
            return this;
        }

        public Builder setDestination(String destination) {
            checkTextNotEmptyOrThrowIllegalArgumentException(source);
            this.destination = destination;
            return this;
        }

        public Builder setDate(long date) {
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
            if (quantity == DEFAULT_QUANTITY) {
                throw new IllegalStateException("The quantity need to be set");
            }

            // Currency
            checkTextNotEmptyOrThrowIllegalStateException(currency);

            // Source
            checkTextNotEmptyOrThrowIllegalStateException(source);

            // Destination
            checkTextNotEmptyOrThrowIllegalStateException(destination);

            if (date == DEFAULT_DATE) {
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

    @Override
    public long getSmsId() {
        return smsId;
    }

    @Override
    public boolean isFromSms() {
        return smsId != DEFAULT_SMS_ID;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public long getDate() {
        return date;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (smsId != that.smsId) return false;
        if (quantity != that.quantity) return false;
        if (date != that.date) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null)
            return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        return destination != null ? destination.equals(that.destination) : that.destination == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (smsId ^ (smsId >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "smsId=" + smsId +
                ", currency='" + currency + '\'' +
                ", quantity=" + quantity +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                '}';
    }
}
