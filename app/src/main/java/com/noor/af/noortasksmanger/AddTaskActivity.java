package com.noor.af.noortasksmanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.noor.af.noortasksmanger.Data.MyTask;

import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etText;
    private EditText etPhone;
    private Button btnContacts;
    private EditText etLocation;
    private Button btnSave;
    private RatingBar rtBarPriorityr;
    private ImageButton btnImageButton;
   // private DatabaseReference reference;
   // private Button btnLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task2);
        etText = (EditText) findViewById(R.id.etText);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnContacts = (Button) findViewById(R.id.btnContacts);
        etLocation = (EditText) findViewById(R.id.etLocation);
        btnSave = (Button) findViewById(R.id.btnSave);
        rtBarPriorityr=(RatingBar)findViewById(R.id.rtBarPriority);
        btnImageButton=(ImageButton)findViewById(R.id.btnImageButton);


        eventHandler();
    }


    private  void dataHadndler() {
        //1.getting data
        String stText = etText.getText().toString();
        String stPhone = etPhone.getText().toString();
        String stLocation = etLocation.getText().toString();
        int prio = rtBarPriorityr.getProgress();
        Date date = Calendar.getInstance().getTime();

        boolean isOk = true;
        //2.checking
        if (stText.length() < 3) {
            etText.setError("");
            isOk = false;
        }
        if (stPhone.length() < 10) {
            etPhone.setError("The phone number is short");
            isOk = false;
        }
        if (stLocation.length()==0) {
            etLocation.setError("Wrong Location");
            isOk = false;
        }
        if (isOk) {
           MyTask myTask = new MyTask();
            myTask.setPhone(stPhone);
            myTask.setPriority(prio);
            myTask.setWhen(date);
            myTask.setId(stText);
            myTask.setAddress(stLocation);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            // reference.push().setValue("Hello Noor");
            //use push to add value
            //sample
            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            email = email.replace(".", "_");
            //all my task will be under my mail under the root MyTask
            //child can not contain chars:$ @ * ...

            //1                        //2
            reference.child(email).child("My Tasks").push().setValue(myTask, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference)
                {
                   // reference.child(email).child("MyTasks").push().setValue(myTask,new DatabaseReference);                    //3
                    if (databaseError == null)// add successful

                    {
                        Toast.makeText(AddTaskActivity.this, "Save", Toast.LENGTH_LONG).show();
                    } else// adding failed
                    {
                        Toast.makeText(AddTaskActivity.this, "Saving failed" + databaseError.getMessage(), Toast.LENGTH_LONG).show();


                    }


                }


            });


        }
    }


    /**
     * putting event handler for the (listeners)
     */
    private  void eventHandler()
    {
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(AddTaskActivity.this,TasksListActivity.class);
                startActivity(i);
//                DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
//                Date date= Calendar.getInstance().getTime();
//               MyTask t= new MyTask("Noor",5,date,"Danoon","0528698754","2524800");
//                t.setAddress("111");
//                t.setId("222");
//                t.setPhone("0528695426");
//                t.setTitle("777");
//                t.setPriority(3);




               dataHadndler();






            }
        });
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rtBarPriorityr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

}

//MyTask t =new MyTask("Noor",5,date,"danoon","05255","456");
       // t.getAddress();
        //t.getAddress();
       // t.getId();
       // t.getTitle();
       // t.getWhen();
     //   String email=FirebaseAuth.getInstance().getCurrentUser().getEmail();
      //  email=email.replace(".","_");
        //reference.child(email).child("MyTask").push().setValue(MyTask);
