package com.example.bishwendra.note_student_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TeacherPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_page);
        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.Tvusername);
        tv.setText(username);
        Button logOut = (Button)findViewById(R.id.logout);

    }

    public void onClick_logout(View view) {
        Intent intend = new Intent(this,MainActivity.class);
        startActivity(intend);
    }

}
