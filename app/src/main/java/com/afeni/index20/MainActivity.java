package com.afeni.index20;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.afeni.index20.controllers.WindManager;
import com.afeni.index20.models.Wind;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener2 {

    private SensorManager mSensorManager;
    private ArrayList<Wind> index20;

    TextView logMAG;
    TextView logORI;
    TextView logX;
    TextView logY;
    TextView logZ;
    float[] mGravity;
    float[] mGeomagnetic;
    float initialRotation = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.index20 = WindManager.getWinds(getResources().getStringArray(R.array.index20));
        setContentView(R.layout.activity_main);

        this.logMAG = findViewById(R.id.logMAG);
        this.logMAG.setText("MAG");

        this.logORI = findViewById(R.id.logORI);
        this.logORI.setText("ORI");
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        this.logX = findViewById(R.id.logX);
        this.logX.setText(index20.get(0).getName());
        this.logY = findViewById(R.id.logY);
        this.logY.setText(index20.get(0).getCardinal());
        this.logZ = findViewById(R.id.logZ);
        this.logZ.setText(String.valueOf(index20.get(0).getFloat_value()));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onFlushCompleted(Sensor sensor) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            mGravity = event.values;
            if(this.initialRotation == 0f) {
                this.initialRotation = mGravity[0];
            }
            this.logORI.setText(Float.toString(Math.round(this.initialRotation - mGravity[0])));

        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            mGeomagnetic = event.values;
            this.logMAG.setText(Float.toString(Math.round(mGeomagnetic[0])));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}