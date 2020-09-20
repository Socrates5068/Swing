package com.dev.socrates.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MainActivity root = this;
    Button loginButton, registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        loadComponents();
    }

    private void loadComponents() {
        loginButton = this.findViewById(R.id.login);
        registerButton = this.findViewById(R.id.cancel);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(root, RegisterUser.class);
                root.startActivity(registerActivity);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(root, RegisterUser.class);
                root.startActivity(loginActivity);
            }
        });

    }
}