package com.noor.af.noortasksmanger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    private EditText etMail;
    private EditText etPassword;
    private Button btnSignIn, btnLogIn;
    private FirebaseAuth auth;

    private View.OnClickListener clicklis = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnSignIn) {
                String email = etMail.getText().toString();
                String password = etPassword.getText().toString();

            }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        etMail = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etpassword);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(clicklis);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(clicklis);
        auth=FirebaseAuth.getInstance();

        eventHandler();

    }


    /**
     * 1. getting data from the ui form(editText,check box,radiobutton..)
     * 2.checking data (the email text is ok ,the password,)
     * 3.dealing with the data
     */
    private void dataHadndler() {
        //1.getting data
        String stEmail = etMail.getText().toString();
        String stPassword = etPassword.getText().toString();
        boolean isokay=true;
        {
            //2.checking
            if (stEmail.length() < 3) {
                etMail.setError("wrong Email");
                isokay = false;
            }
            if (stPassword.length() < 8) {
                etPassword.setError("wrong password");
                isokay = false;
            }
            if (isokay==true) {
                signIn(stEmail, stPassword);
            }
        }

    }
    private void signIn(String email, String passw)
    {
        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(LogInActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                     Intent intent=new Intent(LogInActivity.this,TasksListActivity.class);
                       startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(LogInActivity.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }

            }
        });}

    /**
     * putting event handler for the (listeners)
     */
    private void eventHandler() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(i);




            }
        });


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHadndler();



            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (authStateListener!=null)
        auth.addAuthStateListener(authStateListener);
    }
    private FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            //4.
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {

                //user is signed in
                Toast.makeText(LogInActivity.this, "user is signed in.", Toast.LENGTH_SHORT).show();

            } else {
                //user signed out
                Toast.makeText(LogInActivity.this, "user signed out.", Toast.LENGTH_SHORT).show();

            }
        }
    };

}



