package com.sztefanov.smsserver.network;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Server {

    private TextView view;
    private Thread network;

    public Server(TextView view) {
        this.view = view;
    }

    private String get_lan_ip() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                if (intf.getName().equals("wlan0")) {
                    for (Enumeration<InetAddress> addresses = intf.getInetAddresses(); addresses.hasMoreElements(); ) {
                        InetAddress address = addresses.nextElement();
                        if (address instanceof Inet4Address) {
                            return address.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressLint("SetTextI18n")
    private void setIp() {
        view.setText("IP ADDRESS: " + get_lan_ip());
    }

    public void start_network() {
        try {
            network = new Thread(new Network());
            network.start();
            setIp();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void stop_network() {
        System.exit(0);
    }
}

