package com.noor.af.noortasksmanger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText etMail;
    private EditText etFullName;
    private EditText etPassw1;
    private EditText etPassw2;
    private Button btnSignUp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etMail = (EditText) findViewById(R.id.etMail);
        etPassw1 = (EditText) findViewById(R.id.etPassw1);
        etFullName = (EditText) findViewById(R.id.etFullName);
        etPassw2 = (EditText) findViewById(R.id.etPassw2);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        auth = FirebaseAuth.getInstance();
        eventHandler();
    }

    private void dataHadndler() {
        //1.getting data
        String stFullName = etFullName.getText().toString();
        String stPassw1 = etPassw1.getText().toString();
        String stPassw2 = etPassw2.getText().toString();
        String stMail = etMail.getText().toString();
        creatAcount(stMail, stPassw1);



        //2.checking
        boolean isokay = true;
        if (stFullName.length() == 0) {
            etFullName.setError("No name");
            isokay = false;
        }
        if (stPassw1.length() < 3) {
            etPassw1.setError("The password to is short");
            isokay = false;
        }
        if (stMail.length() < 3) {
            etMail.setError("the email is to short");
            isokay = false;
        }
        if (stPassw2.length() < stPassw1.length()) {
            etPassw2.setError("the password is Incorrect");
            isokay = false;
        }
        if (isokay)
            creatAcount(stMail, stPassw1);

    }


    /**
     * putting event handler for the (listeners)
     */
    private void eventHandler() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUpActivity.this,TasksListActivity.class);
                startActivity(i);
                dataHadndler();

            }
        });
    }

    private FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            //4.
            FirebaseUser user = firebaseAuth.getCurrentUser();

            if (user != null) {

                //user is signed in
                Toast.makeText(SignUpActivity.this, "user is signed in.", Toast.LENGTH_SHORT).show();

            } else {
                //user signed out
                Toast.makeText(SignUpActivity.this, "user signed out.", Toast.LENGTH_SHORT).show();

            }
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }




    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null)
            auth.removeAuthStateListener(authStateListener);
    }

    private void creatAcount(String email, String passw) {
        auth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>()

        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                    //updateUserProfile(task.getResult().getUser());

                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

}







