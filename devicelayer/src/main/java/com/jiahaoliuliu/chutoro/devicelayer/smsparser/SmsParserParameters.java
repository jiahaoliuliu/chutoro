package com.jiahaoliuliu.chutoro.devicelayer.smsparser;

public class SmsParserParameters {

    private String pattern;
    private String dateFormat;
    private int positionQuantity;
    private int positionDestination;
    private int positionDate;
    private String source;

    public SmsParserParameters(String pattern, String dateFormat, int positionQuantity,
                               int positionDestination, int positionDate, String source) {
        this.pattern = pattern;
        this.dateFormat = dateFormat;
        this.positionQuantity = positionQuantity;
        this.positionDestination = positionDestination;
        this.positionDate = positionDate;
        this.source = source;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public int getPositionQuantity() {
        return positionQuantity;
    }

    public void setPositionQuantity(int positionQuantity) {
        this.positionQuantity = positionQuantity;
    }

    public int getPositionDestination() {
        return positionDestination;
    }

    public void setPositionDestination(int positionDestination) {
        this.positionDestination = positionDestination;
    }

    public int getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(int positionDate) {
        this.positionDate = positionDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmsParserParameters that = (SmsParserParameters) o;

        if (positionQuantity != that.positionQuantity) return false;
        if (positionDestination != that.positionDestination) return false;
        if (positionDate != that.positionDate) return false;
        if (pattern != null ? !pattern.equals(that.pattern) : that.pattern != null) return false;
        if (dateFormat != null ? !dateFormat.equals(that.dateFormat) : that.dateFormat != null)
            return false;
        return source != null ? source.equals(that.source) : that.source == null;
    }

    @Override
    public int hashCode() {
        int result = pattern != null ? pattern.hashCode() : 0;
        result = 31 * result + (dateFormat != null ? dateFormat.hashCode() : 0);
        result = 31 * result + positionQuantity;
        result = 31 * result + positionDestination;
        result = 31 * result + positionDate;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SmsParserParameters{" +
                "pattern='" + pattern + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", positionQuantity=" + positionQuantity +
                ", positionDestination=" + positionDestination +
                ", positionDate=" + positionDate +
                ", source='" + source + '\'' +
                '}';
    }
}
