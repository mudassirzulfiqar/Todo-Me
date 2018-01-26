package com.fahad.reminderme;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fahad.reminderme.model.Todo;
import com.fahad.reminderme.storage.DbHelper;
import com.fahad.reminderme.util.BaseActivity;
import com.fahad.reminderme.util.ReminderUtil;
import com.fahad.reminderme.util.TimeUtil;

import java.util.Calendar;

import timber.log.Timber;

public class AddTask extends BaseActivity {


    private EditText titleTextview;
    private TextView timePickerTextview;

    private String timeInMillis = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleTextview = findViewById(R.id.title_textview);
        timePickerTextview = findViewById(R.id.time_selector);

        FloatingActionButton fab = findViewById(R.id.fab);

        timePickerTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
/*
                com.wdullaer.materialdatetimepicker.time.TimePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(com.wdullaer.materialdatetimepicker.time.TimePickerDialog view, int hourOfDay, int minute, int second) {
                        timePickerTextview.setText(hourOfDay + ":" + minute);

                        Calendar toSet = Calendar.getInstance();
                        toSet.add(Calendar.HOUR_OF_DAY, hourOfDay);
                        toSet.add(Calendar.MINUTE, minute);
                        toSet.add(Calendar.SECOND, 0);

                        timeInMillis = String.valueOf(toSet.getTimeInMillis());
                        Timber.d("Set" + TimeUtil.parseTimeinLong(timeInMillis));

                    }*/


                final TimePickerDialog timePickerDialog = new TimePickerDialog(AddTask.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        timePickerTextview.setText(hourOfDay + ":" + minute);

                        Calendar toSet = Calendar.getInstance();
                        toSet.add(Calendar.HOUR_OF_DAY, hourOfDay);
                        toSet.add(Calendar.MINUTE, minute);
                        toSet.add(Calendar.SECOND, 0);

                        timeInMillis = String.valueOf(toSet.getTimeInMillis());
                        Timber.d("Set" + TimeUtil.parseTimeinLong(timeInMillis));
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

                timePickerDialog.show();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveData();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void saveData() {

        if (isValid()) {

            DbHelper dbHelper = new DbHelper(this);

            long insertedRowID = dbHelper.insert(new Todo(titleTextview.getText().toString(), String.valueOf(timeInMillis), ""));

            if (insertedRowID != -1) {
                showSuccess("Todo Created");
                setReminder(insertedRowID);
            }
        } else {
            showError("Fill all the fields");
        }

    }

    private void setReminder(long insertedRowID) {

        Integer idOfInsertedData = Integer.valueOf(String.valueOf((insertedRowID)));

        ReminderUtil.StartTimeAlarmSet(this, timeInMillis, idOfInsertedData, titleTextview.getText().toString());
    }


    boolean isValid() {

        boolean isValid = false;

        if (!titleTextview.getText().toString().isEmpty()) {
            if (!timePickerTextview.getText().toString().isEmpty()) {
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public String getProgressDialogName() {
        return "";
    }

}
