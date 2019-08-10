package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;

public class Client3 {
    public static void main(String[] args) {
        try{
            Socket clientSocket = new Socket("localhost",6789);

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Reader reader = new Reader(inputReader);
            Thread readerThread = new Thread(reader);
            readerThread.start();

            DataOutputStream myDataOutputStream =new DataOutputStream(clientSocket.getOutputStream());
            Writter writter = new Writter(myDataOutputStream);
            Thread writterThread = new Thread(writter);
            writterThread.start();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
