package client;

import java.io.BufferedReader;
import java.io.InputStream;

public class Reader implements Runnable {
    private BufferedReader inputReader;

    public Reader(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    @Override
    public void run() {
        try{
            while (true){
                String msg = inputReader.readLine();
                System.out.println(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
