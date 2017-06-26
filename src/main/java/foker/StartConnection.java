package foker;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Created by samintha on 6/26/2017.
 */
public class StartConnection implements Runnable {

    Thread startConnectionThread;

    StartConnection() {
        startConnectionThread = new Thread(this);

        startConnectionThread.start();
    }

    @Override
    public void run() {
        WebSocketClient client = null;
        try {
            client = new GameSocketConnection(new URI("ws://awseb-e-c-AWSEBLoa-29LKENWCHRJI-2115051294.us-west-2.elb.amazonaws.com:8080/gamenotification"), new Draft_6455());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (client != null)
            client.connect();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press ~ and enter to send a message after connection is established\n\n");

        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            if (str.equals("`")) {
                System.out.print("Enter a message: ");
                client.send(scanner.nextLine());
            } else {
                System.out.println("Error input");
            }
        }
    }
}
