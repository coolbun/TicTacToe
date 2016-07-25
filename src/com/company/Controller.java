package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 * Created by rishabh.ja on 22/07/16.
 */
class Controller
{
    Board board;
    Player player_a,player_b,currentplayer;

    Controller() throws IOException {

        board=new Board(3);

        SocketCreate.skt=new ServerSocket(9999);
        player_a=new Player();
        player_b=new Player();
        currentplayer=player_a;
    }

    public void UI() throws IOException {

        currentplayer.write("Enter move\n");
        if(currentplayer.equals(player_a))
            player_b.write("Waiting for "+currentplayer.getname()+" move\n");
        else
            player_a.write("Waiting for "+currentplayer.getname()+" move\n");

        String input=currentplayer.read();
        int x,y;x=y=0;

        try {
            String[] coordinates = input.split(" ");
            x = Integer.parseInt(coordinates[0]);
            y = Integer.parseInt(coordinates[1]);
        }
        catch(Exception e){
            currentplayer.write("Improper Coordinates entered");
        }


        boolean flag=false;

        flag=board.makemove(currentplayer.getchar(),x,y);

        board.print(player_a);
        board.print(player_b);


        //check result of the game
        if(board.findresult(currentplayer.getchar(),x,y)){
             currentplayer.write("Player "+ currentplayer.getname() + " wins\n");
            return;
        }

        //check if game is draw
        if(board.remainingmoves==0){
            player_a.write("Game ends in a draw\n");
            player_b.write("Game ends in a draw\n");
            return;
        }

        //set current player
        if(flag==true){
            if(currentplayer.equals(player_a))
                currentplayer=player_b;
            else
                currentplayer=player_a;
        }
        UI();
    }
}
