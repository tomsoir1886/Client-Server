package sources;

import sources.model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Client {
    InetSocketAddress crunchifyAddr;
    SocketChannel crunchifyClient;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    public final int CLIENT_EXIT = 0;
    public final int CLIENT_SEND = 1;
    public final int CLIENT_INPUT = 2;
    private int currentState;
    public final int CLIENT_ERROR_COMMAND = 3;


    public Client(String host, int port) {

        try {
            socket = new Socket(InetAddress.getByName(host), port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        crunchifyAddr = new InetSocketAddress("127.0.0.1", 1111);
        try {
            crunchifyClient = SocketChannel.open(crunchifyAddr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wr(String line) {
        try {
            out.writeUTF(line);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean statusConnect() {
        return crunchifyClient.isOpen();
    }

    public String read() {
        String s = "Error read";
        try {
            s = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public void finishConnection() throws IOException {
        crunchifyClient.close();
    }


    public int setCommand(String command) throws IOException {
        if (command.equals("exit")) {
            currentState = CLIENT_EXIT;
            return CLIENT_EXIT;
        }
        if (command.equals("list") || command.equals("sum") ||
                command.equals("count") || command.contains("info account") ||
                command.contains("info depositor") || command.contains("show type") ||
                command.contains("show bank") || command.contains("delete")) {
            wr(command);
            currentState = CLIENT_SEND;
            return CLIENT_SEND;
        }
        if (command.equals("add")) {
            currentState = CLIENT_INPUT;
            return CLIENT_INPUT;
        }
        if (currentState == CLIENT_INPUT) {
            wr("add " + new User(0, command).toString());
            currentState = CLIENT_SEND;
            return CLIENT_SEND;
        }
        return CLIENT_ERROR_COMMAND;
    }
}