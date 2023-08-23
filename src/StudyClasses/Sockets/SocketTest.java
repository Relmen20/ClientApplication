package StudyClasses.Sockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class Server extends Thread {
    private static final int port = 12345;
    private Socket socket;
    private int num;

}

public class SocketTest {

    public static void main(String[] args) throws IOException {

        try {
            InetAddress ia = InetAddress.getByName("localhost");
            ServerSocket srv = new ServerSocket(0);
        } finally {

        }

    }

}

//    ServerSocket srvSocket = null;
//        try {
//                try {
//                int i = 0; // Счётчик подключений
//                // Подключение сокета к localhost
//                InetAddress ia;
//                ia = InetAddress.getByName("localhost");
//                srvSocket = new ServerSocket(port, 0, ia);
//
//                System.out.println("Server started\n\n");
//
//                while(true) {
//                // ожидание подключения
//                Socket socket = srvSocket.accept();
//                System.err.println("Client accepted");
//                // Стартуем обработку клиента
//                // в отдельном потоке
//                new Server().setSocket(i++, socket);
//                }
//                } catch(Exception e) {
//                System.out.println("Exception : " + e);
//                }
//                } finally {
//                try {
//                if (srvSocket != null)
//                srvSocket.close();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }
//                }
//                System.exit(0);
