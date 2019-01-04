package com.jiahaoliuliu.chutoro.entity;

import org.jetbrains.annotations.Nullable;

public class Transaction implements ITransaction {

    protected static final long DEFAULT_SMS_ID = -1;
    protected static final int DEFAULT_QUANTITY = -1;
    protected static final long DEFAULT_DATE = -1l;

    private final long smsId;
    private final String originalSms;
    private final int quantity;
    private final String currency;
    private final String source;
    private final String destination;
    private final long date;

    protected Transaction(long smsId, String originalSms, int quantity, String currency, String source,
                          String destination, long date) {
        this.smsId = smsId;
        this.originalSms = originalSms;
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
        return smsId != DEFAULT_SMS_ID;
    }

    @Nullable
    @Override
    public String getOriginalSms() {
        return originalSms;
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
        if (originalSms != null ? !originalSms.equals(that.originalSms) : that.originalSms != null)
            return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null)
            return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        return destination != null ? destination.equals(that.destination) : that.destination == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (smsId ^ (smsId >>> 32));
        result = 31 * result + (originalSms != null ? originalSms.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "smsId=" + smsId +
                ", originalSms='" + originalSms + '\'' +
                ", quantity=" + quantity +
                ", currency='" + currency + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                '}';
    }
}
