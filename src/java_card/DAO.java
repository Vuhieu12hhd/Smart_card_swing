package java_card;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VuVanDuc
 */
public class DAO {
    private Connection conn;
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
    public static String bytesToString(byte[] data){
        String res = "";
        for (int i= 0;i<data.length;i++){
            if(i == (data.length - 1)){
                res+=data[i];
            } else {
                res+=data[i] + ",";
            }
        }
        return res;
    }

    public static byte[] stringToBytes(String data){
        String[] source = data.split(",");
        byte[] res = new byte[source.length];
        for (int i = 0;i<source.length;i++){
            res[i] = Byte.parseByte(source[i]);
        }
        return res;
    }
    public DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.211.55.2:3306/smart_card", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean addCard(Card c) {
        String sql = "";
        if(c.getId() != null) {
            sql += "UPDATE cards SET ho_ten=?, ngay_sinh=?, bien_so_xe=?, gioi_tinh=?, public_key=?, thong_tin_nha=?, ma_pin=? WHERE id=? ";
        } else {
            sql += "INSERT INTO cards(ho_ten, ngay_sinh, bien_so_xe, gioi_tinh, public_key, thong_tin_nha, ma_pin) VALUES(?,?,?,?,?,?,?) ";
        }
        
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, c.getHoTen());
            ps.setDate(2, new Date(c.getNgaySinh().getTime()));
            ps.setString(3, c.getBienSoXe());
            ps.setString(4, c.getGioiTinh());
            ps.setString(5, c.getPublicKey());
            ps.setString(6, c.getThongTinNha());
            System.out.println(c.getMapin());
            ps.setString(7, c.getMapin());
            if(c.getId() != null) {
                ps.setInt(8, c.getId());
            }
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePublicKey(Card card, byte[] publicKey){
        String sql = "UPDATE cards SET public_key=? WHERE id=? ";
        System.out.println("key: "+ bytesToString(publicKey));
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, bytesToString(publicKey));
            ps.setString(2, card.getId().toString());
            boolean b = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Card> getListCard() {
        ArrayList<Card> list = new ArrayList();
        String sql = "SELECT * FROM cards";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Card c = new Card();
                c.setId(rs.getInt("id"));
                c.setHoTen(rs.getString("ho_ten"));
                c.setNgaySinh(rs.getDate("ngay_sinh"));
                c.setBienSoXe(rs.getString("bien_so_xe"));
                c.setGioiTinh(rs.getString("gioi_tinh"));
                c.setThongTinNha(rs.getString("thong_tin_nha"));
                c.setMapin(rs.getString("ma_pin"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public ArrayList<Log> getListLog(String filter) {
        ArrayList<Log> list = new ArrayList();
        String sql = "";
        if(filter!=null) {
            sql += "SELECT * FROM logs WHERE ho_ten LIKE '%"+filter+"%' OR bien_so_xe like '%"+filter+"%' OR thong_tin_nha like '%"+filter+"%' OR ngay_quet_the like '%"+filter+"%' OR type like '%"+filter+"%'";
        } else {
            sql += "SELECT * FROM logs";
        }
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Log c = new Log();
                c.setId(rs.getInt("id"));
                c.setHoTen(rs.getString("ho_ten"));
                c.setNgaySinh(rs.getDate("ngay_sinh"));
                c.setBienSoXe(rs.getString("bien_so_xe"));
                c.setGioiTinh(rs.getString("gioi_tinh"));
                c.setThongTinNha(rs.getString("thong_tin_nha"));
                c.setNgayQuetThe(rs.getTimestamp("ngay_quet_the").toLocalDateTime());
                c.setType(rs.getString("type"));
                
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public boolean delCard(int id) {
        String sql = "DELETE FROM cards WHERE id='"+id+"'";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addLog(Card card){
        String sql = "";
        sql += "INSERT INTO logs (ho_ten, ngay_sinh, bien_so_xe, gioi_tinh, thong_tin_nha, ngay_quet_the, type) VALUES(?,?,?,?,?,?,?) ";

        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, card.getHoTen());
            ps.setDate(2, new Date(card.getNgaySinh().getTime()));
            ps.setString(3, card.getBienSoXe());
            ps.setString(4, card.getGioiTinh());
            ps.setString(5, card.getThongTinNha());
            ps.setDate(6, new Date(System.currentTimeMillis()));
            ps.setString(7, "Quẹt thẻ");
            boolean b = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean delLog(int id) {
        String sql = "DELETE FROM logs WHERE id='"+id+"'";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public byte[] getPublicKey(int id){
        byte[] publicKey = {};
        String sql = "SELECT * FROM cards where id = " + id;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String pk = rs.getString("public_key");
            publicKey = stringToBytes(pk);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return publicKey;
    }
    public static void main(String[] args) {
        new DAO().getListCard();
    }
    
}
