package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private DataInputStream aDataInputStream;
    private DataOutputStream aDataOutputStream;
    private Socket clientSocket;
    private String clientName;
    private String clientId;

    public ClientHandler(Socket clientSocket,DataInputStream aDataInputStream, DataOutputStream aDataOutputStream, String clientName, String clientId) {
        this.clientSocket = clientSocket;
        this.aDataInputStream = aDataInputStream;
        this.aDataOutputStream = aDataOutputStream;
        this.clientName = clientName;
        this.clientId = clientId;
    }

    public DataInputStream getaDataInputStream() {
        return aDataInputStream;
    }

    public void setaDataInputStream(DataInputStream aDataInputStream) {
        this.aDataInputStream = aDataInputStream;
    }

    public DataOutputStream getaDataOutputStream() {
        return aDataOutputStream;
    }

    public void setaDataOutputStream(DataOutputStream aDataOutputStream) {
        this.aDataOutputStream = aDataOutputStream;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Client Handler Thread Started for client "+clientId);
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(aDataInputStream));

        try {
            String name = inputReader.readLine();
            this.setClientName(name);
            try {
                ChatUtils.BROADCASTMESSAGE(name+" has joind to the group chat message");
            }catch (Exception e){
                System.out.println("Can't Broad cast Message");
                e.printStackTrace();
            }finally {
                System.out.println(this.clientName+" LEFT THE GROUP CHAT");
                this.clientSocket.close();
            }

            while (true){

                try {
                    String message = inputReader.readLine();
                    ChatUtils.BROADCASTMESSAGE(this.clientName+": " +message);
                }catch (Exception e){
                    System.out.println("Can't Broad cast Message");

                    e.printStackTrace();
                    break;
                }finally {
                    System.out.println(this.clientName+" LEFT THE GROUP CHAT");
                    this.clientSocket.close();

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
