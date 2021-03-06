package com.gordondickens.javaseven.streamio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ManagingAsynchronousCommunicationClient {
    private static final Logger logger = LoggerFactory.getLogger(ManagingAsynchronousCommunicationClient.class);

    public static void main(String[] args) {
        try {
            AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
            InetSocketAddress address = new InetSocketAddress("localhost", 5000);

            Future<Void> future = client.connect(address);
            logger.debug("Client: Waiting for the connection to complete");
            future.get();

            String message = "";
            while (!message.equals("quit")) {
                System.out.print("Enter a message: ");
                Scanner scanner = new Scanner(System.in);
                message = scanner.nextLine();
                logger.debug("Client: Sending ...");
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                logger.debug("Client: Message sent: " + new String(buffer.array()));
                client.write(buffer);
            }

        } catch (IOException | InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
    }

}
