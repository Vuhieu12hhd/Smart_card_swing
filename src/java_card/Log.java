/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_card;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**a
 *
 * @author VuVanDuc
 */
public class Log implements Serializable {
    private Integer id;
    private String hoTen, thongTinNha, mapin, gioiTinh, bienSoXe, type;
    private Date ngaySinh;
    private LocalDateTime ngayQuetThe;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getNgayQuetThe() {
        return ngayQuetThe;
    }

    public void setNgayQuetThe(LocalDateTime ngayQuetThe) {
        this.ngayQuetThe = ngayQuetThe;
    }
}
