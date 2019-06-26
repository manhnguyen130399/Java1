/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author Computer
 */
public class PhuHuynh implements Model {

    private String MaPH;
    private String TenPH;
    private String SDT;
    private String DiaChi;

    public PhuHuynh() {
    }

    public PhuHuynh(String MaPH) {
        this.MaPH = MaPH;
    }

    public PhuHuynh(String MaPH, String TenPH, String SDT, String DiaChi) {
        this.MaPH = MaPH;
        this.TenPH = TenPH;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
    }

    public String getMaPH() {
        return MaPH;
    }

    public void setMaPH(String MaPH) {
        this.MaPH = MaPH;
    }

    public String getTenPH() {
        return TenPH;
    }

    public void setTenPH(String TenPH) {
        this.TenPH = TenPH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    @Override
    public String toString() {
        return "PhuHuynh{" + "MaPH=" + MaPH + ", TenPH=" + TenPH + ", SDT=" + SDT + ", DiaChi=" + DiaChi + '}';
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
        final PhuHuynh other = (PhuHuynh) obj;
        if (!Objects.equals(this.MaPH, other.MaPH)) {
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
        final PhuHuynh other = (PhuHuynh) obj;
        if (!Objects.equals(this.MaPH, other.MaPH)) {
            return false;
        }
        if (!Objects.equals(this.TenPH, other.TenPH)) {
            return false;
        }
        if (!Objects.equals(this.SDT, other.SDT)) {
            return false;
        }
        if (!Objects.equals(this.DiaChi, other.DiaChi)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public Model ChangeToNull() {
        if (this.MaPH != null) {
            if (this.MaPH.isEmpty()) {
                this.MaPH = null;
            }
        }

        if (this.TenPH != null) {
            if (this.TenPH.isEmpty()) {
                this.TenPH = null;
            }
        }

        if (this.SDT != null) {
            if (this.SDT.isEmpty()) {
                this.SDT = null;
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
        if (this.MaPH == null) {
            this.MaPH = "";
        }

        if (this.TenPH == null) {
            this.TenPH = "";
        }

        if (this.SDT == null) {
            this.SDT = "";
        }

        if (this.DiaChi == null) {
            this.DiaChi = "";

        }

        return this;
    }

    @Override
    public Model clone() {
        return new PhuHuynh(MaPH, TenPH, SDT, DiaChi);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        PhuHuynh newPhuHuynh = (PhuHuynh)newModel;
        this.DiaChi = newPhuHuynh.getDiaChi();
        this.MaPH = newPhuHuynh.getMaPH();
        this.SDT = newPhuHuynh.getSDT();
        this.TenPH = newPhuHuynh.getTenPH();
        
        return oldModel;
    }
}
