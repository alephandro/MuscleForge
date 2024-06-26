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

	protected void startServer() throws IOException {
		while(true) {
			try {
				Socket temp = listenerSocket.accept();
				this.serverSocket = temp;
				this.objectOutputStream = new ObjectOutputStream(serverSocket.getOutputStream());
				this.objectInputStream = new ObjectInputStream(serverSocket.getInputStream());
				break;
			} catch (IOException e) {
				close();
				throw e;
			}
		}
    }

	protected void handleObjects() throws IOException, ClassNotFoundException, RuntimeException {
		while(!serverSocket.isClosed()) {
			NetworkVariables.updateDomain();
			try {
				Object object = objectInputStream.readObject();
				if(object instanceof String string){
					System.out.println(string);
					switch(string.toLowerCase()) {
						case "send":
							sendBackups();
							break;
						case "receive":
							receiveBackups();
							break;
					}
				}
			} catch (IOException | ClassNotFoundException | RuntimeException e) {
				close();
				throw e;
			}
		}
	}

	protected void receiveBackups() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(backupPath);
			byte[] buffer = new byte[1024];
			int count;
			while((count = objectInputStream.read(buffer)) == 1024){
				fileOutputStream.write(buffer, 0, count);
			}
			fileOutputStream.write(buffer, 0, count);
			fileOutputStream.close();
		} catch (IOException | IndexOutOfBoundsException e) {
			throw new RuntimeException(e);
		}
	}

	protected void sendBackups() {
		try {
			int count;
			byte[] buffer = new byte[1024];
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(backupPath));
			while ((count = bufferedInputStream.read(buffer)) != -1) {
				objectOutputStream.write(buffer, 0, count);
				objectOutputStream.flush();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

    protected void close() throws IOException{
        try {
			System.out.println("Closing the connection");
			if(serverSocket != null)
				serverSocket.close();
            if(listenerSocket != null)
                listenerSocket.close();
        } catch (IOException e) {
			System.out.println(e);
			throw e;
        }
    }


    public static void main(String[] args) {
		BackupServer backupServer = null;
		while(true) {
			try {
				backupServer = null;
				NetworkVariables.updateDomain();
				backupServer = new BackupServer(new ServerSocket(NetworkVariables.BackupPort));
				backupServer.startServer();
				backupServer.handleObjects();
			} catch (IOException | ClassNotFoundException |RuntimeException e) {
				while (true){
					try {
						assert backupServer != null;
						backupServer.close();
						System.out.println("Restarting Backup Server");
						break;
					} catch (IOException ex) {}
				}
			}
		}
    }
}