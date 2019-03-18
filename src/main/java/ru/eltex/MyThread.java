/**
 * Класс MyThread
 * @author Дмитрий Городенцев
 * @version 1.0
 */

package ru.eltex;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyThread implements Runnable {
    Socket client;
    static int index = 0;

    public MyThread (Socket cl) {
        this.client = cl;
    }

    @Override
    public void run () {
        try {
            System.out.println("Thread " + ++index + " created ");

            OutputStream outStream = client.getOutputStream();
            PrintWriter out = new PrintWriter(outStream);

            FileReader reader = new FileReader("src/main/resources/page.html");
            Scanner scan = new Scanner(reader);

            String text = "";
            while (scan.hasNextLine()) {
                text += scan.nextLine();
             }
            System.out.println(text);

           String str = "HTTP/1.1 200 OK\n" +
           "Content-Language: ru\n" +
           "Content-Type: text/html; charset=utf-8\n" +
           "Content-Length: " + text.length() + "\n\n" + text;

            out.write(str);
            out.flush();
            System.out.println("server send a message to client\n\n");

            System.out.println("Closing connections & channels.");

            out.close();

            client.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


