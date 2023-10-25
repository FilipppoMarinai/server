package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    Socket s;

    public MioThread(Socket s){
        this.s = s;
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
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
