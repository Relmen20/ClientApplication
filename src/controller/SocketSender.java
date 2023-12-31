package controller;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

public class SocketSender {
    Socket clientSocket;
    private static final String localhost = "127.0.0.1";

    public SocketSender(int serverPort) {
        try {
            clientSocket = new Socket(InetAddress.getByName(localhost), serverPort);
        } catch (Exception e) {
            System.out.println("Exception " + e);
            throw new RuntimeException();
        }
    }

    public void sendRequest(HashMap<String, Object> sendMap) {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutputStream.writeObject(sendMap);
            objectOutputStream.flush();
        } catch (Exception e) {
            System.out.println("sender Exception : " + e);
        }
    }

    public HashMap<String, Object> catchRespond() {

        HashMap<String, Object> respondMap = new HashMap<>();

        try{
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            respondMap = (HashMap<String, Object>) objectInputStream.readObject();

        } catch (Exception e) {
            System.out.println("catcher Exception : " + e);
        }
        return respondMap;
    }

    public void stopAllProcess() {
        try {
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Socket error");
        }
    }
}
