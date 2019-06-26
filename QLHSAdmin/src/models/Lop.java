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
public class Lop implements Model{

    private String MaLop;
    private String TenLop;

    public Lop() {
    }

    public Lop(String MaLop) {
        this.MaLop = MaLop;
    }

    public Lop(String MaLop, String TenLop) {
        this.MaLop = MaLop;
        this.TenLop = TenLop;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Lop other = (Lop) obj;
        if (!Objects.equals(this.MaLop, other.MaLop)) {
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
        final Lop other = (Lop) obj;
        if (!Objects.equals(this.MaLop, other.MaLop)) {
            return false;
        }
        if (!Objects.equals(this.TenLop, other.TenLop)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return MaLop + " - " + TenLop;
    }

    @Override
    public Model ChangeToNull() {
        if (this.MaLop != null) {
            if (this.MaLop.isEmpty()) {
                this.MaLop = null;
            }
        }

        if (this.TenLop != null) {
            if (this.TenLop.isEmpty()) {
                this.TenLop = null;
            }
        }
        
        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.MaLop == null) {
            this.MaLop = "";
        }

        if (this.TenLop == null) {
            this.TenLop = "";
        }
        
        return this;
    }

    @Override
    public Model clone() {
        return new Lop(MaLop, TenLop);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        Lop newLop = (Lop)newModel;
        this.MaLop = newLop.getMaLop();
        this.TenLop = newLop.getTenLop();
        
        return oldModel;
    }
}
