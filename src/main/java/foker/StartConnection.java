package foker;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by samintha on 6/26/2017.
 */
public class StartConnection implements Runnable {

    private static WebSocketClient client = null;

    StartConnection() {
        Thread startConnectionThread = new Thread(this);

        startConnectionThread.start();
    }

    public static void sendMessage(String message) {
        if (client != null && GameSocketConnection.CONNECTION_OPENED)
            client.send(message);
    }

    @Override
    public void run() {

        try {
            client = new GameSocketConnection(new URI("ws://awseb-e-c-AWSEBLoa-29LKENWCHRJI-2115051294.us-west-2.elb.amazonaws.com:8080/gamenotification"), new Draft_6455());
//            client = new GameSocketConnection(new URI("ws://localhost:8100/gamenotification"), new Draft_6455());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (client != null)
            client.connect();

//        Runnable gameDataSubscriber = () -> {
//            if(GameSocketConnection.CONNECTION_OPENED)
//                sendMessage("KEEPLIVWE");
//        };
//
//
//        //pinging connection every 40 second
//        //start fetching data every second
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        executor.scheduleAtFixedRate(gameDataSubscriber, 40, 40, TimeUnit.SECONDS);
    }
}
