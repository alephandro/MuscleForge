import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client{

    protected Socket socket;
    protected BufferedReader bufferedReader;
    protected BufferedWriter bufferedWriter;
    protected String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        } catch (IOException e) {
            close();
        }
    }

    public void sendMessages() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                bufferedWriter.write(username + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            close();
        }
    }

    public void receiveMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        String message = bufferedReader.readLine();
                        System.out.println(message);
                    } catch (IOException e) {
                        close();
                    }
                }
            }
        }).start();
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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What IP do you wish to connect to?" +
                " (\"localhost\" for own IP)");
        String ip = scanner.nextLine();
        System.out.println("What port do you wish to connect to?");
        int port = scanner.nextInt();
        System.out.println("What do you wish to call yourself?");
        String username = scanner.next();

        Socket socket = new Socket(ip, port);
        Client client = new Client(socket, username);
        client.receiveMessages();
        client.sendMessages();
    }
}
