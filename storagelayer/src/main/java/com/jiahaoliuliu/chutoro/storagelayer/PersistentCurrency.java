package com.jiahaoliuliu.chutoro.storagelayer;

import org.jetbrains.annotations.NotNull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.jiahaoliuliu.chutoro.storagelayer.PersistentCurrency.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class PersistentCurrency {

    public static final String TABLE_NAME = "Currencies";

    @PrimaryKey
    @NotNull
    private long id;

    // The 3 letters ISO 4217 code
    //https://www.iso.org/iso-4217-currency-codes.html
    @NotNull
    private String isoCode;

    // Conversion regarding Dollar. It is used as base currency
    @NotNull
    private int conversionRegardingDollar;

    public PersistentCurrency(@NotNull long id, @NotNull String isoCode, @NotNull int conversionRegardingDollar) {
        this.id = id;
        this.isoCode = isoCode;
        this.conversionRegardingDollar = conversionRegardingDollar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public int getConversionRegardingDollar() {
        return conversionRegardingDollar;
    }

    public void setConversionRegardingDollar(int conversionRegardingDollar) {
        this.conversionRegardingDollar = conversionRegardingDollar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistentCurrency)) return false;

        PersistentCurrency that = (PersistentCurrency) o;

        if (id != that.id) return false;
        if (conversionRegardingDollar != that.conversionRegardingDollar) return false;
        return isoCode.equals(that.isoCode);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + isoCode.hashCode();
        result = 31 * result + conversionRegardingDollar;
        return result;
    }

    @Override
    public String toString() {
        return "PersistentCurrency{" +
                "id=" + id +
                ", isoCode='" + isoCode + '\'' +
                ", conversionRegardingDollar=" + conversionRegardingDollar +
                '}';
    }
}
