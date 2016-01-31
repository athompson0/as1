package com.athompson0.athompso_fueltrack;

import java.util.Date;

/**
 * Created by athompso on 1/29/16.
 */
public class IncompleteEntry {
    private Date date = null;
    private String station = null;
    private Float odometer = null;
    private String grade = null;
    private Float amount = null;
    private Float ucost = null;

    public IncompleteEntry() {
    }

    public IncompleteEntry(LogEntry log) {
        this.setDate(log.getDate());
        this.setStation(log.getStation());
        this.setOdometer(log.getOdometer());
        this.setGrade(log.getGrade());
        this.setAmount(log.getAmount());
        this.setUcost(log.getUcost());
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setOdometer(Float odometer) {
        this.odometer = odometer;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setUcost(Float ucost) {
        this.ucost = ucost;
    }

    public Date getDate() {
        return date;
    }

    public String getStation() {
        return station;
    }

    public Float getOdometer() {
        return odometer;
    }

    public String getGrade() {
        return grade;
    }

    public Float getAmount() {
        return amount;
    }

    public Float getUcost() {
        return ucost;
    }

    public boolean isComplete() {
        return this.date != null && this.station != null && this.odometer != null &&
                this.grade != null && this.amount != null && this.ucost != null;
    }

    public LogEntry createLogEntry() {
        return new LogEntry(this.date, this.station, this.odometer, this.grade, this.amount,
                this.ucost);
    }
}
