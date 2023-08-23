package controller;

import entity.SerializedEntity;

import java.io.*;
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
            throw new RuntimeException(e);
        }
    }

    public void sender(HashMap<String, Object> sendMsg) {

        try {
            OutputStream south = clientSocket.getOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(south);

            SerializedEntity data = new SerializedEntity(sendMsg);
            objectOutputStream.writeObject(data);

        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }

    public HashMap<String, Object> catcher() {

        HashMap<String, Object> receivedData = new HashMap<>();

        SerializedEntity ser;
        try {

            InputStream sin = clientSocket.getInputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(sin);

            ser = (SerializedEntity) objectInputStream.readObject();

            receivedData = ser.sendMsg;
        } catch (Exception e) {
        }
        return receivedData;
    }
}
