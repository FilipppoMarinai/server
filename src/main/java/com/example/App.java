package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            int numero = (int)(Math.random()*100);
            System.out.print("Numero da indovinare: " + numero);
            ServerSocket server = new ServerSocket(4000);
            Socket s = server.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            //prendo il numero
            String num = in.readLine();
            int numeroCLient = Integer.parseInt(num);
            int tentativi = 1;

            while(numeroCLient != numero){

                if(numeroCLient < numero){
                    out.writeByte(1);
                }
                else{
                    out.writeByte(2);
                }

                //prendo il numero
                num = in.readLine();
                numeroCLient = Integer.parseInt(num);
                tentativi++;
            }

            out.writeByte(3);
            out.writeByte(tentativi);

            s.close();
            server.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
