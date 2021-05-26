package com.example.assignmentstart;

import android.app.Application;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ToggleButton;

public class Globals extends Application {
    public final static String API_KEY = "4006f6ec10541c5e23f72c51d5ceaa46";
    public static final String SERVICE_URL = "https://api.musixmatch.com/ws/1.1/";

    public static void buttonAnimation(ToggleButton toggleButton) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        toggleButton.startAnimation(scaleAnimation);
    }

    public static void buttonAnimation(Button button) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        button.startAnimation(scaleAnimation);
    }
}
