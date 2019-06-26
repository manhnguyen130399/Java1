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
public class HanhKiem implements Model {

    private String MaHK;
    private String TenHK;

    public HanhKiem() {
    }

    public HanhKiem(String MaLoaiHK) {
        this.MaHK = MaLoaiHK;
    }

    public HanhKiem(String MaLoaiHK, String TenHK) {
        this.MaHK = MaLoaiHK;
        this.TenHK = TenHK;
    }

    public String getMaHK() {
        return MaHK;
    }

    public void setMaHK(String MaLoaiHK) {
        this.MaHK = MaLoaiHK;
    }

    public String getTenHK() {
        return TenHK;
    }

    public void setTenHK(String TenHK) {
        this.TenHK = TenHK;
    }

    @Override
    public String toString() {
        return MaHK + " - " + TenHK;
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
        final HanhKiem other = (HanhKiem) obj;
        if (!Objects.equals(this.MaHK, other.MaHK)) {
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
        final HanhKiem other = (HanhKiem) obj;
        if (!Objects.equals(this.MaHK, other.MaHK)) {
            return false;
        }
        if (!Objects.equals(this.TenHK, other.TenHK)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public Model ChangeToNull() {
        if (this.MaHK != null) {
            if (this.MaHK.isEmpty()) {
                this.MaHK = null;
            }
        }
        if (this.TenHK != null) {
            if (this.TenHK.isEmpty()) {
                this.TenHK = null;
            }
        }

        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.MaHK == null) {
            this.MaHK = "";
        }
        if (this.TenHK == null) {
            this.TenHK = "";
        }

        return this;
    }

    @Override
    public Model clone() {
        return new HanhKiem(MaHK, TenHK);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        HanhKiem newHanhKiem = (HanhKiem)newModel;
        this.MaHK = newHanhKiem.getMaHK();
        this.TenHK = newHanhKiem.getTenHK();
        
        return oldModel;
    }
}
