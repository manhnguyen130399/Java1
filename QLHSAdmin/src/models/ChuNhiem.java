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
public class ChuNhiem implements Model {

    private String MaGV;
    private String MaLOP;
    private Integer Nam;
    private Integer HocKi;

    public ChuNhiem() {
    }

    public ChuNhiem(String MaGV, String MaLOP, Integer Nam, Integer HocKi) {
        this.MaGV = MaGV;
        this.MaLOP = MaLOP;
        this.Nam = Nam;
        this.HocKi = HocKi;
    }

    public String getMaGV() {
        return MaGV;
    }

    public void setMaGV(String MaGV) {
        this.MaGV = MaGV;
    }

    public String getMaLop() {
        return MaLOP;
    }

    public void setMaLop(String MaLOP) {
        this.MaLOP = MaLOP;
    }

    public Integer getNam() {
        return Nam;
    }

    public void setNam(Integer Nam) {
        this.Nam = Nam;
    }

    public Integer getHocKi() {
        return HocKi;
    }

    public void setHocKi(Integer HocKi) {
        this.HocKi = HocKi;
    }

    @Override
    public String toString() {
        return "ChuNhiem{" + "MaGV=" + MaGV + ", MaLOP=" + MaLOP + ", Nam=" + Nam + ", HocKi=" + HocKi + '}';
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
        final ChuNhiem other = (ChuNhiem) obj;
        if (!Objects.equals(this.MaGV, other.MaGV)) {
            return false;
        }
        if (!Objects.equals(this.MaLOP, other.MaLOP)) {
            return false;
        }
        if (!Objects.equals(this.Nam, other.Nam)) {
            return false;
        }
        if (!Objects.equals(this.HocKi, other.HocKi)) {
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
        final ChuNhiem other = (ChuNhiem) obj;
        if (!Objects.equals(this.MaGV, other.MaGV)) {
            return false;
        }
        if (!Objects.equals(this.MaLOP, other.MaLOP)) {
            return false;
        }
        if (!Objects.equals(this.Nam, other.Nam)) {
            return false;
        }
        if (!Objects.equals(this.HocKi, other.HocKi)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public Model ChangeToNull() {
        if (this.HocKi != null) {
            if (this.HocKi <= 0) {
                this.HocKi = null;
            }
        }
        if (this.Nam != null) {
            if (this.Nam <= 0) {
                this.Nam = null;
            }
        }
        if (this.MaLOP != null) {
            if (this.MaLOP.isEmpty()) {
                this.MaLOP = null;
            }
        }
        if (this.MaGV != null) {
            if (this.MaGV.isEmpty()) {
                this.MaGV = null;
            }
        }
        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.HocKi == null) {
            this.HocKi = 0;
        }
        if (this.Nam == null) {
            this.Nam = 0;
        }
        if (this.MaLOP == null) {
            this.MaLOP = "";
        }
        if (this.MaGV == null) {
            this.MaGV = "";
        }
        return this;
    }

    @Override
    public Model clone() {
        return new ChuNhiem(MaGV, MaLOP, Nam, HocKi);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        ChuNhiem newChuNhiem = (ChuNhiem)newModel;
        this.HocKi = newChuNhiem.getHocKi();
        this.MaGV = newChuNhiem.getMaGV();
        this.MaLOP = newChuNhiem.getMaLop();
        this.Nam = newChuNhiem.getNam();
        
        return oldModel;
    }
}
