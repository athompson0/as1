package com.athompson0.athompso_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);
        Intent intent = getIntent();
        LogEntry log = (LogEntry) intent.getSerializableExtra("entry");

        Button saveButton = (Button) findViewById(R.id.btnSave);
        Button cancelButton = (Button) findViewById(R.id.btnCancel);

        EditText dateText = (EditText) findViewById(R.id.etDate);
        EditText stationText = (EditText) findViewById(R.id.etStation);
        EditText odometerText = (EditText) findViewById(R.id.etOdometer);
        EditText gradeText = (EditText) findViewById(R.id.etGrade);
        EditText amountText = (EditText) findViewById(R.id.etAmount);
        EditText ucostText = (EditText) findViewById(R.id.etUcost);

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        dateText.setText(sdf.format(log.getDate()));
        stationText.setText(log.getStation());
        odometerText.setText(log.getOdometer().toString());
        gradeText.setText(log.getGrade());
        amountText.setText(log.getAmount().toString());
        ucostText.setText(log.getUcost().toString());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView invalidEntry = (TextView) findViewById(R.id.textInvalid);
                Intent intent = getIntent();
                LogEntry log = (LogEntry) intent.getSerializableExtra("entry");
                IncompleteEntry entries = new IncompleteEntry(log);

                if (validEntries(entries)) {
                    intent.putExtra("newLog", entries.createLogEntry());
                    intent.putExtra("oldLog", log);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                else {
                    invalidEntry.setVisibility(View.VISIBLE);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    public boolean validEntries(IncompleteEntry entries) {
        EditText dateText = (EditText) findViewById(R.id.etDate);
        EditText stationText = (EditText) findViewById(R.id.etStation);
        EditText odometerText = (EditText) findViewById(R.id.etOdometer);
        EditText gradeText = (EditText) findViewById(R.id.etGrade);
        EditText amountText = (EditText) findViewById(R.id.etAmount);
        EditText ucostText = (EditText) findViewById(R.id.etUcost);

        String sDate = dateText.getText().toString();
        String station = stationText.getText().toString();
        String sOdometer = odometerText.getText().toString();
        String grade = gradeText.getText().toString();
        String sAmount = amountText.getText().toString();
        String sUcost = ucostText.getText().toString();

        entries.setStation(station);
        entries.setGrade(grade);

        Date date;
        float odometer;
        float amount;
        float ucost;

        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(sDate);
        } catch (ParseException ex) {
            return Boolean.FALSE;
        }

        try {
            odometer = Float.parseFloat(sOdometer);
        } catch (NumberFormatException ex) {
            return Boolean.FALSE;
        }

        try {
            amount = Float.parseFloat(sAmount);
        } catch (NumberFormatException ex) {
            return Boolean.FALSE;
        }

        try {
            ucost = Float.parseFloat(sUcost);
        }
        catch (NumberFormatException ex) {
            return Boolean.FALSE;
        }

        entries.setOdometer(odometer);
        entries.setAmount(amount);
        entries.setUcost(ucost);

        return Boolean.TRUE;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
