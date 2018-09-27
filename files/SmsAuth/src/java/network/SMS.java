package network;

import network.Connector;
import com.sztefanov.smsserver.network.Packet;
import network.Database;
import functions.Functions;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMS {

    public String send(String tel) {
        String code = null;
        String random = "";
        for (int i = 0; i < 100; i++) {
            int random_ascii = Functions.randomRange(0, 127);
            random += (char) random_ascii;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(random.getBytes("utf8"));
            code = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return "HIBA";
        }
        code = code.substring(0, 6);
        String register_account = Database.register_account(tel, code);
        boolean register_success = register_account.equals("success");
        boolean send_code_success = Connector.send_code(new Packet(tel, code));
        if (!register_success || !send_code_success) {
            return register_account;
        } else {
            return "SMS ELKÜLDVE";
        }
    }
    
}
