package sources;

import sources.model.User;
import sources.workData.WorkData;

import java.io.IOException;
import java.net.InetSocketAddress;
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

        // Selector: multiplexor of SelectableChannel objects
        Selector selector = Selector.open(); // selector is open here

        // ServerSocketChannel: selectable channel for stream-oriented listening sockets
        ServerSocketChannel crunchifySocket = ServerSocketChannel.open();
        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);

        // Binds the channel's socket to a local address and configures the socket to listen for connections
        crunchifySocket.bind(crunchifyAddr);

        // Adjusts this channel's blocking mode.
        crunchifySocket.configureBlocking(false);
       crunchifySocket.register(selector, SelectionKey.OP_ACCEPT);

        int ops = crunchifySocket.validOps();
        SelectionKey selectKy = crunchifySocket.register(selector, ops, null);

        // Infinite loop..
        // Keep server running

        while (true) {
            String[] arr;

            WorkData workData = new WorkData();

            // Selects a set of keys whose corresponding channels are ready for I/O operations
            selector.select();

            // token representing the registration of a SelectableChannel with a Selector
            Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
            Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();

            while (crunchifyIterator.hasNext()) {
                SelectionKey myKey = crunchifyIterator.next();

                // Tests whether this key's channel is ready to accept a new socket connection
                if (myKey.isAcceptable()) {
                    SocketChannel crunchifyClient = crunchifySocket.accept();

                    // Adjusts this channel's blocking mode to false
                    crunchifyClient.configureBlocking(false);

                    // Operation-set bit for read operations
                    crunchifyClient.register(selector, SelectionKey.OP_READ);

                    // Tests whether this key's channel is ready for reading
                } else if (myKey.isReadable()) {

                    SocketChannel crunchifyClient = (SocketChannel) myKey.channel();
                    ByteBuffer crunchifyBuffer = ByteBuffer.allocate(256);
                    crunchifyClient.read(crunchifyBuffer);
                    String result = new String(crunchifyBuffer.array()).trim();


                    arr = result.split(" ");

                    if (result.equals("list")) {
                        crunchifyClient.write(ByteBuffer.wrap(getData(workData.users()).getBytes()));
                    } else if (result.equals("sum")) {
                        crunchifyClient.write(ByteBuffer.wrap((String.valueOf(workData.sum())).getBytes()));
                    } else if (result.equals("count")) {

                        crunchifyClient.write(ByteBuffer.wrap((String.valueOf(workData.count())).getBytes()));
                    } else if (arr[0].equals("add")) {
                        crunchifyClient.write(ByteBuffer.wrap(workData.add(new User(1,result)).getBytes()));
                    } else if (arr[0].equals("delete")) {
                        crunchifyClient.write(ByteBuffer.wrap((workData.delete(Long.valueOf(arr[1])).getBytes())));
                    } else if (arr[0].equals("show")) {
                        if (arr[1].equals("type")) {
                            crunchifyClient.write(ByteBuffer.wrap((workData.typeFind(String.valueOf(arr[2])).toString().getBytes())));
                        } else {
                            crunchifyClient.write(ByteBuffer.wrap((workData.titleFind(String.valueOf(arr[2])).toString().getBytes())));
                        }
                        if (arr[0].equals("info")) {
                            if (arr[1].equals("account")) {
                                crunchifyClient.write(ByteBuffer.wrap((workData.idFind(Long.valueOf(arr[2])).toString().getBytes())));
                            } else {
                                crunchifyClient.write(ByteBuffer.wrap((workData.deposotorFind(String.valueOf(arr[2])).toString().getBytes())));
                            }
                        }
                    }
                }
            }
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