package com.example.gym;

import com.example.gym.utils.NetworkVariables;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    protected ServerSocket serverSocket;
	protected Socket backupSocket;
	protected ObjectOutputStream backupOutputStream;
	protected ObjectInputStream backupInputStream;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
		try {
			this.backupSocket = new Socket(NetworkVariables.BackupIP, NetworkVariables.BackupPort);
			this.backupOutputStream = new ObjectOutputStream(backupSocket.getOutputStream());
			this.backupInputStream = new ObjectInputStream(backupSocket.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		saveDatabase();

    }

    public void startServer() {
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

	private void saveDatabase() {
		String backupPath = DataBase.saveDatabase();
		try {
			int count;
			byte[] buffer = new byte[1024];
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(backupPath));
			while ((count = bufferedInputStream.read(buffer)) != -1) {
				backupOutputStream.write(buffer, 0, count);
				backupOutputStream.flush();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

    public static void main(String[] args) throws IOException {
        Server server = new Server(new ServerSocket(8888));
		server.startServer();
    }
}
