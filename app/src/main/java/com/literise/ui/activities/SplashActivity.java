package com.literise.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.literise.R;
import com.literise.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500; // 2.5 seconds
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize session manager
        sessionManager = new SessionManager(this);

        // Get center content view
        LinearLayout centerContent = findViewById(R.id.centerContent);

        // Load and apply fade-in animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        centerContent.startAnimation(fadeIn);

        // Delay and navigate
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToNextScreen();
            }
        }, SPLASH_DURATION);
    }

    private void navigateToNextScreen() {
        Intent intent;

        if (sessionManager.isLoggedIn()) {
            // User is logged in - go to Dashboard (to be implemented)
            // For now, go to login screen
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        } else {
            // User not logged in - go to Login screen
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        }

        startActivity(intent);
        finish(); // Close splash activity
    }
}
