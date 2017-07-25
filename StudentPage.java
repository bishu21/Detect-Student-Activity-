package com.example.bishwendra.note_student_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentPage extends AppCompatActivity {



    DatabaseHelper db = new DatabaseHelper(this);


    private static ListView list_view,header;
    private  static  String [] Teacher_Names =  {};
    private static  String [] Header = {"SELECT TEACHER"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.Tvusername);
        tv.setText(username);


        final List<String> Teacher = new ArrayList<String>(Arrays.asList(Teacher_Names));


        HeaderList();
        GetAllTeachers(Teacher);
        ShowTeacherList(Teacher);


        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(StudentPage.this, StudentJoin.class);
                String message = "abc";
                intent.putExtra("EXTRA_MESSAGE",parent.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }

    public void HeaderList() {
        header = (ListView)findViewById(R.id.Headerlist) ;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_header,Header);
        header.setAdapter(adapter);
    }

    public void ShowTeacherList(List<String>Teacher){
        list_view = (ListView)findViewById(R.id.TeacherList) ;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,Teacher);
        list_view.setAdapter(adapter);
        Toast temp = Toast.makeText(StudentPage.this," hello" , Toast.LENGTH_SHORT);
        temp.show();
    }

    private void GetAllTeachers(List<String>Teacher) {
        db.GetTeacher(Teacher);


    }

    public void onClick_logout(View view) {
        Intent intend = new Intent(this,MainActivity.class);
        startActivity(intend);
    }

}


