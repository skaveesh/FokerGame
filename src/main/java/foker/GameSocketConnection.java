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

    private static int PLAYER_ID;

    public GameSocketConnection(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public GameSocketConnection(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("new connection opened");

    }

    @Override
    public void onMessage(String message) {

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
            PlayerIdData player = gson.fromJson(jsonObject.getJSONObject("PLAYERID").toString(), PlayerIdData.class);
            PLAYER_ID = player.getPLAYERID().getPlayerId();
            System.out.println(PLAYER_ID);
        } else if(isJsonObject && jsonObject.has("PLAYERREADY")){
            PlayerReadyData playerReadyData = gson.fromJson(jsonObject.getJSONObject("PLAYERREADY").toString(), PlayerReadyData.class);

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

    }
}
