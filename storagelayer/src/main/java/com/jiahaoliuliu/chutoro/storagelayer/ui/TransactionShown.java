package com.jiahaoliuliu.chutoro.storagelayer.ui;

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
public class TransactionShown implements ITransactionShown {

    // TODO: Encapsulate those fields if later it proves to work with the
    // room queries
    // Persistent
    public int quantity;
    public String currency;
    public String source;
    public String destinationCodeName;
    public long date;

    // Destination
    public String destinationName;

    @Nullable
    public long destinationLatitude;

    @Nullable
    public long destinationLongitude;

    @Nullable
    public String destinationDescription;

    // Destination group
    @NotNull
    public String destinationGroupName;

    @NotNull
    public String destinationGroupCategory;

    @Nullable
    public long destinationGroupLatitude;

    @Nullable
    public long destinationGroupLongitude;

    @Nullable
    public String destinationGroupDescription;

    @NotNull
    @Override
    public int getQuantity() {
        return quantity;
    }

    @NotNull
    @Override
    public String getCurrency() {
        return currency;
    }

    @NotNull
    @Override
    public String getSource() {
        return source;
    }

    @NotNull
    @Override
    public String getDestinationCodeName() {
        return destinationCodeName;
    }

    @NotNull
    @Override
    public long getDate() {
        return date;
    }

    @NotNull
    @Override
    public String getDestinationName() {
        return destinationName;
    }

    @Override
    public boolean hasDestinationName() {
        return destinationName != null && !destinationName.isEmpty();
    }

    @Nullable
    @Override
    public long getDestinationLatitude() {
        return destinationLatitude;
    }

    @Nullable
    @Override
    public long getDestinationLongitude() {
        return destinationLongitude;
    }

    @Nullable
    @Override
    public String getDestinationDescription() {
        return destinationDescription;
    }

    @NotNull
    @Override
    public String getDestinationGroupName() {
        return destinationGroupName;
    }

    @Override
    public boolean hasDestinationGroupName() {
        return destinationGroupName != null && !destinationGroupName.isEmpty();
    }

    @NotNull
    @Override
    public String getDestinationGroupCategory() {
        return destinationGroupCategory;
    }

    @Override
    public boolean hasDestinationGroupCategory() {
        return destinationGroupCategory != null && !destinationGroupCategory.isEmpty();
    }

    @Nullable
    @Override
    public long getDestinationGroupLatitude() {
        return destinationGroupLatitude;
    }

    @Nullable
    @Override
    public long getDestinationGroupLongitude() {
        return destinationGroupLongitude;
    }

    @Nullable
    @Override
    public String getDestinationGroupDescription() {
        return destinationGroupDescription;
    }
}
