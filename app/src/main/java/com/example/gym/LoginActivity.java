package com.example.gym;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gym.internet.Client;

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
                // Aquí puedes agregar la lógica para validar el inicio de sesión
                // Y luego pasar a la siguiente actividad si las credenciales son correctas
                // Por ejemplo:
                //Intent intent = new Intent(LoginActivity.this, NextActivity.class);
                //startActivity(intent);

                try {
                    Socket socket = new Socket("88.27.144.170", 3306);
                    Client client = new Client(socket, "MuscleUser");
                    client.sendMessage("RAAAAAAAAAAH!");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                /*DataBase.connectAndExecuteSQL("INSERT INTO usuarios" +
                        "                           VALUES('luis', 51123546C)");*/
            }
        });
    }
}
