package com.example.gym.internet;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    protected static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    protected Socket socket;
    protected BufferedReader bufferedReader;
    protected BufferedWriter bufferedWriter;
    protected String username;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferedReader.readLine();
            clientHandlers.add(this);
            sendMessage("[SERVER]: " + username + " has joined the chat");
        } catch (IOException e) {
            close();
        }
    }

    @Override
    public void run() {
        while(socket.isConnected()) {
            try {
                String message = bufferedReader.readLine();
                sendMessage(message);
            } catch (IOException e) {
                close();
                break;
            }
        }
    }

    protected void sendMessage(String message) {
        for(ClientHandler clientHandler : clientHandlers) {
            try {
                if(!clientHandler.equals(this)) {
                    clientHandler.bufferedWriter.write(message);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                close();
            }
        }
    }

    protected void removeClientHandler() {
        clientHandlers.remove(this);
        sendMessage("[SERVER]: " + username + " has left the chat");
    }

    protected void close() {
        removeClientHandler();
        try {
            if(bufferedReader != null)
                bufferedReader.close();
            if(bufferedWriter != null)
                bufferedWriter.close();
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
