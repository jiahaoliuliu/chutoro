package com.jiahaoliuliu.chutoro.entity;

public class Transaction implements ITransaction {

    private final int quantity;
    private final String source;
    private final String destination;
    private final long date;

    public Transaction(int quantity, String source, String destination, long date) {
        this.quantity = quantity;
        this.source = source;
        this.destination = destination;
        this.date = date;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (quantity != that.quantity) return false;
        if (date != that.date) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        return destination != null ? destination.equals(that.destination) : that.destination == null;
    }

    @Override
    public int hashCode() {
        int result = quantity;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "quantity=" + quantity +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                '}';
    }
}
