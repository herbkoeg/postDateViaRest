package de.hk.boundary;

import java.time.LocalDate;

public class PojoWithLocalDate {

    private String action;
    private LocalDate someLocalDate;


    public PojoWithLocalDate(String action, LocalDate someLocalDate) {
        this.action = action;
        this.someLocalDate = someLocalDate;
    }

    public PojoWithLocalDate() {

    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDate getSomeLocalDate() {
        return someLocalDate;
    }

    public void setSomeLocalDate(LocalDate someLocalDate) {
        this.someLocalDate = someLocalDate;
    }

    @Override
    public String toString() {
        return "PojoWithLocalDate{" +
                "action='" + action + '\'' +
                ", date=" + someLocalDate +
                '}';
    }
}
