package server;

import java.io.IOException;
import java.util.ArrayList;

public class ChatUtils {
    static String ENTER_YOUR_NAME_MESSAGE = "Enter Your Name: ";
    static String WELCOME_MESSAGE = "Welcome to CSE4226 Group Chat";
    static ArrayList<ClientHandler> CLIENTS = new ArrayList<>();

    static void BROADCAST_MESSAGE(String message) throws IOException {
        for (ClientHandler clientHandler : CLIENTS) {
            clientHandler.getaDataOutputStream().writeBytes(message + "\n");
        }
    }

    static boolean REMOVE_ONE_CHAT_HANDLER(String clientId) {
        for (int i = 0; i < CLIENTS.size(); i++) {
            if (CLIENTS.get(i).getClientId().equals(clientId)) {
                CLIENTS.remove(i);
                return true;
            }
        }
        return false;
    }

    static void BROADCAST_MESSAGE_WITHOUT_CURRENT_CLIENT(String currentClientId, String message) throws IOException {
        for (ClientHandler clientHandler : CLIENTS) {
            if (!clientHandler.getClientId().equals(currentClientId)) {
                clientHandler.getaDataOutputStream().writeBytes(message + "\n");
            }

        }
    }

    static void SEND_MESSAGE_TO_SPECIFIC_CLIENT(String clinetId, String message) throws IOException {
        for (ClientHandler clientHandler : CLIENTS) {
            if (clientHandler.getClientId().equals(clinetId)) {
                clientHandler.getaDataOutputStream().writeBytes(message + "\n");
            }

        }
    }


}
