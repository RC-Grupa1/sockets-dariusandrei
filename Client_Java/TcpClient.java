import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) {
        String serverIp = "127.0.0.1";
        int port = 5100;

        if (args.length >= 1) {
            serverIp = args[0];
        }

        try (
            Socket socket = new Socket(serverIp, port);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
            );
            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
            );
            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)
        ) {
            System.out.println("[TCP CLIENT] Conectat la serverul " + serverIp + ":" + port);

            while (true) {
                System.out.print("Client: ");
                String message = scanner.nextLine();

                out.write(message);
                out.newLine();
                out.flush();

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("[TCP CLIENT] Ai trimis exit. Închid conexiunea.");
                    break;
                }

                String reply = in.readLine();

                if (reply == null) {
                    System.out.println("[TCP CLIENT] Serverul a închis conexiunea.");
                    break;
                }

                System.out.println("Server: " + reply);

                if (reply.equalsIgnoreCase("exit")) {
                    System.out.println("[TCP CLIENT] Serverul a trimis exit. Închid conexiunea.");
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("[TCP CLIENT] Eroare: " + e.getMessage());
        }
        
        
        
        
        
        
    }
}