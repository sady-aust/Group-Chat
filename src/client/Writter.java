package client;

import java.io.DataOutputStream;
import java.util.Scanner;

public class Writter implements Runnable {
    private DataOutputStream outputWritter;

    public Writter(DataOutputStream myDataOutputStream) {
        this.outputWritter = myDataOutputStream;
    }

    @Override
    public void run() {
        try {
            while (true){
                Scanner sc = new Scanner(System.in);
                String msg = sc.nextLine();
                outputWritter.writeBytes(msg+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
