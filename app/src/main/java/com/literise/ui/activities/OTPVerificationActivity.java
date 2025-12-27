package com.literise.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.literise.R;
import com.literise.data.mock.MockAuthData;
import com.literise.models.Student;
import com.literise.models.User;
import com.literise.utils.SessionManager;

public class OTPVerificationActivity extends AppCompatActivity {

    private TextView[] otpBoxes;
    private TextView textViewPhoneNumber, textViewTimer;
    private Button buttonVerify;
    private Button[] numberButtons;
    private Button buttonBackspace;
    private ImageView buttonBack;

    private String phoneNumber;
    private boolean fromRegistration;
    private StringBuilder otpCode;
    private int currentOtpIndex = 0;

    private CountDownTimer countDownTimer;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Get data from intent
        phoneNumber = getIntent().getStringExtra("phone_number");
        fromRegistration = getIntent().getBooleanExtra("from_registration", false);

        // Initialize session manager
        sessionManager = new SessionManager(this);

        // Initialize OTP code
        otpCode = new StringBuilder();

        // Initialize views
        initializeViews();
        setupNumberPad();
        startTimer();

        // Display phone number
        textViewPhoneNumber.setText("+" + phoneNumber);
    }

    private void initializeViews() {
        // OTP Boxes
        otpBoxes = new TextView[]{
            findViewById(R.id.otpBox1),
            findViewById(R.id.otpBox2),
            findViewById(R.id.otpBox3),
            findViewById(R.id.otpBox4),
            findViewById(R.id.otpBox5),
            findViewById(R.id.otpBox6)
        };

        textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonVerify = findViewById(R.id.buttonVerify);
        buttonBack = findViewById(R.id.buttonBack);

        // Number buttons
        numberButtons = new Button[]{
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        };

        buttonBackspace = findViewById(R.id.buttonBackspace);

        // Set listeners
        buttonVerify.setOnClickListener(v -> verifyOTP());
        buttonBack.setOnClickListener(v -> finish());
    }

    private void setupNumberPad() {
        // Number buttons
        for (int i = 0; i < numberButtons.length; i++) {
            final String number = String.valueOf(i);
            numberButtons[i].setOnClickListener(v -> addDigit(number));
        }

        // Backspace button
        buttonBackspace.setOnClickListener(v -> removeDigit());
    }

    private void addDigit(String digit) {
        if (currentOtpIndex < 6) {
            otpCode.append(digit);
            otpBoxes[currentOtpIndex].setText(digit);
            currentOtpIndex++;
        }
    }

    private void removeDigit() {
        if (currentOtpIndex > 0) {
            currentOtpIndex--;
            otpBoxes[currentOtpIndex].setText("");
            otpCode.deleteCharAt(otpCode.length() - 1);
        }
    }

    private void verifyOTP() {
        if (otpCode.length() != 6) {
            Toast.makeText(this, "Masukkan kode OTP 6 digit", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mock OTP verification
        boolean isValid = MockAuthData.verifyOTP(phoneNumber, otpCode.toString());

        if (isValid) {
            Toast.makeText(this, "Verifikasi berhasil!", Toast.LENGTH_SHORT).show();

            // Mock: For testing, any 6-digit code works
            // In production, this would validate with backend

            // Login the user automatically after verification
            User user = MockAuthData.login(phoneNumber, "password123"); // Mock password
            if (user != null) {
                Student student = MockAuthData.getStudentByUserId(user.getUserId());

                sessionManager.createLoginSession(
                    user.getUserId(),
                    user.getPhoneNumber(),
                    user.getFullName(),
                    student != null ? student.getStudentId() : -1
                );
            }

            // Navigate to Dashboard or Welcome screen
            // For now, show success and go to login
            Toast.makeText(this, "Akun Anda telah diverifikasi!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(OTPVerificationActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "Kode OTP salah. Coba lagi.", Toast.LENGTH_SHORT).show();
            // Clear OTP
            clearOTP();
        }
    }

    private void clearOTP() {
        otpCode.setLength(0);
        currentOtpIndex = 0;
        for (TextView box : otpBoxes) {
            box.setText("");
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(318000, 1000) { // 5:18 minutes
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;

                String timeString = String.format("Tidak Menerima Kode OTP?\n%02d:%02d", minutes, seconds);
                textViewTimer.setText(timeString);
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("Kirim ulang OTP");
                textViewTimer.setTextColor(getResources().getColor(R.color.primary_purple));
                textViewTimer.setOnClickListener(v -> {
                    Toast.makeText(OTPVerificationActivity.this,
                        "OTP baru telah dikirim: 123456", Toast.LENGTH_LONG).show();
                    startTimer();
                });
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
