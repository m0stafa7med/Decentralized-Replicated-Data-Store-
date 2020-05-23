package src;
import src.Communication.MulticastReceiver;
import src.Communication.MulticastPublisher;
import src.Communication.ReceiveThread;
import src.Communication.SendThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

public class Main{
    public static void main(String[] args)
    {
        //Multicast receiver that runs on every node.
        Thread multicastReceiverThread = new Thread(new MulticastReceiver());
        multicastReceiverThread.start();

        MulticastPublisher multicastPublisher = new MulticastPublisher();

        //Perform the first broadcast
        try{
            multicastPublisher.multicast("هلا وغلا");
        } catch (SocketException seIOException)
        {
            System.out.println("Unexpected socket problem. Please try again later.");
        }catch (IOException ioException)
        {
            System.out.println("Unexpected I/O exception problem. Please try again later.");
        }

        //A receive thread that waits for data from another peers.
        Thread receiveThread = new Thread(new ReceiveThread());
        receiveThread.start();

        
        try {
            multicastReceiverThread.join();
        }catch (InterruptedException ie)
        {
            System.out.println("Interrupted Exception");
        }
    }
}