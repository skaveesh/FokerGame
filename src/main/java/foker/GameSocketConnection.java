package foker;

import com.google.gson.Gson;
import foker.gson.PLAYERID;
import foker.gson.PLAYERREADY;
import foker.gson.PlayerIdData;
import foker.gson.PlayerReadyData;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;

/**
 * Created by samintha on 6/26/2017.
 */
public class GameSocketConnection extends WebSocketClient {

    public static int PLAYER_ID;

    GameSocketConnection(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    GameSocketConnection(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("new connection opened");

    }

    @Override
    public void onMessage(String message) {

        System.out.println("Server returned data:");
        System.out.println(message);

        boolean isJsonObject;
        JSONObject jsonObject = new JSONObject();
        Gson gson = new Gson();

        try {
            jsonObject = new JSONObject(message);
            isJsonObject = true;
        } catch (Exception e) {
            e.printStackTrace();
            isJsonObject = false;
        }

        if (isJsonObject && jsonObject.has("PLAYERID")) {
            PlayerIdData playerIdData = gson.fromJson(message, PlayerIdData.class);
            PLAYER_ID = playerIdData.getPLAYERID().getPlayerId();
        } else if(isJsonObject && jsonObject.has("PLAYERREADY")){
            PlayerReadyData playerReadyData = gson.fromJson(message, PlayerReadyData.class);

            for(PLAYERREADY playerready : playerReadyData.getPLAYERREADY()){
                if(!playerready.getPlayerReady()){
                    System.out.println(playerready.getPlayerName()+" is not ready yet");                }
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);

    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    ex.printStackTrace();
    }
}
