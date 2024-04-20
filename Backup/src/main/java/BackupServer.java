import utils.NetworkVariables;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BackupServer {

    protected ServerSocket listenerSocket;
	protected Socket serverSocket;
	protected ObjectOutputStream objectOutputStream;
	protected ObjectInputStream objectInputStream;
	protected String backupPath = "backup.sql";

    public BackupServer(ServerSocket listenerSocket) {
        this.listenerSocket = listenerSocket;
    }

    public void startServer() {
        try {
            while (!listenerSocket.isClosed()) {
				Socket temp = listenerSocket.accept();
				if(temp.getInetAddress().toString().equals(NetworkVariables.ServerIP))
					this.serverSocket = temp;

				this.objectOutputStream = new ObjectOutputStream(serverSocket.getOutputStream());
				this.objectInputStream = new ObjectInputStream(serverSocket.getInputStream());
				receiveBackups();
            }
        } catch (IOException e) {
            stopListening();
        }
    }

	protected void receiveBackups() {
		while (serverSocket.isConnected()) {
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(backupPath);
				byte[] buffer = new byte[1024];
				int count;
				while((count = objectInputStream.read(buffer)) != -1){
					fileOutputStream.write(buffer, 0, count);
				}
				fileOutputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected void sendBackups() {
		while (serverSocket.isConnected()) {
			try {
				objectOutputStream.writeObject(new File(backupPath));
				objectOutputStream.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void closeConnection() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

    protected void stopListening() {
        try {
            if(listenerSocket != null)
                listenerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        BackupServer backupServer = new BackupServer(new ServerSocket(9999));
        backupServer.startServer();
		backupServer.receiveBackups();
    }
}
