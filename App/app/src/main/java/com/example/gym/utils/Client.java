package com.example.gym.utils;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    protected Socket socket;
    protected ObjectInputStream objectInputStream;
    protected ObjectOutputStream objectOutputStream;

    public Client() {
        try {
            this.socket = new Socket(NetworkVariables.ServerDomain, NetworkVariables.Port);
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            close();
        }
    }

    public Object sendMessage(String message) {
        Object object = null;
        try {
            if(socket.isConnected()) {
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();

                object = objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            close();
        }
        return object;
    }

    private void handleObjects(Object object) {

    }

    public void sendMessages() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                objectOutputStream.writeObject(": " + message + "\n");
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            close();
        }
    }

    public void receiveObjects() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isConnected()) {
                    try	{
                        Object object = objectInputStream.readObject();
                        handleObjects(object);
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void close() {
        try {
            if(objectInputStream != null)
                objectInputStream.close();
            if(objectOutputStream != null)
                objectOutputStream.close();
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
