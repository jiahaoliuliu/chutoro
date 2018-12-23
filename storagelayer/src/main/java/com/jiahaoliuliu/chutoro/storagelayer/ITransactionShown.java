package com.jiahaoliuliu.chutoro.storagelayer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    long getDestinationGroupLatitude();

    @Nullable
    long getDestinationGroupLongitude();

    @Nullable
    String getDestinationGroupDescription();
}
