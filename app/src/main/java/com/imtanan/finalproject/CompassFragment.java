package com.imtanan.finalproject;

/**
 * Created by Mani on 03/01/2016.
 */
//import com.msdesign.qibla.R;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassFragment extends Fragment implements SensorEventListener {

    TextView notice1, notice2;
    private ImageView image; // Define the display assembly compass picture
    private float currentDegree = 0; // Record the compass picture angle turned
    private SensorManager mSensorManager; // Device sensor manager

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.compass_fragment, container, false);

        notice1 = (TextView) v.findViewById(R.id.notice1);
        image = (ImageView) v.findViewById(R.id.viewCompass);
        notice2 = (TextView) v.findViewById(R.id.notice2);

        // Initialise android device sensor capabilities
        mSensorManager = (SensorManager) (SensorManager)getActivity().getSystemService(getActivity().SENSOR_SERVICE);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }
}