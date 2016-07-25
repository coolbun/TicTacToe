package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by rishabh.ja on 25/07/16.
 */
public class SocketCreate {

    public static ServerSocket skt;
    private Socket client;
    private BufferedReader bufferedReader;
    private PrintStream printStream;

    SocketCreate() throws IOException {
        client=skt.accept();
        bufferedReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
        printStream=new PrintStream(client.getOutputStream());
    }

    public String readfromclient() throws IOException {
        String buf=bufferedReader.readLine();
        if(buf!=null){
            System.out.println("Read from client successfully");
            return buf;
        }
        System.out.println("Unsuccessfull read");
        return "";
    }

    public void writetoclient(String msg){
        printStream.print(msg);
    }

}
