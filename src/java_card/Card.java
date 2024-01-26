/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_card;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**a
 *
 * @author VuVanDuc
 */
public class Card implements Serializable {
    private Integer id;
    private String hoTen, publicKey, thongTinNha, mapin, gioiTinh, bienSoXe;
    private Date ngaySinh;

    public Card(String hoTen, String gioiTinh, String publicKey, String thongTinNha, String mapin, String bienSoXe, Date ngaySinh) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.publicKey = publicKey;
        this.thongTinNha = thongTinNha;
        this.mapin = mapin;
        this.bienSoXe = bienSoXe;
        this.ngaySinh = ngaySinh;
    }

    public Card(byte[] data){
        parse(new String(data, StandardCharsets.UTF_8));
    }
    
    public Card() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getThongTinNha() {
        return thongTinNha;
    }

    public String getMapin() {
        return mapin;
    }

    public void setMapin(String mapin) {
        this.mapin = mapin;
    }

    public void setThongTinNha(String thongTinNha) {
        this.thongTinNha = thongTinNha;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void parse(String data){
        String[] d = data.split("@");
        setId(Integer.parseInt(d[0]));
        setHoTen(d[1]);
        setBienSoXe(d[2]);
        setNgaySinh(new Date(Long.parseLong(d[3])));
        setGioiTinh(d[4]);
        setThongTinNha(d[5]);

    }

    public void parseWithoutId(String data){
        String[] d = data.split("@");
        setHoTen(d[0]);
        setBienSoXe(d[1]);
        setNgaySinh(new Date(Long.parseLong(d[2])));
        setGioiTinh(d[3]);
        setThongTinNha(d[4]);

    }

    public String toString(){
        return getId()+"@"+getHoTen()+"@"+getBienSoXe()+"@"+getNgaySinh().getTime()+"@"+getGioiTinh()+"@"+getThongTinNha();
    }

    public String toStringWithoutId(){
        return getHoTen()+"@"+getBienSoXe()+"@"+getNgaySinh().getTime()+"@"+getGioiTinh()+"@"+getThongTinNha();
    }
}
