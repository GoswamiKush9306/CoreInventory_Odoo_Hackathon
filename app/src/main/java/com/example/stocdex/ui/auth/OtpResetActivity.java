package com.example.stocdex.ui.auth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stocdex.R;
import com.stocdex.data.ThemeUtils;

public class OtpResetActivity extends AppCompatActivity {

    private String sentOtp = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_reset);

        EditText inputEmail = findViewById(R.id.inputEmailOtp);
        EditText inputOtp = findViewById(R.id.inputOtp);
        EditText inputNewPassword = findViewById(R.id.inputNewPassword);
        Button buttonSendOtp = findViewById(R.id.buttonSendOtp);
        Button buttonConfirmReset = findViewById(R.id.buttonConfirmReset);

        buttonSendOtp.setOnClickListener(v -> {
            String email = inputEmail.getText().toString();
            Toast.makeText(this, "OTP sent to " + email, Toast.LENGTH_SHORT).show();
        });

        buttonConfirmReset.setOnClickListener(v -> {
            String enteredOtp = inputOtp.getText().toString();
            if (sentOtp.equals(enteredOtp)) {
                Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else {
                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
}