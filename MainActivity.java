package com.example.bishwendra.note_student_activity;

        import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    DatabaseHelper db = new DatabaseHelper(this);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner =(Spinner)findViewById(R.id.spinner2);
        String [] Entity = {"Select","Student","Teacher"};
        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Entity);
        spinner.setAdapter(adapter);


    }



    public void onClick (View view){

        if(view.getId()==R.id.login){
            EditText a =(EditText)findViewById(R.id.username);
            String str = a.getText().toString();
            EditText b =(EditText)findViewById(R.id.password);
            String pass = b.getText().toString();

            String password = db.searchPass(str,spinner.getSelectedItem().toString());
            if(pass.equals(password))
            {
                if(spinner.getSelectedItem().toString()=="Student") {
                    Intent i = new Intent(MainActivity.this, StudentPage.class);
                    i.putExtra("Username", str);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(MainActivity.this, TeacherPage.class);
                    i.putExtra("Username", str);
                    startActivity(i);
                }
            }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this,"Username and Password don't match" , Toast.LENGTH_SHORT);
                temp.show();
            }
        }
        if(view.getId()==R.id.register) {
            Intent intend = new Intent(this, Sign_Up.class);
            startActivity(intend);
        }
    }


}






