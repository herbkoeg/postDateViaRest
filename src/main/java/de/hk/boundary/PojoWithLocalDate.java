package de.hk.boundary;

import java.time.LocalDate;

public class PojoWithLocalDate {

    private String action;
    private LocalDate date;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PojoWithLocalDate{" +
                "action='" + action + '\'' +
                ", date=" + date +
                '}';
    }
}
