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
           ServerSocket server = new ServerSocket(3000);

           System.out.println("il server è in ascolto");
           Socket s = server.accept();

           BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
           DataOutputStream out = new DataOutputStream(s.getOutputStream());

           int ranDom = (int)Math.floor(Math.random() *(100- 1 + 1) + 1);
           System.out.println("il numero generato dal server è: "+ ranDom);
           boolean mac= false;

                int volte = 0;
                do {
                    volte++;
                    String num = in.readLine();
                    int numero = Integer.parseInt(num);


                    if(numero < ranDom){
                        out.writeBytes(1+"\n");
                    }else if(numero > ranDom){
                        out.writeBytes(2+"\n");
                    }else{
                        out.writeBytes(3+"\n");
                        out.writeBytes(volte+"\n");
                        mac = true;
                    }
                } while (!mac);

                server.close();
                s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
