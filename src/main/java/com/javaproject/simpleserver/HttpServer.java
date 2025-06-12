package com.javaproject.simpleserver;


import com.javaproject.simpleserver.config.Configuration;
import com.javaproject.simpleserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    public static void main(String[] args) {

        System.out.println("Server starting....");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf =  ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using port:" +conf.getPort());
        System.out.println("webroot:"+ conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>HTTP Server-Java</title></head><body><h1>Java HTTP server is working</h1></body></html>";
            final String CRLF = "\n\r";
            String response =
                    "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: "+ html.getBytes().length + CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;
            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
