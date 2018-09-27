package network;

import com.sztefanov.smsserver.network.Packet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {

    private static int PORT = 50000;
    private static String HOST = "192.168.0.87";

    public static boolean send_code(Packet p) {
        try {
            InetAddress address = Inet4Address.getByName(HOST);
            DatagramSocket socket = new DatagramSocket();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(p);
            byte[] output = out.toByteArray();
            DatagramPacket packet = new DatagramPacket(output, output.length, address, PORT);
            socket.send(packet);
            os.flush();
            os.close();
        } catch (SocketException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
