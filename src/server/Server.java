package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int PORT = 6789;
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is Running on "+PORT);
            while (true){
                Socket welcomeSocket = serverSocket.accept();
                DataInputStream myDataInputStream = new DataInputStream(welcomeSocket.getInputStream());
                DataOutputStream myDataOutputStream = new DataOutputStream(welcomeSocket.getOutputStream());
                myDataOutputStream.writeBytes(ChatUtils.ENTERYOURNAMEMESSAGE+"\n");
                ClientHandler aClientHandler = new ClientHandler(welcomeSocket,myDataInputStream,myDataOutputStream,null,null);
                ChatUtils.CLIENTS.add(aClientHandler);

                aClientHandler.setClientId(Integer.toString(ChatUtils.CLIENTS.size()));

                Thread clientHandlerThread = new Thread(aClientHandler);
                clientHandlerThread.start();


            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
