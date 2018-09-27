package com.sztefanov.smsserver.network;

import android.telephony.SmsManager;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Network implements Runnable {

    private DatagramSocket socket;

    Network() throws SocketException {
        int port = 50000;
        this.socket = new DatagramSocket(port);
    }

    private void listen() {
        try {
            byte[] input = new byte[1024];
            DatagramPacket packet_receive = new DatagramPacket(input, 1024);
            socket.receive(packet_receive);
            ByteArrayInputStream in = new ByteArrayInputStream(packet_receive.getData());
            ObjectInputStream is = new ObjectInputStream(in);
            Object o = is.readObject();
            if (o instanceof Packet) {
                //String incoming = (String) o;
                //get 5 charachters code
                Packet packet = (Packet) o;
                Log.i("incoming", o.toString());
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(packet.getTel(), null, packet.getCode(), null, null);
            }
        } catch (ClassNotFoundException | IOException ex) {
            Log.e("server", "Failed to start server: " + ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            listen();
        }
    }

}

