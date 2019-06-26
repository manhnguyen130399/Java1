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
public class GiangDay implements Model {

    private String MaGV;
    private String MaMH;
    private String MaLop;
    private Integer Nam;
    private Integer HocKi;

    public GiangDay() {
    }

    public GiangDay(String MaGV, String MaMH, String MaLop, Integer Nam, Integer HocKi) {
        this.MaGV = MaGV;
        this.MaMH = MaMH;
        this.MaLop = MaLop;
        this.Nam = Nam;
        this.HocKi = HocKi;
    }

    public String getMaGV() {
        return MaGV;
    }

    public void setMaGV(String MaGV) {
        this.MaGV = MaGV;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String MaMH) {
        this.MaMH = MaMH;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
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
        return "GiangDay{" + "MaGV=" + MaGV + ", MaMH=" + MaMH + ", MaLop=" + MaLop + ", Nam=" + Nam + ", HocKi=" + HocKi + '}';
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
        final GiangDay other = (GiangDay) obj;
        if (!Objects.equals(this.MaGV, other.MaGV)) {
            return false;
        }
        if (!Objects.equals(this.MaMH, other.MaMH)) {
            return false;
        }
        if (!Objects.equals(this.MaLop, other.MaLop)) {
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
        final GiangDay other = (GiangDay) obj;
        if (!Objects.equals(this.MaGV, other.MaGV)) {
            return false;
        }
        if (!Objects.equals(this.MaMH, other.MaMH)) {
            return false;
        }
        if (!Objects.equals(this.MaLop, other.MaLop)) {
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
        if (this.MaGV != null) {
            if (this.MaGV.isEmpty()) {
                this.MaGV = null;
            }
        }
        if (this.MaMH != null) {
            if (this.MaMH.isEmpty()) {
                this.MaMH = null;
            }
        }
        if (this.MaMH != null) {
            if (this.MaMH.isEmpty()) {
                this.MaMH = null;
            }
        }
        if (this.Nam != null) {
            if (this.Nam < 0) {
                this.Nam = null;
            }
        }
        if (this.HocKi != null) {
            if (this.HocKi < 0) {
                this.HocKi = null;
            }
        }

        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.MaGV == null) {
            this.MaGV = "";
        }
        if (this.MaMH == null) {
            this.MaMH = "";
        }
        if (this.MaMH == null) {
            this.MaMH = "";
        }
        if (this.Nam == null) {
            this.Nam = 0;
        }
        if (this.HocKi == null) {
            this.HocKi = 0;
        }

        return this;
    }

    @Override
    public Model clone() {
        return new GiangDay(MaGV, MaMH, MaLop, Nam, HocKi);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        GiangDay newGiangDay = (GiangDay)newModel;
        this.HocKi = newGiangDay.getHocKi();
        this.MaGV = newGiangDay.getMaGV();
        this.MaLop = newGiangDay.getMaLop();
        this.MaMH = newGiangDay.getMaMH();
        this.Nam = newGiangDay.getNam();
        
        return oldModel;
    }
}
