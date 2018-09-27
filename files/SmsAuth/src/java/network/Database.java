package network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static String MYSQL_HOST = "jdbc:mysql://localhost/security";
    private static String MYSQL_USER = "security";
    private static String MYSQL_PASS = "security";
    
    private static ResultSet query(String sql) {
        try {
            Connection conn = (Connection) DriverManager.getConnection(MYSQL_HOST, MYSQL_USER, MYSQL_PASS);
            Statement s = conn.createStatement();
            return s.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String register_account(String tel, String code) {
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = (Connection) DriverManager.getConnection(MYSQL_HOST, MYSQL_USER, MYSQL_PASS);
            Statement s = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM users WHERE tel = " + tel;
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            int rows = rs.getInt(1);
            if(rows == 0) {
                sql = "INSERT INTO users (tel, code) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, tel);
                stmt.setString(2, code);
                stmt.executeUpdate();
                conn.close();
            } else {
                sql = "UPDATE users SET code = ? WHERE tel = " + tel;
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, code);
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
        return "success";
    }
    
    public static String authenticate(String tel, String code) {
        try {
            Connection conn = (Connection) DriverManager.getConnection(MYSQL_HOST, MYSQL_USER, MYSQL_PASS);
            String sql = "SELECT code FROM users WHERE tel = " + tel;
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            String db_code = rs.getString("code");
            if(code.equals(db_code)) {
                return "success";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";
    }
    
    private static String p(int tabs, String line) {
        String result = "\n";
        for (int i = 0; i < tabs; i++) {
            result += "\t";
        }
        result += line;
        return result;
    }
    
    public static String list_users() throws SQLException {
        String html = "";
        String sql = "SELECT * FROM users";
        ResultSet rs = query(sql);
        int count = 1;
        while(rs.next()) {
            String tel = rs.getString("tel");
            String code = rs.getString("code");
            html += p(7, "<tr>");
            html += p(8, "<th scope='row'>" + count + "</th>");
            html += p(8, "<td>" + tel + "</td>");
            html += p(8, "<td>" + code + "</td>");
            html += p(7, "</tr>");
            count++;
        }
        return html;
    }
}
