package com.jing.canvastest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleView = (CircleView) findViewById(R.id.circleView);


        AnimatorSet set = new AnimatorSet();

        ObjectAnimator animator = ObjectAnimator.ofFloat(circleView, CircleView.CIRCLE_RADIUS_PROGRESS, 0.1f, 1f);
        animator.setDuration(1250);
        animator.setInterpolator(DECCELERATE_INTERPOLATOR);


        ObjectAnimator animator1 = ObjectAnimator.ofFloat(circleView, CircleView.INNER_CIRCLE_RADIUS_PROGRESS, 0.1f, 1f);
        animator1.setDuration(4200);
        animator1.setStartDelay(1200);
        animator1.setInterpolator(DECCELERATE_INTERPOLATOR);

        set.playTogether(animator,animator1);
        set.start();



    }
}
