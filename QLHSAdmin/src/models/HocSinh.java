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
public class HocSinh implements Model {

    private String MaHS;
    private String TenHs;
    private String GioiTinh;
    private Date NgaySinh;
    private String DanToc;
    private String TonGiao;
    private String DiaChi;

    public HocSinh() {
    }

    public HocSinh(String MaHS) {
        this.MaHS = MaHS;
    }

    public HocSinh(String MaHS, String MaPH) {
        this.MaHS = MaHS;
    }

    public HocSinh(String MaHS, String TenHs, String GioiTinh, Date NgaySinh, String DanToc, String TonGiao, String DiaChi) {
        this.MaHS = MaHS;
        this.TenHs = TenHs;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DanToc = DanToc;
        this.TonGiao = TonGiao;
        this.DiaChi = DiaChi;
    }

    public String getMaHS() {
        return MaHS;
    }

    public void setMaHS(String MaHS) {
        this.MaHS = MaHS;
    }

    public String getTenHs() {
        return TenHs;
    }

    public void setTenHs(String TenHs) {
        this.TenHs = TenHs;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getDanToc() {
        return DanToc;
    }

    public void setDanToc(String DanToc) {
        this.DanToc = DanToc;
    }

    public String getTonGiao() {
        return TonGiao;
    }

    public void setTonGiao(String TonGiao) {
        this.TonGiao = TonGiao;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
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
        final HocSinh other = (HocSinh) obj;
        if (!Objects.equals(this.MaHS, other.MaHS)) {
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
        final HocSinh other = (HocSinh) obj;
        if (!Objects.equals(this.MaHS, other.MaHS)) {
            return false;
        }
        if (!Objects.equals(this.TenHs, other.TenHs)) {
            return false;
        }
        if (!Objects.equals(this.GioiTinh, other.GioiTinh)) {
            return false;
        }
        if (!Objects.equals(this.DanToc, other.DanToc)) {
            return false;
        }
        if (!Objects.equals(this.TonGiao, other.TonGiao)) {
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
    public String toString() {
        return MaHS + " - " + TenHs;
    }

    @Override
    public Model ChangeToNull() {
        if (this.MaHS != null) {
            if (this.MaHS.isEmpty()) {
                this.MaHS = null;
            }
        }

        if (this.TenHs != null) {
            if (this.TenHs.isEmpty()) {
                this.TenHs = "";
            }
        }

        if (this.GioiTinh != null) {
            if (this.GioiTinh.isEmpty()) {
                this.GioiTinh = null;
            }
        }

        if (this.DanToc != null) {
            if (this.DanToc.isEmpty()) {
                this.DanToc = null;
            }
        }

        if (this.TonGiao != null) {
            if (this.TonGiao.isEmpty()) {
                this.TonGiao = null;
            }
        }

        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.MaHS == null) {
            this.MaHS = "";
        }
        if (this.NgaySinh == null) {
            this.NgaySinh = new Date(1999 - 1900, 1, 1);
        }
        if (this.GioiTinh == null) {
            this.GioiTinh = "";
        }
        if (this.DiaChi == null) {
            this.DiaChi = "";
        }
        if (this.DanToc == null) {
            this.DanToc = "";
        }
        if (this.TonGiao == null) {
            this.TonGiao = "";
        }

        return this;
    }

    @Override
    public Model clone() {
        return new HocSinh(MaHS, TenHs, GioiTinh, NgaySinh, DanToc, TonGiao, DiaChi);
    }

    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();

        HocSinh newHocSinh = (HocSinh) newModel;
        this.DanToc = newHocSinh.getDanToc();
        this.DiaChi = newHocSinh.getDiaChi();
        this.GioiTinh = newHocSinh.getGioiTinh();
        this.MaHS = newHocSinh.getMaHS();
        this.NgaySinh = newHocSinh.getNgaySinh();
        this.TenHs = newHocSinh.getTenHs();
        this.TonGiao = newHocSinh.getTonGiao();

        return oldModel;
    }
}
