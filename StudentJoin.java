package com.example.bishwendra.note_student_activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentJoin extends AppCompatActivity {


    public static final int REQUEST_CAPTURE = 1;
    ImageView result_photo;

    TextView ProximitySensor, ProximityMax, ProximityReading;

    SensorManager mySensorManager;
    Sensor myProximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_join);

        Button click = (Button) findViewById(R.id.button);
        result_photo = (ImageView) findViewById(R.id.imageView);

        if (!hasCamera()) {
            click.setEnabled(false);
        }


        String username = getIntent().getStringExtra("EXTRA_MESSAGE");
        TextView tv = (TextView)findViewById(R.id.Joined);
        tv.setText(username);


        ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
        ProximityMax = (TextView) findViewById(R.id.proximityMax);
        ProximityReading = (TextView) findViewById(R.id.proximityReading);

        mySensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);

        if (myProximitySensor == null) {
            ProximitySensor.setText("No Proximity Sensor!");
        } else {
            ProximitySensor.setText(myProximitySensor.getName());
            ProximityMax.setText("Maximum Range: "
                    + String.valueOf(myProximitySensor.getMaximumRange()));
            mySensorManager.registerListener(proximitySensorEventListener,
                    myProximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    SensorEventListener proximitySensorEventListener
            = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub

            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                ProximityReading.setText("Proximity Sensor Reading:"
                        + String.valueOf(event.values[0]));
            }
        }
    };

    public boolean hasCamera() {

        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View v) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == REQUEST_CAPTURE && requestCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap photo =(Bitmap)extras.get("data");
            result_photo.setImageBitmap(photo);
        }
    }
}
