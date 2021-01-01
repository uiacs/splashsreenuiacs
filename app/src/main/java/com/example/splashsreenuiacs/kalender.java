package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class kalender extends AppCompatActivity {
    CalendarView calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        calender = (CalendarView) findViewById(R.id.calender);

    }
}