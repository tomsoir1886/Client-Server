package sources;

import sources.model.User;
import sources.workData.WorkData;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class MainServer {
    public static void main(String[] args) throws IOException, InterruptedException {

        int port = 1111; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            String[] arr = null;

            while (true) {
                WorkData workData = new WorkData();
                String result = in.readUTF().trim();
                System.out.println("The dumb client just sent me this line : " + result);
                arr = result.split(" ");
                if (result.equals("exit")) {
                    break;
                } else if (result.equals("list")) {
                    out.writeUTF(getData(workData.users())); // отсылаем клиенту обратно ту самую строку текста.
                    out.flush();
                } else if (result.equals("sum")) {
                    out.writeUTF(String.valueOf(workData.sum()));
                    out.flush();
                } else if (result.equals("count")) {
                    out.writeUTF(String.valueOf(workData.count()));
                    out.flush();
                } else if (arr[0].equals("add")) {
                    out.writeUTF(workData.add(new User(1, result)));
                    out.flush();
                } else if (arr[0].equals("delete")) {
                    out.writeUTF(workData.delete(Long.valueOf(arr[1])));
                    out.flush();
                } else if (arr[0].equals("show")) {
                    if (arr[1].equals("type")) {
                        out.writeUTF(workData.typeFind(String.valueOf(arr[2])).toString());
                        out.flush();
                    } else {
                        out.writeUTF(workData.titleFind(String.valueOf(arr[2])).toString());
                        out.flush();
                    }
                }
                if (arr[0].equals("info")) {
                    if (arr[1].equals("account")) {
                        out.writeUTF(workData.idFind(Long.valueOf(arr[2])).toString());
                        out.flush();
                    } else {
                        out.writeUTF(workData.deposotorFind(String.valueOf(arr[2])).toString());
                        out.flush();
                    }
                }

            }

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private static String getData(User user) {
        String str = "";
        if (user != null) {
            str += user.toString();
        } else {
            str = "Missing data";
        }
        return str;
    }

    private static String getData(ArrayList<User> users) {
        String str = " ";
        if (users != null) {
            for (User user : users) {
                str += users;
            }
        } else {
            str = "Missing data";
        }
        return str;
    }
}