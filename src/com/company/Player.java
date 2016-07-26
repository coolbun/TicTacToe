package com.company;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by rishabh.ja on 25/07/16.
 */
public class Player implements Runnable {
    private String name, movechar;
    SocketCreate socket;
    public Controller currentGame;
    volatile public String message;
    Thread t;

    Player() throws IOException {
        socket = new SocketCreate();
        this.write("Enter the name of the player ");
        name = this.read();
        this.write("Enter the character of your choice");
        movechar = this.read();
        message = "Game started\n";
    }

    public void write(String msg) {
        socket.writetoclient(msg);
    }

    public void setname(String msg) {
        name = msg;
    }

    public String read() throws IOException {
        return socket.readfromclient();
    }

    public void endconnection() throws IOException {
        Thread.currentThread().interrupt();
        socket.close();

    }

    public String getname() {
        return name;
    }

    public String getchar() {
        return movechar;
    }


    @Override
    public void run() {
        while (true) {
            try {
                if(currentGame.getresult())
                    break;
                String input = socket.readfromclient();
                if (currentGame.getcurrentplayer().equals(this) == true) {
                    currentGame.flag = 1;
                    currentGame.message = input;
                } else {
                    socket.writetoclient("Not your move!!\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        t = new Thread(this, "Thread_player");
        t.start();
    }
}
