package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            //inizializzazione di una socket
            Socket s = new Socket("localhost", 3000);


            //apertura del stream input e output
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             boolean indovinato = false;

             Scanner scrivi = new Scanner(System.in);

            do {

                System.out.println("inserire un numero:");
                int val = scrivi.nextInt();

                out.writeBytes(val + "\n");

                int temp = Integer.parseInt(in.readLine());

                if(temp == 1){
                    System.out.println("il valore inserito è troppo piccolo");
                }else if (temp == 2) {
                    System.out.println("il valore inserito è troppo grande");
                }else{
                    System.out.println("indovinato");
                    System.out.println("Tentativi: "+Integer.parseInt(in.readLine()));
                    indovinato = true;
                }
                
                
            } while (!indovinato);
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
