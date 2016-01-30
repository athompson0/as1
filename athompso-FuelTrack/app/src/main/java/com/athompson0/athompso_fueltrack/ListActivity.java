package com.athompson0.athompso_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView logList;
    private LogEntryList logs;
    private ArrayAdapter<LogEntry> adapter;
    private TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        logList = (ListView) findViewById(R.id.lvLogs);
        logs = new LogEntryList();
        totalText = (TextView) findViewById(R.id.textTotal);
        Button addButton = (Button) findViewById(R.id.btnAddEntry);

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewLogActivity.class);
                startActivityForResult(intent, 1);
                adapter.notifyDataSetChanged();
                saveInFile();
                //updateText();
            }
        });
    }

    public void updateText() {
        //code from
        //http://stackoverflow.com/questions/8911356/whats-the-best-practice-to-round-a-float-to-2-decimals
        float roundedTotal = BigDecimal.valueOf(logs.getTotal())
                .setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        totalText.setText("Total = $" + roundedTotal);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            LogEntry log = (LogEntry) intent.getSerializableExtra("log");
            logs.add(log);
        }
    }

    //saveInFile(), loadFromFile(), and onStart() functions taken from the lonelyTwitter app
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        updateText();
        adapter = new ArrayAdapter<LogEntry>(this, R.layout.list_entry, logs.getLogs());
        logList.setAdapter(adapter);

    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput("file.sav");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Taken from
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            // 01-29-2016
            Type listType = new TypeToken<ArrayList<LogEntry>>() {}.getType();
            ArrayList<LogEntry> theLogs = gson.fromJson(in, listType);
            logs.setLogs(theLogs);
        } catch (FileNotFoundException ex) {
            logs = new LogEntryList();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput("file.sav", 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(logs.getLogs(), out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
