package com.noor.af.noortasksmanger;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.noor.af.noortasksmanger.Data.MyAdabterTask;
import com.noor.af.noortasksmanger.Data.MyTask;

import java.util.Date;

public class TasksListActivity extends AppCompatActivity {
    private ListView listView;
    private EditText etText;
    private Button btnFastAdd;
    private MyAdabterTask myAdabter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        etText = (EditText) findViewById(R.id.etText);
        btnFastAdd = (Button) findViewById(R.id.btnFastAdd);
        listView = (ListView) findViewById(R.id.listView);
        myAdabter = new MyAdabterTask(this, R.layout.my_adabter);
        listView.setAdapter(myAdabter);
        eventHandler();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmLogout:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this, LogInActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }


    private void dataHadndler() {
        //1.getting data
        String stText = etText.getText().toString();
        if (stText.length() == 0)
            etText.setError("No text");
    }

    /**
     * putting event handler for the (listeners)
     */
    private void eventHandler() {
        btnFastAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TasksListActivity.this, AddTaskActivity.class);
                startActivity(i);
                dataHadndler();


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initListView();
    }

    private void initListView() {
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".", "_");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(email);
        reference.child("My Tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myAdabter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MyTask myTask = ds.getValue(MyTask.class);
                    myTask.setId(ds.getKey());
                    myAdabter.add(myTask);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}












