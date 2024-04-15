import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    protected ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What port do you wish to host out of?");
        ServerSocket serverSocket = new ServerSocket(scanner.nextInt());
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
