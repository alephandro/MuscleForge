import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

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
				this.serverSocket = listenerSocket.accept();
				System.out.println(serverSocket.getInetAddress());

				this.objectOutputStream = new ObjectOutputStream(serverSocket.getOutputStream());
				this.objectInputStream = new ObjectInputStream(serverSocket.getInputStream());
				receiveBackups();
            }
        } catch (IOException e) {
            closeServer();
        }
    }

	public void receiveBackups() {
		while (serverSocket.isConnected()) {
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(backupPath);
				byte[] buffer = new byte[1024];
				int count;
				while((count = objectInputStream.read(buffer)) >= 0){
					fileOutputStream.write(buffer);
				}
				fileOutputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void sendBackups() {
		while (serverSocket.isConnected()) {
			try {
				objectOutputStream.writeObject(new File(backupPath));
				objectOutputStream.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

    protected void closeServer() {
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
    }
}
