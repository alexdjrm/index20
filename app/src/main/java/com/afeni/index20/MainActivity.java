package com.afeni.index20;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.afeni.index20.controllers.WindManager;

public class MainActivity extends AppCompatActivity implements SensorEventListener2 {

    private SensorManager mSensorManager;
    private WindManager windManager;

    TextView logMAG;
    TextView logORI;
    TextView logX;
    TextView logY;
    TextView logZ;
    ImageView imageViewProtractorPointer;
    float[] mGravity;
    float[] mGeomagnetic;
    float azimut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.windManager = new WindManager(getResources().getStringArray(R.array.index20));
        setContentView(R.layout.activity_main);
        this.logMAG = findViewById(R.id.logMAG);
        this.logMAG.setText("MAG");
        this.logORI = findViewById(R.id.logORI);
        this.logORI.setText("ORI");
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.logX = findViewById(R.id.logX);
        this.logY = findViewById(R.id.logY);
        this.logZ = findViewById(R.id.logZ);
        imageViewProtractorPointer = findViewById(R.id.compass);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
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

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;

        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];

            if (SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic)) {
                // orientation contains azimut, pitch and roll
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimut = orientation[0];
            }
        }
        float rotation = Math.round(azimut * 360 / (2 * 3.14159f));
        this.logMAG.setText(String.valueOf(rotation));
        imageViewProtractorPointer.setRotation(roundRotation(rotation));
        float absValue = rotation<0 ? 360+rotation : rotation;
        this.logX.setText(String.valueOf(windManager.getWindByRotation(absValue).getName()));
        this.logY.setText(String.valueOf(windManager.getWindByRotation(absValue).getDegrees()));
        this.logZ.setText(String.valueOf(windManager.getWindByRotation(absValue).getCardinal()));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private float roundRotation(float rotation){
        Double res = Math.floor(rotation/11.25)*11.25;
        return res.floatValue();
    }
}
