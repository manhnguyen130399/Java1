/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Computer
 */
public class GiaoVien implements Model {

    private String MaGV;
    private String TenGV;
    private Date NgaySinh;
    private String GioiTinh;
    private String DiaChi;

    public GiaoVien() {
    }

    public GiaoVien(String MaGV) {
        this.MaGV = MaGV;
    }

    public GiaoVien(String MaGV, String TenGV, Date NgaySinh, String GioiTinh, String DiaChi) {
        this.MaGV = MaGV;
        this.TenGV = TenGV;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
    }

    public String getMaGV() {
        return MaGV;
    }

    public void setMaGV(String MaGV) {
        this.MaGV = MaGV;
    }

    public String getTenGV() {
        return TenGV;
    }

    public void setTenGV(String TenGV) {
        this.TenGV = TenGV;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    @Override
    public String toString() {
        return  MaGV + " " + TenGV;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GiaoVien other = (GiaoVien) obj;
        if (!Objects.equals(this.MaGV, other.MaGV)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equalsAll(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GiaoVien other = (GiaoVien) obj;
        if (!Objects.equals(this.MaGV, other.MaGV)) {
            return false;
        }
        if (!Objects.equals(this.TenGV, other.TenGV)) {
            return false;
        }
        if (!Objects.equals(this.GioiTinh, other.GioiTinh)) {
            return false;
        }
        if (!Objects.equals(this.DiaChi, other.DiaChi)) {
            return false;
        }
        if (!Objects.equals(this.NgaySinh, other.NgaySinh)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public Model ChangeToNull() {
        if (this.MaGV != null) {
            if (this.MaGV.isEmpty()) {
                this.MaGV = null;
            }
        }
        if (this.TenGV != null) {
            if (this.TenGV.isEmpty()) {
                this.TenGV = null;
            }
        }
        if (this.GioiTinh != null) {
            if (this.GioiTinh.isEmpty()) {
                this.GioiTinh = null;
            }
        }
        if (this.DiaChi != null) {
            if (this.DiaChi.isEmpty()) {
                this.DiaChi = null;
            }
        }

        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.MaGV == null) {
            this.MaGV = "";
        }
        if (this.TenGV == null) {
            this.TenGV = "";
        }
        if (this.GioiTinh == null) {
            this.GioiTinh = "";
        }
        if (this.DiaChi == null) {
            this.DiaChi = "";
        }
        if (this.NgaySinh == null) {
            this.NgaySinh = new Date();
        }

        return this;
    }

    @Override
    public Model clone() {
        return new GiaoVien(MaGV, TenGV, NgaySinh, GioiTinh, DiaChi);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        GiaoVien newGiaoVien = (GiaoVien)newModel;
        this.DiaChi = newGiaoVien.getDiaChi();
        this.GioiTinh = newGiaoVien.getGioiTinh();
        this.MaGV = newGiaoVien.getMaGV();
        this.NgaySinh = newGiaoVien.getNgaySinh();
        this.TenGV = newGiaoVien.getTenGV();
        
        return oldModel;
    }
}
