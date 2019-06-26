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
public class CO implements Model {

    private String MaPH;
    private String MaHS;
    private String Loai;

    public CO() {
    }

    public CO(String MaPH, String MaHS) {
        this.MaPH = MaPH;
        this.MaHS = MaHS;
    }

    public CO(String MaPH, String MaHS, String Loai) {
        this.MaPH = MaPH;
        this.MaHS = MaHS;
        this.Loai = Loai;
    }    

    public String getMaPH() {
        return MaPH;
    }

    public void setMaPH(String MaPH) {
        this.MaPH = MaPH;
    }

    public String getMaHS() {
        return MaHS;
    }

    public void setMaHS(String MaHS) {
        this.MaHS = MaHS;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String Loai) {
        this.Loai = Loai;
    }

    @Override
    public String toString() {
        return "CO{" + "MaPH=" + MaPH + ", MaHS=" + MaHS + ", Loai=" + Loai + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final CO other = (CO) obj;
        if (!Objects.equals(this.MaPH, other.MaPH)) {
            return false;
        }
        if (!Objects.equals(this.MaHS, other.MaHS)) {
            return false;
        }
        if (!Objects.equals(this.Loai, other.Loai)) {
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
        final CO other = (CO) obj;
        if (!Objects.equals(this.MaPH, other.MaPH)) {
            return false;
        }
        if (!Objects.equals(this.MaHS, other.MaHS)) {
            return false;
        }
        if (!Objects.equals(this.Loai, other.Loai)) {
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
        if (this.MaHS != null) {
            if (this.MaHS.isEmpty()) {
                this.MaHS = null;
            }
        }
        if (this.Loai != null) {
            if (this.Loai.isEmpty()) {
                this.Loai = null;
            }
        }
        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.MaPH == null) {
            this.MaPH = "";
        }
        if (this.MaHS == null) {
            this.MaHS = "";
        }
        if (this.Loai == null) {
            this.Loai = "";
        }
        return this;
    }

    @Override
    public Model clone() {
        return new CO(MaPH, MaHS, Loai);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        CO newCO = ((CO)newModel);
        this.Loai = newCO.getLoai();
        this.MaHS = newCO.getMaHS();
        this.MaPH = newCO.getMaPH();
        
        return oldModel;
    }
}
