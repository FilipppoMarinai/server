package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            ServerSocket server = new ServerSocket(4000);
            ArrayList client = new ArrayList();

            do{
                Socket s = server.accept();

                MioThread thread = new MioThread(s, client);
                thread.client = client;
                thread.start();
            }
            while(true);


        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
