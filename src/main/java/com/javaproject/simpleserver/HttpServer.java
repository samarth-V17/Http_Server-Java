package com.javaproject.simpleserver;

import com.javaproject.simpleserver.config.Configuration;
import com.javaproject.simpleserver.config.ConfigurationManager;
import com.javaproject.simpleserver.core.ServerListenerThread;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {


        LOGGER.info("Server starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf =  ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Port: {}", conf.getPort());
        LOGGER.info("Webroot: {}", conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread((conf.getPort()), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
