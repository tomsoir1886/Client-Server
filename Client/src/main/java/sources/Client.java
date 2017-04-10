package sources;

import sources.model.User;

import java.io.IOException;
import java.net.InetSocketAddress;
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

    public final int CLIENT_EXIT = 0;
    public final int CLIENT_SEND = 1;
    public final int CLIENT_INPUT = 2;
    private int currentState;
    public final int CLIENT_ERROR_COMMAND = 3;


    public Client() {
        crunchifyAddr = new InetSocketAddress("localhost", 1111);
        try {
            crunchifyClient = SocketChannel.open(crunchifyAddr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wr(String line) {
        ByteBuffer buffer = ByteBuffer.wrap(line.getBytes());
        try {
            crunchifyClient.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.clear();
    }

    public boolean statusConnect() {
        return crunchifyClient.isOpen();
    }

    public String read() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        int bytesRead = 0;
        try {
            bytesRead = crunchifyClient.read(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byteBuffer.flip();
        byte[] lineBytes = new byte[bytesRead];
        byteBuffer.get(lineBytes, 0, bytesRead);
        byteBuffer.clear();
        return new String(lineBytes);
    }

    public void finishConnection() throws IOException {
        crunchifyClient.close();
    }
    

    public int setCommand(String command) throws IOException {
        if (command.equals("exit")) {
            currentState = CLIENT_EXIT;
            return CLIENT_EXIT;
        } else if (command.equals("list") || command.equals("sum") ||
                command.equals("count") || command.contains("info account") ||
                command.contains("info depositor") || command.contains("show type") ||
                command.contains("show bank") || command.contains("delete")) {
            wr(command);
            currentState = CLIENT_SEND;
            return CLIENT_SEND;
        } else if (command.equals("add")) {
            currentState = CLIENT_INPUT;
            return CLIENT_INPUT;
        } else if (currentState == CLIENT_INPUT) {
            wr("add " + new User(0, command).toString());
            currentState = CLIENT_SEND;
            return CLIENT_SEND;
        }
        return CLIENT_ERROR_COMMAND;
    }
}