package com.imtanan.finalproject;

/**
 * Created by Mani on 03/01/2016.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.FloatRange;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LogoutFragment extends android.support.v4.app.Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.logout_frament, container, false);
        return v;
    }
}
