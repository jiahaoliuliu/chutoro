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

    @NotNull
    private String name;

    // The 3 letters ISO 4217 code
    //https://www.iso.org/iso-4217-currency-codes.html
    @NotNull
    private String isoCode;

    // Conversion regarding Dollar. It is used as base currency
    // This is the value por 1.000.000 dollars
    @NotNull
    private int conversionToDollar;

    public PersistentCurrency(@NotNull long id, @NotNull String name, @NotNull String isoCode, @NotNull int conversionToDollar) {
        this.id = id;
        this.name = name;
        this.isoCode = isoCode;
        this.conversionToDollar = conversionToDollar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public int getConversionToDollar() {
        return conversionToDollar;
    }

    public void setConversionToDollar(int conversionToDollar) {
        this.conversionToDollar = conversionToDollar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistentCurrency)) return false;

        PersistentCurrency that = (PersistentCurrency) o;

        if (id != that.id) return false;
        if (conversionToDollar != that.conversionToDollar) return false;
        if (!name.equals(that.name)) return false;
        return isoCode.equals(that.isoCode);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + isoCode.hashCode();
        result = 31 * result + conversionToDollar;
        return result;
    }

    @Override
    public String toString() {
        return "PersistentCurrency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isoCode='" + isoCode + '\'' +
                ", conversionToDollar=" + conversionToDollar +
                '}';
    }
}
