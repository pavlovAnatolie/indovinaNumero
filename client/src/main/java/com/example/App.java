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
            DataOutputStream out = new DataOutputStream(s.getOutputStream());//output
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));//input

            //booleano che ferma il dowhile quando il numeor viene indovinato
             boolean indovinato = false;


             //inizializzazione della classe scanner per poter scrivere da tastiera 
             Scanner scrivi = new Scanner(System.in);
             System.out.println("il dispositivo è stato collegato");



             //ciclo per la lettura dei numeri
            do {


                System.out.println("inserire un numero:");
                //prendo il valore dalla tastiera
                int val = scrivi.nextInt();

                //invio il valore sullo stream
                out.writeBytes(val + "\n");

                //prendo il valore (elaborato) dal server
                int temp = Integer.parseInt(in.readLine());

                //eseguo i vari controlli per vedere la corretezza
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
            //CHIUDO LA SOCKET
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
