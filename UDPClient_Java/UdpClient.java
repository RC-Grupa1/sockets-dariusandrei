import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) {
        String serverIp = "127.0.0.1";
        int port = 5101;

        // Poți rula:
        // java UdpClient
        // java UdpClient 127.0.0.1
        // java UdpClient 127.0.0.1 5101
        if (args.length >= 1) {
            serverIp = args[0];
        }

        if (args.length >= 2) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("[UDP CLIENT] Port invalid. Folosesc portul default 5101.");
                port = 5101;
            }
        }

        try (
            DatagramSocket socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)
        ) {
            InetAddress serverAddress = InetAddress.getByName(serverIp);

            System.out.println("[UDP CLIENT] Trimit catre serverul " + serverIp + ":" + port);
            System.out.println("[UDP CLIENT] Scrie mesaje. Scrie exit pentru inchidere.");

            while (true) {
                System.out.print("Client: ");
                String message = scanner.nextLine();

                byte[] sendBuffer = message.getBytes(StandardCharsets.UTF_8);

                DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer,
                    sendBuffer.length,
                    serverAddress,
                    port
                );

                socket.send(sendPacket);

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("[UDP CLIENT] Ai trimis exit. Inchid clientul.");
                    break;
                }

                byte[] receiveBuffer = new byte[1024];

                DatagramPacket receivePacket = new DatagramPacket(
                    receiveBuffer,
                    receiveBuffer.length
                );

                socket.receive(receivePacket);

                String reply = new String(
                    receivePacket.getData(),
                    0,
                    receivePacket.getLength(),
                    StandardCharsets.UTF_8
                ).trim();

                System.out.println("Server: " + reply);

                if (reply.equalsIgnoreCase("exit")) {
                    System.out.println("[UDP CLIENT] Serverul a trimis exit. Inchid clientul.");
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("[UDP CLIENT] Eroare: " + e.getMessage());
        }
    }
}