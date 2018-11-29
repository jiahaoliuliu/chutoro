package com.jiahaoliuliu.chutoro.devicelayer.smsparser;

public class Sms {

    private long id;
    private String body;
    private long date;

    public Sms(long id, String body, long date) {
        this.id = id;
        this.body = body;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sms sms = (Sms) o;

        if (id != sms.id) return false;
        if (date != sms.date) return false;
        return body != null ? body.equals(sms.body) : sms.body == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
