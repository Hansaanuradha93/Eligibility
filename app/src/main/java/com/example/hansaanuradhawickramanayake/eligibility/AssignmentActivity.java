package com.example.hansaanuradhawickramanayake.eligibility;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AssignmentActivity extends AppCompatActivity {

    RadioButton completedRadioButton, notCompletedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        setTitle("Assignment Status");

        completedRadioButton = findViewById(R.id.completedRadioButton);
        notCompletedRadioButton = findViewById(R.id.notCompletedRadioButton);
    }

    public void checkAssignment(View view){

        if (!completedRadioButton.isChecked() && !notCompletedRadioButton.isChecked()){

            Toast.makeText(this, "Check again", Toast.LENGTH_LONG).show();
        } else if(notCompletedRadioButton.isChecked()){

            Toast.makeText(this, "Please complete required Assignments", Toast.LENGTH_LONG).show();
        } else if (completedRadioButton.isChecked()){

            Intent intent = new Intent(AssignmentActivity.this, AttendanceActivity.class);
            startActivity(intent);
        }    }

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
                Intent intent = new Intent(AssignmentActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }
}
