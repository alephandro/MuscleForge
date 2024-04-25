package com.example.gym;

import com.example.gym.utils.NetworkVariables;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.example.gym.DataBase.backupPath;

public class Server {

    protected ServerSocket serverSocket;
	BackupHandler backupHandler;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {

		backupHandler = new BackupHandler();
		backupHandler.connectToBackupServer();
		backupHandler.loadDatabase();
		backupHandler.close();

		periodicBackup.start();

		try {
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client from " + clientSocket.getInetAddress());

                ClientHandler client = new ClientHandler(clientSocket);
                Thread clientThread = new Thread(client);
                clientThread.start();
            }
        } catch (IOException e) {
            closeServer();
        }
    }

    protected void closeServer() {
        try {
            if(serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	Thread periodicBackup = new Thread(new Runnable() {
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

                try {
                    NetworkVariables.updateDomain();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                backupHandler.connectToBackupServer();
				backupHandler.saveDatabase();
				backupHandler.close();
			}
		}
	});

    public static void main(String[] args) throws IOException {
		while(true){
            try {
                NetworkVariables.updateDomain();
                break;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
		Server server = new Server(new ServerSocket(8888));
		server.startServer();
    }
}
