import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
                if(message == null)
                    throw new IOException();

                System.out.println("el mensaje es: " + message);
				handleSql(message);

                //sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                close();
                break;
            }
        }
    }

	private void handleSql(String sql) {

		try {
			switch (sql.toUpperCase().charAt(0)) {
				case 'I':
					int res = DataBase.executeInsert(sql);
					System.out.println("The result from the insert is " + res);
					break;
				case 'S':
					res = DataBase.executeQuery(sql);
					if(res == 0)
						System.out.println("Email or password incorrect (implement)");
					else
						System.out.println("Login accepted (implement)");
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
			bufferedWriter.write(message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			close();
		}
    }

    protected void close() {
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
