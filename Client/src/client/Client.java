/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client; 

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Sony
 */
public class Client {
    Socket socket;
    protected int porta = 1122;
    String nomeServer = "localhost";
    //stream per comunicare
    DataOutputStream out;
    BufferedReader in;

    Scanner input= new Scanner(System.in);

    public Socket connetti(){
        try {
            System.out.println("Client avviato..");
            //creo il socket per connettermi al server
            socket = new Socket(nomeServer, porta);
            System.out.println("Client connesso con successo al server ' "+nomeServer+" ' sulla porta: "+porta);
            //inizializzo gli stream per consentire la comunicazione
            out = new DataOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (Exception e){
            System.out.println("Errore, impossibile connettersi");
            System.exit(1);
        }
        return socket;

    }

    //menu per scelta operazioni
    private void menu(){
        try{
            System.out.println("---Calcolatrice---");
            System.out.println("[1.Somma \t 2.Differenza \t 3.Moltiplicazione \t 4.Divisione]");
        } catch (Exception e){
            System.out.println("Errore");
        }


    }

    //funzione per comunicare
    public void comunica(){
        try {
            int scelta=0;
                menu();
                System.out.println("Inserire operazione ");
                scelta = input.nextInt();
                //invio la scelta al server
                //out.writeByte(scelta);
                out.write(scelta);

                //Inserisco i numeri e li spedisco al server
                System.out.println("Inserisci primo numero.");
                double x = input.nextDouble();
                out.writeDouble(x);

                System.out.println("Inserisci secondo numero.");
                double y = input.nextDouble();
                out.writeDouble(y);
                
                System.out.println(x + "," + y);
           
        }catch (IOException e){

        }


    }

    public static void main(String args[]){
        Client myClient = new Client();
        myClient.connetti();
        myClient.comunica();


    }

}
