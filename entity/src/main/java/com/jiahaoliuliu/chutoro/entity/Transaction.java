package com.jiahaoliuliu.chutoro.entity;

public class Transaction implements ITransaction {

    private long smsId = -1;
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

    @Override
    public long getSmsId() {
        return smsId;
    }

    @Override
    public boolean isFromSms() {
        return smsId != -1;
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
