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
import com.literise.models.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextChildName, editTextPhone, editTextPassword;
    private Button buttonRegister;
    private TextView textViewLogin;
    private ImageView buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
            editTextFullName.setError("Nama lengkap diperlukan");
            editTextFullName.requestFocus();
            return;
        }

        if (childName.isEmpty()) {
            editTextChildName.setError("Nama anak diperlukan");
            editTextChildName.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError("Nomor telepon diperlukan");
            editTextPhone.requestFocus();
            return;
        }

        if (password.isEmpty() || password.length() < 6) {
            editTextPassword.setError("Password minimal 6 karakter");
            editTextPassword.requestFocus();
            return;
        }

        // Mock registration
        User newUser = MockAuthData.register(phone, fullName, childName, password);

        if (newUser != null) {
            // Registration successful
            Toast.makeText(this, "Registrasi berhasil! Verifikasi nomor telepon Anda.", Toast.LENGTH_LONG).show();

            // Navigate to OTP verification
            Intent intent = new Intent(RegisterActivity.this, OTPVerificationActivity.class);
            intent.putExtra("phone_number", phone);
            intent.putExtra("from_registration", true);
            startActivity(intent);
            finish();

        } else {
            // Registration failed (user already exists)
            Toast.makeText(this, "Nomor telepon sudah terdaftar", Toast.LENGTH_SHORT).show();
        }
    }
}
