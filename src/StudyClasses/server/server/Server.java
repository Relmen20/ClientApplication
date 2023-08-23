package server.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Server extends Thread {

    private static final int port = 6666;
    private String TEMPL_MSG = "The client '%d' sent me message : \n\t";
    private String TEMPL_CONN = "The client '%d' closed the connection";

    private Socket socket;
    private int num;

    public Server() {
    }

    public void setSocket(int num, Socket socket) {

        this.num = num;
        this.socket = socket;

        setDaemon(true); // Установка daemon-потока

        start();
    }

    public void run() {
        try {

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream dis = new DataInputStream(sin);
            DataOutputStream dos = new DataOutputStream(sout);

            String line = null;
            while (true) {

                line = dis.readUTF(); // Ожидание сообщения от клиента

                System.out.println(String.format(TEMPL_MSG, num) + line);
                System.out.println("I'm sending it back...");

                dos.writeUTF("Server receive text : " + line);

                dos.flush(); // Завершаем передачу данных
                System.out.println();

                if (line.equalsIgnoreCase("quit")) {
                    socket.close();  // завершаем соединение
                    System.out.println(
                            String.format(TEMPL_CONN, num));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }



    public static int getPort(){
        return port;
    }
}
