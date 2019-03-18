/**
 * Клиент-серверное приложение
 * @author Дмитрий Городенцев
 * @version 1.0
 */

package ru.eltex;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main (String[] args) {

        try (ServerSocket server = new ServerSocket(8080)){
            server.setSoTimeout(30000);
            while (true) {
                Socket client = server.accept();
                Runnable r = new MyThread(client);
                Thread t = new Thread(r);
                t.start();
                System.out.println("Connection accepted");

            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


/*
    13.03

      ( Spring Boot ) backend     <->     frontend
        html, REST

                      DB(MySQL, mongo)    JQuery, Angular


        http://192.168.0.102 : 8080/getusers
                                   /getfio
                                         |
                                      интерфейсы

        http(TCP) {get, post}
                                GET(255 символов) / get_users
                                      http/1.0

                                POST(передача)

        ServerSocket server = new ServerSocket(8080);
        while(true) {
            Socket client = server.accept();
            Runnable r = new MyThread(client);
            Thread t = new Thread(r);
            t.start();
        }

        class MyThread implements Runnable {
            public MyThread(Socket client) {
                this.client = client;
            }
            public void run() {
                InputStream inStream = this.client.getInputStream();
                OutputStream outStream = this.client.getOutputStream();

                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream);
                out.write("hello");
                out.flush();
            }
        }



        вопросы как убить процесс, доки...


        ДЗ: 1) клиент сервер + потоки
            2) веб-сервер
                вместо клиента браузер

            3) метод get_users - выдать всех пользователей, передать текстом пользователей с бд
        подумать над функционалом телефонной книги или проекта

        travis

     */

