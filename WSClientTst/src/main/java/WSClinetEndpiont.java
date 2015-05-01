import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

public class WSClinetEndpiont
{
   private static CountDownLatch messageLatch;
   private static final String MSG_TO_SEND = "hello";

   public static void main( String[] args )
   {
      try
      {
         messageLatch = new CountDownLatch(1);

         final ClientEndpointConfig ceInstance = ClientEndpointConfig.Builder.create().build();

         ClientManager client = ClientManager.createClient();
         client.connectToServer(new Endpoint() {

            @Override
            public void onOpen( Session session, EndpointConfig config )
            {
               try
               {
                  session.addMessageHandler(new MessageHandler.Whole<String>() {
                     public void onMessage( String message )
                     {
                        System.out.println(message);
                        messageLatch.countDown();
                     }
                  });
                  session.getBasicRemote().sendText(MSG_TO_SEND);
               }
               catch (IOException e)
               {
                  e.printStackTrace();
               }
            }
         }, ceInstance, new URI("ws://localhost/api/v1/ws"));
         messageLatch.await(100, TimeUnit.SECONDS);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
