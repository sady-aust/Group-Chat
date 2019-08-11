package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private DataInputStream aDataInputStream;
    private DataOutputStream aDataOutputStream;
    private Socket clientSocket;
    private String clientName;
    private String clientId;

    public ClientHandler(Socket clientSocket, DataInputStream aDataInputStream, DataOutputStream aDataOutputStream, String clientName, String clientId) {
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
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(aDataInputStream));

        try {
            String name = inputReader.readLine();
            this.setClientName(name);
            try {
                String joinMessage = "Server Message: " + name + " has joined to the group chat message";
                ChatUtils.BROADCAST_MESSAGE_WITHOUT_CURRENT_CLIENT(this.clientId, joinMessage);

                String welcomeMessage = "Hey! " + this.clientName + " " + ChatUtils.WELCOME_MESSAGE;
                ChatUtils.SEND_MESSAGE_TO_SPECIFIC_CLIENT(this.clientId, welcomeMessage);

            } catch (Exception e) {
                System.out.println("Can't Broadcast Message");
                e.printStackTrace();
            }

            while (true) {
                String message = inputReader.readLine();
                ChatUtils.BROADCAST_MESSAGE(this.clientName + ":" + message);

            }
        } catch (Exception e) {
            if (ChatUtils.REMOVE_ONE_CHAT_HANDLER(this.clientId)) {
                System.out.println("One client left!");
                System.out.println("Latest Count " + ChatUtils.CLIENTS.size());
                try {
                    ChatUtils.BROADCAST_MESSAGE("Server Message: " + this.clientName + " has left the group chat");
                } catch (Exception ex) {
                    System.out.println("Can't broadcast message");
                }
            }

        } finally {
            try {
                this.clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
