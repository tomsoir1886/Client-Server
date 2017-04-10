package sources;

import sources.model.User;
import sources.model.Type;
import sources.writh.Writh;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tomsoir on 24.03.2017.
 */
public class MainClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        log("Connecting to Server on port 1111...");
        Scanner sc = new Scanner(System.in);
        String line = null;

        Client client = new Client("127.0.0.1",1111);
        try {
            while (true) {
                menu();
                line = sc.nextLine();
                line = line.toLowerCase();
                int statusClient = client.setCommand(line);
                if (statusClient == client.CLIENT_EXIT) {
                    client.wr(line);
                    break;
                }
                if (statusClient == client.CLIENT_INPUT) {
                    String input = "";
                    boolean checkerInput = false;
                    System.out.println("Input name of the bank");
                    do {
                        line = sc.nextLine().trim();
                        if (line.length() > 0) {
                            input += line.replace(" ", "_") + " ";
                            checkerInput = false;
                        } else {
                            checkerInput = true;
                            System.out.println("Error. \nExample: Bank \nRepeat input");
                        }
                    } while (checkerInput);
                    System.out.println("Input country");
                    do {
                        line =sc.nextLine().trim();
                        if (line.length() > 0) {
                            input += line.replace(" ", "_") + " ";
                            checkerInput = false;
                        } else {
                            checkerInput = true;
                            System.out.println("Error. \nExample: Country \nRepeat input");
                        }
                    } while (checkerInput);
                    System.out.println("Choose type(Input number)");
                    for (int i = 0; i < Type.values().length; i++) {
                        System.out.println(i + " " + Type.values()[i].getType());
                    }

                    do {
                        line = sc.nextLine();
                        if (Writh.checkInt(line) && line.length() > 0) {
                            input += Type.values()[Integer.valueOf(line)].getType().trim().replace(" ", "_") + " ";
                            checkerInput = false;
                        } else {
                            checkerInput = true;
                            System.out.println("Error. \nExample: 1 \nRepeat input");
                        }
                    } while (checkerInput);
                    System.out.println("Input name of depositor");

                    do {
                        line = sc.nextLine();
                        if (Writh.checkFullName(line) && line.length() > 0) {
                            input += line.trim().replace(" ", "_") + " ";
                            checkerInput = false;
                        } else {
                            checkerInput = true;
                            System.out.println("Error. \nExample: Silvana Mehrin\nRepeat input");
                        }
                    } while (checkerInput);
                    System.out.println("Input account Id");
                    do {
                        line = sc.nextLine();
                        if (Writh.checkInt(line)) {
                            input += line + " ";
                            checkerInput = false;
                        } else {
                            checkerInput = true;
                            System.out.println("Error. \nExample: 12345\nRepeat input");
                        }
                    } while (checkerInput);
                    System.out.println("Input amount on deposit");
                    do {
                        line = sc.nextLine();
                        if (Writh.checkInt(line)) {
                            input += line.trim().replace(" ", "_") + " ";
                            checkerInput = false;
                        } else {
                            checkerInput = true;
                            System.out.println("Error. \nExample: 9999\nRepeat input");
                        }
                    } while (checkerInput);
                    System.out.println("Input profitability");
                    do {
                        line = sc.nextLine();
                        if (Writh.checkDouble(line)) {
                            input += line + " ";
                            checkerInput = false;
                        } else {
                            checkerInput = true;
                            System.out.println("Error. \nExample: 0.5\nRepeat input");
                        }
                    } while (checkerInput);
                    System.out.println("Input time constraints");
                    do {
                        line = sc.nextLine();
                        if (Writh.checkDate(line)) {
                            input += line + " ";
                            checkerInput = false;
                        } else {
                            checkerInput = true;
                            System.out.println("Error. \nExample: 2017-03-23\nRepeat input");
                        }
                    } while (checkerInput);
                    client.setCommand(input);
                }
                if (statusClient == client.CLIENT_ERROR_COMMAND) {
                    System.out.println("Error input command");
                    continue;
                }
                if (statusClient == client.CLIENT_SEND) {
                    System.out.println("Sending request to the server");
                }
                System.out.println("The server was very polite. It sent me this : " + client.read());
                System.out.println();
                Thread.sleep(3000);
            }
            client.finishConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void log(String str) {
        System.out.println(str);
    }

    public static void menu() {
        System.out.println("------------— MENU —--------------------");
        System.out.println("Print line");
        System.out.println("1. list");
        System.out.println("2. sum");
        System.out.println("3. count");
        System.out.println("4. info account <account id>");
        System.out.println("5. info depositor <depositor>");
        System.out.println("6. show type <type>");
        System.out.println("7. show bank <name>");
        System.out.println("8. add <deposit info>");
        System.out.println("9. delete <account id>");
        System.out.println("10. exit");
        System.out.println("-------------------------------------------");
    }


}
