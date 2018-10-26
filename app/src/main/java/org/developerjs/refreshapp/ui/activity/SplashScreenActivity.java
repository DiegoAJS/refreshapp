package org.developerjs.refreshapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import org.developerjs.refreshapp.MainActivity;
import org.developerjs.refreshapp.R;

public class SplashScreenActivity extends AppCompatActivity implements Animation.AnimationListener {

    public static String TAG=SplashScreenActivity.class.getSimpleName();

    private TextView textView;

    // Animation
    Animation animFadein;



    public static void createInstance(Context context) {
        Intent intent = getLaunchIntent(context);

        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(context, SplashScreenActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        textView =(TextView) findViewById(R.id.tvTextSplashScreen);

        // load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        // set animation listener
        animFadein.setAnimationListener(this);

        textView.startAnimation(animFadein);

        LottieAnimationView lottieAnimationView =(LottieAnimationView) findViewById(R.id.animation_view_lottie);

        lottieAnimationView.playAnimation();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // check for fade in animation
        if (animation == animFadein){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
