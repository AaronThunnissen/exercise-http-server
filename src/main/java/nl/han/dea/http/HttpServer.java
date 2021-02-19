package nl.han.dea.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class HttpServer {

    private int tcpPort;

    public HttpServer(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public static void main(String[] args) {
        new HttpServer(8383).startServer();
    }

    public void startServer() {
        try (
                var serverSocket = new ServerSocket(this.tcpPort);

        ) {
            System.out.println("Server accepting requests on port " + tcpPort);
            while(true) {
                var acceptedSocket = serverSocket.accept();
                var connectionHandler = new ConnectionHandler(acceptedSocket);
                new Thread(connectionHandler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
