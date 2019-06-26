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
public class HocLuc implements Model {

    private String MaHL;
    private String LoaiHL;

    public HocLuc() {
    }

    public HocLuc(String MaLoaiHL) {
        this.MaHL = MaLoaiHL;
    }

    public HocLuc(String MaLoaiHL, String LoaiHocLuc) {
        this.MaHL = MaLoaiHL;
        this.LoaiHL = LoaiHocLuc;
    }

    public String getMaHL() {
        return MaHL;
    }

    public void setMaHL(String MaLoaiHL) {
        this.MaHL = MaLoaiHL;
    }

    public String getTenHL() {
        return LoaiHL;
    }

    public void setTenHL(String LoaiHocLuc) {
        this.LoaiHL = LoaiHocLuc;
    }

    @Override
    public String toString() {
        return MaHL + " - " + LoaiHL;
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
        final HocLuc other = (HocLuc) obj;
        if (!Objects.equals(this.MaHL, other.MaHL)) {
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
        final HocLuc other = (HocLuc) obj;
        if (!Objects.equals(this.MaHL, other.MaHL)) {
            return false;
        }
        if (!Objects.equals(this.LoaiHL, other.LoaiHL)) {
            return false;
        }
        return true;
    }
    
    @Override
    public Model ChangeToNull() {
        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        return this;
    }

    @Override
    public Model clone() {
        return new HocLuc(MaHL, LoaiHL);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        HocLuc newHocLuc = (HocLuc)newModel;
        this.LoaiHL = newHocLuc.getTenHL();
        this.MaHL = newHocLuc.getMaHL();
        
        return oldModel;
    }
}
