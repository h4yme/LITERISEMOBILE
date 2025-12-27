package com.literise.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.literise.R;
import com.literise.data.mock.MockAuthData;
import com.literise.models.Student;
import com.literise.models.User;
import com.literise.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextPhone, editTextPassword;
    private Button buttonLogin;
    private TextView textViewRegister, textViewForgotPassword;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewRegister = findViewById(R.id.textViewRegister);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);

        // Initialize session manager
        sessionManager = new SessionManager(this);

        // Set click listeners
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Register screen
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Forgot Password feature coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performLogin() {
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validation
        if (phone.isEmpty()) {
            editTextPhone.setError("Phone number required");
            editTextPhone.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        // Mock login
        User user = MockAuthData.login(phone, password);

        if (user != null) {
            // Login successful
            // Get associated student
            Student student = MockAuthData.getStudentByUserId(user.getUserId());

            // Create session
            sessionManager.createLoginSession(
                user.getUserId(),
                user.getPhoneNumber(),
                user.getFullName(),
                student != null ? student.getStudentId() : -1
            );

            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

            // Navigate to Dashboard (to be implemented)
            // For now, show success message
            Toast.makeText(this, "Welcome " + user.getFullName() + "!", Toast.LENGTH_LONG).show();

        } else {
            // Login failed
            Toast.makeText(this, "Invalid phone number or password", Toast.LENGTH_SHORT).show();
        }
    }
}
