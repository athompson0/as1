package com.athompson0.athompso_fueltrack;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewLogActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);

        Button saveButton = (Button) findViewById(R.id.btnSave);
        Button cancelButton = (Button) findViewById(R.id.btnCancel);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                TextView invalidEntry = (TextView) findViewById(R.id.textInvalid);
                IncompleteEntry entries = parseEntries();

                if (entries.isComplete()) {
                    setResult(Activity.RESULT_OK, new Intent()
                            .putExtra("log",entries.createLogEntry()));
                    finish();
                }
                else {
                    invalidEntry.setVisibility(View.VISIBLE);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    public IncompleteEntry parseEntries() {
        IncompleteEntry entries = new IncompleteEntry();

        EditText stationText = (EditText) findViewById(R.id.etStation);
        EditText odometerText = (EditText) findViewById(R.id.etOdometer);
        EditText gradeText = (EditText) findViewById(R.id.etGrade);
        EditText amountText = (EditText) findViewById(R.id.etAmount);
        EditText ucostText = (EditText) findViewById(R.id.etUcost);

        String station = stationText.getText().toString();
        String sOdometer = odometerText.getText().toString();
        String grade = gradeText.getText().toString();
        String sAmount = amountText.getText().toString();
        String sUcost = ucostText.getText().toString();

        entries.setStation(station);
        entries.setGrade(grade);

        float odometer;
        float amount;
        float ucost;


        try {
            odometer = Float.parseFloat(sOdometer);
        } catch (NumberFormatException ex) {
            return entries;
        }

        try {
            amount = Float.parseFloat(sAmount);
        } catch (NumberFormatException ex) {
            return entries;
        }

        try {
            ucost = Float.parseFloat(sUcost);
        }
        catch (NumberFormatException ex) {
            return entries;
        }

        entries.setOdometer(odometer);
        entries.setAmount(amount);
        entries.setUcost(ucost);

        return entries;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_log, menu);
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
