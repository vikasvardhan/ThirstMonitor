package com.example.thirstmonitor.LoginRegistration;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thirstmonitor.MainWindow.MainActivity;
import com.example.thirstmonitor.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginRegistrationActivity extends AppCompatActivity {
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        Login = (Button) findViewById(R.id.button_submit);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splashIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        });
    }
}
