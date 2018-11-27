package com.jiahaoliuliu.chutoro.devicelayer;

/**
 * Basic class for sms
 */
public class Sms {

    private final String body;
    private final long date;

    public Sms(String body, long date) {
        this.body = body;
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public long getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sms sms = (Sms) o;

        if (date != sms.date) return false;
        return body != null ? body.equals(sms.body) : sms.body == null;
    }

    @Override
    public int hashCode() {
        int result = body != null ? body.hashCode() : 0;
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
