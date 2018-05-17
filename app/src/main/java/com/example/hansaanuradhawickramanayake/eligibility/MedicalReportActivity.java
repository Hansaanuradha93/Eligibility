package com.example.hansaanuradhawickramanayake.eligibility;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MedicalReportActivity extends AppCompatActivity {

    RadioButton approvedRadioButton, notApprovedRadioButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report);

        setTitle("Check Medical Report");

        approvedRadioButton = findViewById(R.id.approvedRadioButton);
        notApprovedRadioButton = findViewById(R.id.notApprovedRadioButton);
        resultTextView = findViewById(R.id.resultTextView);

    }

    public void checkMedicalReportStatus(View view){

        if (!approvedRadioButton.isChecked() && !notApprovedRadioButton.isChecked()){

            Toast.makeText(this, "Check again", Toast.LENGTH_LONG).show();
        } else if(notApprovedRadioButton.isChecked()){

            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText("You are Not Eligible for the exam");
        } else if (approvedRadioButton.isChecked()){


            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText("You are Eligible for the exam");
        }
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
                Intent intent = new Intent(MedicalReportActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }
}
