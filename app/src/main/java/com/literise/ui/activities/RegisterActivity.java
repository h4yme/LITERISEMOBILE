package com.literise.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.literise.R;
import com.literise.data.mock.MockAuthData;
import com.literise.models.Student;
import com.literise.models.User;
import com.literise.utils.SessionManager;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextChildName, editTextPhone, editTextPassword;
    private Button buttonRegister;
    private TextView textViewLogin;
    private ImageView buttonBack;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize session manager
        sessionManager = new SessionManager(this);

        // Initialize views
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextChildName = findViewById(R.id.editTextChildName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewLogin = findViewById(R.id.textViewLogin);
        buttonBack = findViewById(R.id.buttonBack);

        // Set click listeners
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to login
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back
            }
        });
    }

    private void performRegistration() {
        String fullName = editTextFullName.getText().toString().trim();
        String childName = editTextChildName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validation
        if (fullName.isEmpty()) {
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
            return;
        }

        if (childName.isEmpty()) {
            editTextChildName.setError("Child's name is required");
            editTextChildName.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError("Phone number is required");
            editTextPhone.requestFocus();
            return;
        }

        if (password.isEmpty() || password.length() < 6) {
            editTextPassword.setError("Password must be at least 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        // Mock registration
        User newUser = MockAuthData.register(phone, fullName, childName, password);

        if (newUser != null) {
            // Registration successful - auto verify and login
            newUser.setVerified(true);

            // Get associated student
            Student student = MockAuthData.getStudentByUserId(newUser.getUserId());

            // Create session
            sessionManager.createLoginSession(
                newUser.getUserId(),
                newUser.getPhoneNumber(),
                newUser.getFullName(),
                student != null ? student.getStudentId() : -1
            );

            Toast.makeText(this, "Registration successful! Welcome to LiteRise!", Toast.LENGTH_LONG).show();

            // Navigate to Login screen (or later: Welcome/Dashboard)
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        } else {
            // Registration failed (user already exists)
            Toast.makeText(this, "Phone number already registered", Toast.LENGTH_SHORT).show();
        }
    }
}
