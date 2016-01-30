package com.athompson0.athompso_fueltrack;

/**
 * Created by athompso on 1/29/16.
 */
public class IncompleteEntry {
    private String station = null;
    private Float odometer = null;
    private String grade = null;
    private Float amount = null;
    private Float ucost = null;

    public IncompleteEntry() {
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

    public boolean isComplete() {
        return this.station != null && this.odometer != null && this.grade != null &&
                this.amount != null && this.ucost != null;
    }

    public LogEntry createLogEntry() {
        return new LogEntry(this.station, this.odometer, this.grade, this.amount, this.ucost);
    }
}
