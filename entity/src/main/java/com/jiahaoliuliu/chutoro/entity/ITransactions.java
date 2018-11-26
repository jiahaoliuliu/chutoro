package com.jiahaoliuliu.chutoro.entity;

public interface ITransactions {

    /**
     * Get transactions quantity. Since float does not operate very well in Java, it is using
     * int with 2 decimals instead
     * @return The quantity used * 100
     */
    int getQuantity();

    /**
     * The bank where the money were originally from
     * @return The bank where the money come from
     */
    String getSource();

    String getDestination();

    long getDate();
}
