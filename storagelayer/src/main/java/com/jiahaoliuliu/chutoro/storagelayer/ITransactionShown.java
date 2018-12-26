package com.jiahaoliuliu.chutoro.storagelayer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * TODO: This interface should be extended from {@link com.jiahaoliuliu.chutoro.entity.ITransaction}
 * and IDestination (to be created) and IDestinationGroup (to be created)
 */
public interface ITransactionShown {

    @NotNull
    int getQuantity();

    @NotNull
    String getCurrency();

    @NotNull
    String getSource();

    @NotNull
    String getDestination();

    @NotNull
    long getDate();

    @NotNull
    String getDestinationName();

    @Nullable
    long getDestinationLatitude();

    @Nullable
    long getDestinationLongitude();

    @Nullable
    String getDestinationDescription();

    @NotNull
    String getDestinationGroupName();

    @NotNull
    String getDestinationGroupCategory();

    boolean hasDestinationGroupCategory();

    @Nullable
    long getDestinationGroupLatitude();

    @Nullable
    long getDestinationGroupLongitude();

    @Nullable
    String getDestinationGroupDescription();
}
