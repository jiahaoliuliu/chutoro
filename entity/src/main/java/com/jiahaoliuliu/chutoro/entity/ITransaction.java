package com.jiahaoliuliu.chutoro.entity;

import org.jetbrains.annotations.Nullable;

public interface ITransaction {

    /**
     * Get the sms id when it was parsed
     * @return
     */
    long getSmsId();

    /**
     * If the current transaction is from the result of parsing a sms
     * @return
     */
    boolean isFromSms();

    /**
     * Get the original sms which this data is based on. If this field is not null, the SMS id
     * must exist
     * @return
     *      The original SMS
     */
    @Nullable
    String getOriginalSms();

    /**
     * Get transactions quantity. Since float does not operate very well in Java, it is using
     * int with 2 decimals instead
     * @return The quantity used * 100
     */
    int getQuantity();

    /**
     * The currency of the quantity
     * @return
     */
    String getCurrency();

    /**
     * The bank where the money were originally from
     * @return The bank where the money come from
     */
    String getSource();

    /**
     * Where the money goes
     * @return
     */
    String getDestinationCodeName();

    /**
     * When the operation was made
     * @return
     */
    long getDate();

}
