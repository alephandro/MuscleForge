package com.example.gym;

import com.example.gym.utils.NetworkVariables;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.example.gym.DataBase.backupPath;

public class BackupHandler {

	protected Socket backupSocket;
	protected ObjectOutputStream backupOutputStream;
	protected ObjectInputStream backupInputStream;

	public void connectToBackupServer() {
		int timeout = 0;
		while(true) {
			try {
				timeout = 5;
				this.backupSocket = new Socket();
				this.backupSocket.connect(new InetSocketAddress(NetworkVariables.BackupDomain, NetworkVariables.BackupPort),
						5000);
				timeout = 0;
				this.backupOutputStream = new ObjectOutputStream(backupSocket.getOutputStream());
				InputStream is = backupSocket.getInputStream();
				this.backupInputStream = new ObjectInputStream(is);
				break;
			} catch (IOException e) {
				try {
					timeout += 5;
					System.out.println("Retrying the connection in " + timeout + "s");
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

	public void close() {
		try {
			System.out.println("Closing the connection");
			if(backupSocket != null)
				backupSocket.close();
			if(backupSocket != null)
				backupOutputStream.close();
			if(backupSocket != null)
				backupInputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void saveDatabase() {
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

	public void loadDatabase() {
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

}
