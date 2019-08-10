package server;

import java.io.IOException;
import java.util.ArrayList;

public class ChatUtils {
    static String ENTERYOURNAMEMESSAGE = "Enter Your Name: ";
    static ArrayList<ClientHandler> CLIENTS = new ArrayList<>() ;

    static void BROADCASTMESSAGE(String message) throws IOException {
        for(ClientHandler clientHandler:CLIENTS){
            clientHandler.getaDataOutputStream().writeBytes(message+"\n");
        }
    }



}
