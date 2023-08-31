package controller;

import model.SerializedWrapper;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

public class SocketSender {
    Socket clientSocket;

    private static final int serverPort = 6666;
    private static final String localhost = "127.0.0.1";

    public SocketSender() {
        try {
            clientSocket = new Socket(InetAddress.getByName(localhost), serverPort);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void sendRequest(HashMap<String, Object> sendMap) {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            SerializedWrapper serializedMap = new SerializedWrapper(sendMap);
            objectOutputStream.writeObject(serializedMap);

        } catch (Exception e) {
            System.out.println("sender Exception : " + e);
        }
    }

    public HashMap<String, Object> catchRespond() {

        HashMap<String, Object> respondMap = new HashMap<>();
        SerializedWrapper serializedWrapper;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            serializedWrapper = (SerializedWrapper) objectInputStream.readObject();
            respondMap = serializedWrapper.getMapWrapper();

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
