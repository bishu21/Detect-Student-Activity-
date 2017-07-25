package com.example.bishwendra.note_student_activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Sign_Up extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editUser, editPass, editMobile,Type;
    Button register;
    Spinner spinner;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        spinner =(Spinner)findViewById(R.id.spinner);
        String [] Entity = {"Select","Student","Teacher"};
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Entity);
        spinner.setAdapter(adapter);


        myDb = new DatabaseHelper(this);

        editUser = (EditText)findViewById(R.id.usernameText);
        editPass = (EditText)findViewById(R.id.passwordText);
        editMobile = (EditText)findViewById(R.id.mobileText);
        register = (Button)findViewById(R.id.registerButton);

        AddData();
        addListenerOnSpinnerItemSelection();


    }

    private void addListenerOnSpinnerItemSelection() {
        spinner.setOnItemSelectedListener(new CustomOnSelectedListener());
    }


    public void AddData() {
        register.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {

                        Boolean isInserted = myDb.insertData(editUser.getText().toString(), editPass.getText().toString(), editMobile.getText().toString(),spinner.getSelectedItem().toString());

                        if (isInserted==Boolean.TRUE)
                            Toast.makeText(Sign_Up.this, "input is inserted " +
                                    "OnClickListener : " +

                                      "\nSpinner  : "+ String.valueOf(spinner.getSelectedItem()), Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Sign_Up.this, "input is not inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


}

