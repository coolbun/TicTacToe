package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.RunnableFuture;

public class Main {
    public static void main(String[] args) throws IOException {
        SocketCreate.socket=new ServerSocket(9999);
        while (true) {
            System.out.println("Waiting for player " + (1) + " Connection...");
            Player player_a = new Player();
            System.out.println("Waiting for player " + (2) + " Connection...");
            Player player_b = new Player();
            ThreadPool gameThread = new ThreadPool();
            gameThread.initPlayers(player_a, player_b);
        }
    }
}
