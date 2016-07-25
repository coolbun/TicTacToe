package com.company;

import java.io.IOException;

/**
 * Created by rishabh.ja on 25/07/16.
 */
public class Player {
    private String name,movechar;
    SocketCreate skt;

    Player() throws IOException {
        skt=new SocketCreate();
        this.write("Enter the name of the player ");
        name=this.read();
        this.write("Enter the character of your choice");
        movechar=this.read();
    }

    public void write(String msg){
        skt.writetoclient(msg);
    }

    public void setname(String msg){
        name=msg;
    }

    public String read() throws IOException {
        return skt.readfromclient();
    }

    public String getname() {
        return name;
    }

    public String getchar() {
        return movechar;
    }
}
