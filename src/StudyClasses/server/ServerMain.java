package server;

import server.server.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain {

    public static void main(String[] args) {
        ServerSocket srvSocket = null;
        try {
            try {
                int i = 0; // Счётчик подключений

                InetAddress ia = InetAddress.getByName("localhost");
                srvSocket = new ServerSocket(Server.getPort(), 1, ia);

                System.out.println("Server started\n\n");

                while(true) {

                    Socket socket = srvSocket.accept();  // ожидание подключения
                    System.err.println("Client accepted");

                    // Стартуем обработку клиента
                    // в отдельном потоке
                    new Server().setSocket(i++, socket);
                }
            } catch(Exception e) {
                System.out.println("Exception : " + e);
            }
        } finally {
            try {
                if (srvSocket != null)
                    srvSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
