package com.athompson0.athompso_fueltrack;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by athompso on 1/29/16.
 */
public class LogEntryList implements Serializable {
    private ArrayList<LogEntry> logs;
    private float total;

    public LogEntryList() {
        logs = new ArrayList<LogEntry>();
        total = 0;
    }

    public void setLogs(ArrayList<LogEntry> logs) {
        this.logs = logs;
        this.total = 0;
        for (int index = 0; index < logs.size(); index++) {
            total += logs.get(index).getTcost();
        }
    }

    public ArrayList<LogEntry> getLogs() {
        return logs;
    }

    public LogEntry getLog(int index) {
        return logs.get(index);
    }

    public float getTotal() {
        return total;
    }

    public void add(LogEntry log) {
        logs.add(log);
        total += log.getTcost();
    }

    public void replace(LogEntry oldLog, LogEntry newLog) {
        int index = logs.indexOf(oldLog);
        logs.remove(index);
        logs.add(index, newLog);
        total = total - oldLog.getTcost() + newLog.getTcost();
    }
}
