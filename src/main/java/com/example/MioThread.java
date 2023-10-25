package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class MioThread extends Thread{
    Socket s;
    ArrayList client;

    public MioThread(Socket s, ArrayList client){
        this.s = s;
        this.client = client;
    }

    public void run(){
        try{
            int numero = (int)(Math.random()*100);
            System.out.print("Numero da indovinare: " + numero);

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

            try{
                System.out.println("Il client ha vinto");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
