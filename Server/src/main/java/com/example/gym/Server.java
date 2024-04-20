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
		loadDatabase();
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
			backupOutputStream.writeObject("receive");
			int count;
			byte[] buffer = new byte[1024];
			FileInputStream fileInputStream = new FileInputStream(backupPath);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			while ((count = bufferedInputStream.read(buffer)) != -1) {
				backupOutputStream.write(buffer, 0, count);
				backupOutputStream.flush();
			}
			fileInputStream.close();

			Files.delete(Paths.get(backupPath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Database saved");
	}

	private void loadDatabase() {
		try {
			backupOutputStream.writeObject("send");
			FileOutputStream fileOutputStream = new FileOutputStream(backupPath);
			byte[] buffer = new byte[1024];
			int count;
			while((count = backupInputStream.read(buffer)) == 1024){
				fileOutputStream.write(buffer, 0, count);
			}
			fileOutputStream.write(buffer, 0, count);
			fileOutputStream.close();

			DataBase.loadDatabase();
			Files.delete(Paths.get(backupPath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

    public static void main(String[] args) throws IOException {
        Server server = new Server(new ServerSocket(8888));
		server.startServer();
    }
}
