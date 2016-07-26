package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 * Created by rishabh.ja on 22/07/16.
 */
class Controller {
    Board board;
    Player player_a, player_b, currentplayer;
    volatile int flag;
    volatile String message;
    volatile boolean gameresult;

    public boolean getresult() {
        return gameresult;
    }

    Controller(Player player_a, Player player_b) throws IOException {

        board = new Board(3);
        this.player_a = player_a;
        this.player_b = player_b;
        currentplayer = player_a;
        flag = 0;
        gameresult = false;
        message = null;
    }


    Controller() throws IOException {

        board = new Board(3);
        SocketCreate.socket = new ServerSocket(9999);
        player_a = new Player();
        player_b = new Player();
        currentplayer = player_a;
        player_a.run();
        player_b.run();
    }

    public void startPlayers() {
        player_a.start();
        player_b.start();
    }

    public Player getcurrentplayer() {
        return currentplayer;
    }

    private void closeGame() throws IOException {
        player_a.endconnection();
        player_b.endconnection();
    }

    public void UI() throws IOException {
        currentplayer.write("Enter move\n");
        if (currentplayer == player_a)
            player_b.write("Waiting for " + currentplayer.getname() + " move\n");
        else
            player_a.write("Waiting for " + currentplayer.getname() + " move\n");

        while (true) {
            String input = "";
            boolean flag1 = false;

            if (flag == 1) {
                flag = 0;
                input = message;
                int x, y;
                x = y = 0;

                try {
                    String[] coordinates = input.split(" ");
                    x = Integer.parseInt(coordinates[0]);
                    y = Integer.parseInt(coordinates[1]);
                } catch (Exception e) {
                    currentplayer.write("Improper Coordinates entered");
                }
                flag1 = board.makemove(currentplayer.getchar(), x, y);
                board.print(player_a);
                board.print(player_b);
                if (board.findresult(currentplayer.getchar(), x, y)) {
                    currentplayer.write("Player " + currentplayer.getname() + " wins\n");
                    gameresult = true;
                    closeGame();
                    return;
                }
            } else
                continue;

            //check if game is draw
            if (board.remainingmoves == 0) {
                player_a.write("Game ends in a draw\n");
                player_b.write("Game ends in a draw\n");
                gameresult = true;
                closeGame();
                return;
            }

            //set current player
            if (flag1 == true) {
                if (currentplayer.equals(player_a))
                    currentplayer = player_b;
                else
                    currentplayer = player_a;
            }

            currentplayer.write("Enter move\n");
            if (currentplayer == player_a)
                player_b.write("Waiting for " + currentplayer.getname() + " move\n");
            else
                player_a.write("Waiting for " + currentplayer.getname() + " move\n");
        }
    }
}
