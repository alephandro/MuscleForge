package com.example.gym.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkVariables {

    public static final int Port = 8888;

    private static String ServerDomain = "muscleforge-server.duckdns.org";

    public static String ServerIP = getIPFromDNS(ServerDomain); //10.0.2.2 for localhost

    public static String getIPFromDNS(String url) {
        while(true){
            try {
                InetAddress address = InetAddress.getByName(url);
                return address.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

}
