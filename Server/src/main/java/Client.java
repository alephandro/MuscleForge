import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    protected Socket socket;
    protected ObjectInputStream objectInputStream;
    protected ObjectOutputStream objectOutputStream;
    protected String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.username = username;
            sendMessage(username);
        } catch (IOException e) {
            close();
        }
    }

    public void sendMessage(String message) {
        try {
            if(socket.isConnected()) {
				objectOutputStream.writeObject(message + "\n");
				objectOutputStream.flush();
            }
        } catch (IOException e) {
            close();
        }
    }

    public void sendMessages() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
				objectOutputStream.writeObject(username + ": " + message + "\n");
				objectOutputStream.flush();
            }
        } catch (IOException e) {
            close();
        }
    }

	@Override
	public void run() {
		receiveObjects();
	}

	private void receiveObjects() {
		while(socket.isConnected()) {
			try	{
				Object object = objectInputStream.readObject();
				handleObjects(object);
			} catch (IOException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void handleObjects(Object object) {

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

    public static void main(String[] args) throws IOException {

    }
}
