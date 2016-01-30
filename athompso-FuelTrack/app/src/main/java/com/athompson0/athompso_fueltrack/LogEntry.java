package com.athompson0.athompso_fueltrack;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by athompso on 1/25/16.
 */
public class LogEntry implements Serializable {
    private Date date;
    private String station;
    private Float odometer;
    private String grade;
    private Float amount;
    private Float ucost;
    private Float tcost;

    public LogEntry(String station, Float odometer, String grade, Float amount, Float ucost) {
        this.station = station;
        this.odometer = odometer;
        this.grade = grade;
        this.amount = amount;
        this.ucost = ucost;

        this.date = new Date();
        this.tcost = this.amount * (this.ucost/100);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Float getOdometer() {
        return odometer;
    }

    public void setOdometer(Float odometer) {
        this.odometer = odometer;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
        this.tcost = this.amount * (this.ucost/100);
    }

    public Float getUcost() {
        return ucost;
    }

    public void setUcost(Float ucost) {
        this.ucost = ucost;
        this.tcost = this.amount * (this.ucost/100);
    }

    public Float getTcost() {
        return tcost;
    }

    @Override
    public String toString() {
        Float rounded = BigDecimal.valueOf(this.tcost).setScale(2, BigDecimal.ROUND_HALF_UP)
                .floatValue();
        return date.toString() + ", " + station + "\nCost: " + rounded.toString();
    }
}
