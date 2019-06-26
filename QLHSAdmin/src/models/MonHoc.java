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
public class MonHoc implements Model {

    private String MaMH;
    private String TenMH;

    public MonHoc() {
    }

    public MonHoc(String MaMH) {
        this.MaMH = MaMH;
    }

    public MonHoc(String MaMH, String TenMH) {
        this.MaMH = MaMH;
        this.TenMH = TenMH;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String MaMH) {
        this.MaMH = MaMH;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String TenMH) {
        this.TenMH = TenMH;
    }

    @Override
    public String toString() {
        return MaMH + " - " + TenMH;
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
        final MonHoc other = (MonHoc) obj;
        if (!Objects.equals(this.MaMH, other.MaMH)) {
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
        final MonHoc other = (MonHoc) obj;
        if (!Objects.equals(this.MaMH, other.MaMH)) {
            return false;
        }
        if (!Objects.equals(this.TenMH, other.TenMH)) {
            return false;
        }
        return true;
    }

    @Override
    public Model ChangeToNull() {
        if (this.MaMH != null) {
            if (this.MaMH.isEmpty()) {
                this.MaMH = null;
            }
        }

        if (this.TenMH != null) {
            if (this.TenMH.isEmpty()) {
                this.TenMH = null;
            }
        }
        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.MaMH == null) {
            this.MaMH = "";
        }
        if (this.TenMH == null) {
            this.TenMH = "";
        }
        return this;
    }

    @Override
    public Model clone() {
        return new MonHoc(MaMH, TenMH);
    }

    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        MonHoc newMonHoc = (MonHoc)newModel;
        this.MaMH = newMonHoc.getMaMH();
        this.TenMH = newMonHoc.getTenMH();
        
        return oldModel;
    }
}
