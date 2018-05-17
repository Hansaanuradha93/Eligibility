package com.example.hansaanuradhawickramanayake.eligibility;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AttendanceActivity extends AppCompatActivity {

    EditText attendanceEditText;
    Button medicalReportButton;
    TextView eligibilityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        setTitle("Check Attendance");

        attendanceEditText = findViewById(R.id.attendanceEditText);
        medicalReportButton = findViewById(R.id.medicalReportutton);
        eligibilityTextView = findViewById(R.id.eligibilityTextView);

        attendanceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                eligibilityTextView.setVisibility(View.INVISIBLE);
                eligibilityTextView.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void checkAttendance(View view){


        if(attendanceEditText.getText().toString().isEmpty()){

            Toast.makeText(this, "Enter your attendance", Toast.LENGTH_LONG).show();
        } else {

            Double attendanceValue = Double.parseDouble(attendanceEditText.getText().toString());

            if (attendanceValue > 100 || attendanceValue < 0) {

                Toast.makeText(this, "Wrong Input. Check again", Toast.LENGTH_LONG).show();
                medicalReportButton.setVisibility(View.INVISIBLE);
            } else if (attendanceValue >= 80) {

                Toast.makeText(this, "You are Eligible for the exam", Toast.LENGTH_LONG).show();
                medicalReportButton.setVisibility(View.INVISIBLE);

                eligibilityTextView.setVisibility(View.VISIBLE);
                eligibilityTextView.setText("You are Eligible for the exam");


            } else if (attendanceValue >= 50) {

                Toast.makeText(this, "Please hand over an approved medical report to proceed", Toast.LENGTH_LONG).show();

                medicalReportButton.setVisibility(View.VISIBLE);
                medicalReportButton.isEnabled();

                eligibilityTextView.setVisibility(View.INVISIBLE);


            } else {

                Toast.makeText(this, "You are not eligible for the exam", Toast.LENGTH_LONG).show();
                medicalReportButton.setVisibility(View.INVISIBLE);


                eligibilityTextView.setVisibility(View.VISIBLE);
                eligibilityTextView.setText("You are Not Eligible for the exam");

            }
        }

    }

    public void checkMedicalReport(View view){


        Intent intent = new Intent(AttendanceActivity.this, MedicalReportActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){

            case R.id.logout :
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AttendanceActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }
}
