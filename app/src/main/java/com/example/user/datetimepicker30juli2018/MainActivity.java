package com.example.user.datetimepicker30juli2018;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    TextView tvDateTime,tvDateTimeNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDateTime = (TextView) findViewById(R.id.tvDateTime);
        tvDateTimeNow = (TextView) findViewById(R.id.tvDateTimeNow);

        Toast.makeText(this, "language : " + Locale.getDefault().getDisplayLanguage(), Toast.LENGTH_SHORT).show();

        Calendar cal = Calendar.getInstance();

        DateFormat init = new SimpleDateFormat("MMM dd,yyyy");
        DateFormat fin = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        if(Locale.getDefault().getDisplayLanguage().equals("Bahasa Indonesia")){
            init = new SimpleDateFormat("dd MMM yyyy");
        }
        else if (Locale.getDefault().getDisplayLanguage().equals("English")){
            init = new SimpleDateFormat("MMM dd,yyyy");
        }

        String currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(cal.getTime());
        Toast.makeText(this, "now : " + currentDate, Toast.LENGTH_SHORT).show();
        try{
            Date dt = init.parse(currentDate);
            String output = fin.format(dt);
            tvDateTimeNow.setText(output);
        }catch(ParseException e){
            e.printStackTrace();
        }



        tvDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        mDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String rawDate = year + "-" + String.format("%02d",month)+ "-" + String.format("%02d", day);
                String datetimenow = tvDateTimeNow.getText().toString();

                tvDateTime.setText(rawDate);

                TextView tvDateTimeNow = (TextView) findViewById(R.id.tvDateTimeNow);
                TextView tvResult = (TextView) findViewById(R.id.tvResult);

                Toast.makeText(MainActivity.this, "picker : " + rawDate, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "now : " + datetimenow, Toast.LENGTH_SHORT).show();

                String result = "";
                if(rawDate.compareTo(datetimenow) < 0){
                    result = "picker < now";
                }else if(rawDate.compareTo(datetimenow) == 0){
                    result = "picker == now";
                }else if(rawDate.compareTo(datetimenow) > 0) {
                    result = "picker > now";
                }
                tvResult.setText(result);
                Log.w("DatePicker","Date : " + rawDate);
                Log.w("DateTimeNow", "Date : " + datetimenow);


            }
        };
    }
}
