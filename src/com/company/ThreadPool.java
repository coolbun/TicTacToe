package com.company;

import java.io.IOException;

public class ThreadPool implements Runnable
{

    Player player_a,player_b;

    public void initPlayers(Player p1, Player p2) {
        player_a = p1;
        player_b = p2;
        new Thread(this, "myThread").start();
    }

    public void run(){
        try {
            Controller game=new Controller(player_a,player_b);
            player_a.currentGame=player_b.currentGame=game;
            game.startPlayers();
            game.UI();
        } catch (IOException e) {
            return;
        }
    }


}
