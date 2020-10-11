/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

//port java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Sony
 */
public class Server {
    ServerSocket server;
    Socket msocket;
    DataInputStream in;
    DataOutputStream out;
    public Socket attendi() {
        try {
            //creo il server sulla porta ****
            System.out.println("Server in esecuzione..");
            server = new ServerSocket(1122);

            //accetto eventuale connessione da parte del client
            msocket = server.accept();
            System.out.println("Client connesso con successo! ");

            //chiudo la connessione per evitare altre connessioni
            server.close();

            //inizializzo gli stream per consentire la comunicazione
            out = new DataOutputStream(msocket.getOutputStream());
            //in = new BufferedReader(new InputStreamReader(msocket.getInputStream()));
            in = new DataInputStream(msocket.getInputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return msocket;
    }


    public void calcola(){
        try{
            double a;
            double b;
            double risultato =0;
            //leggo la scelta e in base a quella eseguo le operazioni
            int scelta = in.read(); 
            //accetto prima i numeri
            a = in.readDouble();
            System.out.println("a:" + a);
            b = in.readDouble();
            System.out.println("b:"+ b);
            
            switch (scelta){
                case 1:
                    System.out.println("Scelta: "+scelta);
                    //somma
                     risultato = a+b;
                     System.out.println("Risultato: "+risultato);
                     break;
                case 2:
                    if(scelta == 2)
                        risultato = a-b;
                     System.out.println("Risultato: "+risultato);
                break;
                case 3:
                    if(scelta == 3)
                    risultato = a*b;
                    System.out.println("Risultato: "+risultato);
                break;
                case 4:
                    if(scelta == 4)
                    if(b==0){
                        System.out.println("Impossibile dividere pe zero");
                    }
                    risultato = a/b;
                    System.out.println("Risultato: "+risultato);
               break;
            }
            
            
        }catch (Exception e){

        }
    }


    public static void main(String args[]) {
        Server myServer = new Server();
        myServer.attendi();
        myServer.calcola();
    }
}
    

