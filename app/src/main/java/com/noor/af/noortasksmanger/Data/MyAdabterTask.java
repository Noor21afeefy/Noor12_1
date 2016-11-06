package com.noor.af.noortasksmanger.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.noor.af.noortasksmanger.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 10/30/2016.
 */
public class MyAdabterTask extends ArrayAdapter<MyTask> {

    public MyAdabterTask(Context context, int resource) {

        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.my_adabter,parent,false);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        EditText etPhone = (EditText) convertView.findViewById(R.id.etPhone);
        ImageButton btnCall=(ImageButton)convertView.findViewById(R.id.btnCall);
        ImageButton btnLocation = (ImageButton) convertView.findViewById(R.id.btnLocation);
//        Button btnSave = (Button) convertView.findViewById(R.id.btnSave);
//        Button btnContacts = (Button) convertView.findViewById(R.id.btnContacts);
//       RatingBar rtBarPriorityr=(RatingBar) convertView.findViewById(R.id.rtBarPriority);
        final MyTask myTask=getItem(position);
        tvTitle.setText(myTask.getTitle());
        etPhone.setText(myTask.getPhone());
//        rtBarPriorityr.setRating(myTask.getPriority());
//        etLocation.setText(myTask.getAddress());
//        btnContacts.setText(myTask.getId());
        btnCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }


        });

        btnLocation.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
        return convertView;






    }

}
