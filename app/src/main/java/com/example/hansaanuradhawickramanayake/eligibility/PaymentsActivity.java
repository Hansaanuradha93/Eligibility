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

public class PaymentsActivity extends AppCompatActivity {

    RadioButton paidRadioButton, notPaidRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        setTitle("Check Payments");

        paidRadioButton = findViewById(R.id.paidRadioButton);
        notPaidRadioButton = findViewById(R.id.notPaidRadioButton);
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
                Intent intent = new Intent(PaymentsActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }


    public void checkPayments(View view){

        if (!paidRadioButton.isChecked() && !notPaidRadioButton.isChecked()){

            Toast.makeText(this, "Check again", Toast.LENGTH_LONG).show();
        } else if(notPaidRadioButton.isChecked()){

            Toast.makeText(this, "Please make payments", Toast.LENGTH_LONG).show();
        } else if (paidRadioButton.isChecked()){

            Intent intent = new Intent(PaymentsActivity.this, AssignmentActivity.class);
            startActivity(intent);
        }

    }

}
