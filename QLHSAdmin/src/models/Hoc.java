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
public class Hoc implements Model {

    private String MaLop;
    private String MaHs;
    private Integer Nam;
    private Integer HocKi;

    public Hoc() {
    }

    public Hoc(String MaLop, String MaHs, Integer Nam, Integer HocKi) {
        this.MaLop = MaLop;
        this.MaHs = MaHs;
        this.Nam = Nam;
        this.HocKi = HocKi;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getMaHs() {
        return MaHs;
    }

    public void setMaHs(String MaHs) {
        this.MaHs = MaHs;
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
        return "Hoc{" + "MaLop=" + MaLop + ", MaHs=" + MaHs + ", Nam=" + Nam + ", HocKi=" + HocKi + '}';
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
        final Hoc other = (Hoc) obj;
        if (!Objects.equals(this.MaLop, other.MaLop)) {
            return false;
        }
        if (!Objects.equals(this.MaHs, other.MaHs)) {
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
        final Hoc other = (Hoc) obj;
        if (!Objects.equals(this.MaLop, other.MaLop)) {
            return false;
        }
        if (!Objects.equals(this.MaHs, other.MaHs)) {
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
        if (this.MaHs != null) {
            if (this.MaHs.isEmpty()) {
                this.MaHs = null;
            }
        }
        if (this.MaLop != null) {
            if (this.MaLop.isEmpty()) {
                this.MaLop = null;
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
        if (this.MaHs == null) {
            this.MaHs = "";
        }
        if (this.MaLop == null) {
            this.MaLop = "";
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
        return new Hoc(MaLop, MaHs, Nam, HocKi);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        Hoc newHoc = (Hoc)newModel;
        this.HocKi = newHoc.getHocKi();
        this.MaHs = newHoc.getMaHs();
        this.MaLop = newHoc.getMaLop();
        this.Nam = newHoc.getNam();
        
        return oldModel;
    }
}
