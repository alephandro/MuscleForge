package com.example.gym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gym.utils.Client;
import com.example.gym.utils.HashUtils;
import java.io.IOException;
import java.net.Socket;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonNext = findViewById(R.id.buttonNext);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextEmail = findViewById(R.id.editTextEmail);
                EditText editTextPassword = findViewById(R.id.editTextPassword);

                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                Client client = new Client();
                Object object = client.sendMessage("INSERT INTO users VALUES ('" + email + "', '" + HashUtils.hashPassword(password) + "');");
                client.close();

                boolean bool = false;
                String error = "Default Error";
                if(object.getClass().equals(String.class)) { //Android studio no me deja meter un JDK para hacer instanceof
                    String string = (String)object;          //ME CAGO EN GOOGLE
                    System.out.println("!" + string + "!");
                    if(string.equals("This email already exists")) {
                        error = string;
                    } else if(string.equals("1")) {
                        bool = true;
                    }
                }

                if(bool) {
                    startActivity(new Intent(RegisterActivity.this, MainMenuActivity.class));
                    finish();
                }
                else
                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}