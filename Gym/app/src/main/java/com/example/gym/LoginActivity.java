package com.example.gym;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym.utils.Client;
import com.example.gym.utils.HashUtils;

import java.io.IOException;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonNext = findViewById(R.id.buttonNext);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Regresar a la actividad anterior
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextEmail = findViewById(R.id.editTextEmail);
                EditText editTextPassword = findViewById(R.id.editTextPassword);

                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                try {
                    Socket socket = new Socket("10.0.2.2", 8888);
                    Client client = new Client(socket, "MuscleUser");
                    client.sendMessage("SELECT email, password FROM usuarios" +
                            " WHERE usuarios.email = '" + email + "'" +
                            " AND usuarios.password = '" + HashUtils.hashPassword(password) + "';");

                    //Intent intent = new Intent(LoginActivity.this, NextActivity.class);
                    //startActivity(intent);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
