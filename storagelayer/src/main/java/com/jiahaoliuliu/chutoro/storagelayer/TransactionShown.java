package com.jiahaoliuliu.chutoro.storagelayer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Special transaction class create to be shown for the UI.
 * This class should be on the data layer, but for the follow
 * reasons this is not possible:
 * - If this is set on the presentation layer, it won't be visible
 *  by the storage layer
 * - If this is set on the domain, it will violates the clean code
 * because this is presentation specific representation of data
 * - If this is et on the repository layer, the changes on the database
 * won't be hardly reflected on the presentation layer (remember that
 * livedata requires a compulsory lifecycle owner)
 */
public class TransactionShown {

    // TODO: Encapsulate those fields if later it proves to work with the
    // room queries
    // Persistent
    public int quantity;
    public String currency;
    public String source;
    public String destination;
    public long date;

    // Destination
    public String destinationName;

    @Nullable
    public long destinationLatitude;

    @Nullable
    public long destinationLongitude;

    @Nullable
    public String destinationDescription;

    // Destination details
    @NotNull
    public String destinationDetailsName;

    @NotNull
    public String destinationDetailsCategory;

    @Nullable
    public long destinationDetailsLatitude;

    @Nullable
    public long destinationDetailsLongitude;

    @Nullable
    public String destinationDetailsDescription;
}
