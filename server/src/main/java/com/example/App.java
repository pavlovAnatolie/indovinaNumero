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
            // metto il server in ascolto sulla porta 3000 per poter acquisire e creare la socket
           ServerSocket server = new ServerSocket(3000);

           System.out.println("il server è in ascolto");
           Socket s = server.accept();
           System.out.println("il dispositivo è stato collegato");

           //apertura del stream input e output
           BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));//input
           DataOutputStream out = new DataOutputStream(s.getOutputStream());//output

           //genero un valore casulamente da 1 a 100
           int ranDom = (int)Math.floor(Math.random() *(100- 1 + 1) + 1);
           System.out.println("il numero generato dal server è: "+ ranDom);
           boolean mac= false;


                //metto un contatore per tracciare i tentativi
                int tentativi = 0;
                do {
                    //aumento il valore con ogni tentativo che faccio
                    tentativi++;

                    //prendo il numero inserito dal utente sullo stream e lo converto
                    String num = in.readLine();
                    int numero = Integer.parseInt(num);
                    System.out.println("cliet->"+numero);

                    //controllo il vaolore e ritorno il resoconto del resultato relativo
                    if(numero < ranDom){
                        out.writeBytes(1+"\n");
                        System.out.println("server->"+1);
                    }else if(numero > ranDom){
                        out.writeBytes(2+"\n");
                        System.out.println("server->"+2);
                    }else{
                        out.writeBytes(3+"\n");
                        out.writeBytes(tentativi+"\n");
                        System.out.println("server->"+3);
                        System.out.println("server->"+tentativi);
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
