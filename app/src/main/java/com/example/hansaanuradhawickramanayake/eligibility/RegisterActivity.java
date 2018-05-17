package com.example.hansaanuradhawickramanayake.eligibility;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    // Fields
    EditText usernameRegisterEditText, passwordRegisterEditText;

    // Firebase
    private FirebaseAuth mAuth;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameRegisterEditText = findViewById(R.id.usernameRegisterEditText);
        passwordRegisterEditText = findViewById(R.id.passwordRegisterEditText);

        mAuth = FirebaseAuth.getInstance();
    }

    public void register(View view){

        username = usernameRegisterEditText.getText().toString();
        password = passwordRegisterEditText.getText().toString();



        if (username.isEmpty() || password.isEmpty()){

            Toast.makeText(this, " Email and Password is required", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
                                pd.setMessage("Loading");
                                pd.show();

                                Log.d("result", "createUserWithEmail:success");
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, PaymentsActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("result", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }

    }

    public void cancel(View view){

        usernameRegisterEditText.setText("");
        passwordRegisterEditText.setText("");
    }
}
