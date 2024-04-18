import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

    protected static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    protected Socket socket;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            clientHandlers.add(this);
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
			try {
				Object object = objectInputStream.readObject();
				handleObjects(object);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				close();
				break;
			}
		}
	}

	private void handleObjects(Object object) {
		if(object instanceof String string)
			handleSql(string);
		else if (object instanceof Exercise exercise)
			System.out.printf("Hacer algo");
	}

	private void handleSql(String sql) {
		System.out.println(sql);
		try {
			switch (sql.toUpperCase().charAt(0)) {
				case 'I':
					int res = DataBase.executeInsert(sql);
					System.out.println("The result from the insert is " + res);
					sendMessage(res + "");
					break;
				case 'S':
					if(sql.equals("SELECT * FROM exercises")){
						List<Exercise> exercises = DataBase.executeQueryExercises(sql);
						sendObject(exercises);
					} else {
						res = DataBase.executeQuery(sql);
						if(res == 0)
							sendMessage("Email or password incorrect");
						else
							sendMessage("Login accepted");
					}
					break;
			}
		} catch (SQLException e) {
			String error = e.getLocalizedMessage();
			System.out.println("pff" + e);
			if(error.substring(0, error.indexOf(' ')).equals("Duplicate")) {
				sendMessage("This email already exists");
			}
		}
	}

    protected void sendMessage(String message) {
		try {
			if(socket.isConnected()) {
				objectOutputStream.writeObject(message);
				objectOutputStream.flush();
			}
		} catch (IOException e) {
			close();
		}
    }

	protected void sendObject(Object message) {
		try {
			if(socket.isConnected()) {
				objectOutputStream.writeObject(message);
				objectOutputStream.flush();
			}
		} catch (IOException e) {
			close();
		}
	}

    protected void close() {
        try {
            if(objectOutputStream != null)
				objectOutputStream.close();
            if(objectInputStream != null)
                objectInputStream.close();
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
