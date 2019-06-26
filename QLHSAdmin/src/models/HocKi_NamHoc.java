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
public class HocKi_NamHoc implements Model {

    private Integer Nam;
    private Integer HocKi;

    public HocKi_NamHoc() {
    }

    public HocKi_NamHoc(Integer Nam, Integer HocKi) {
        this.Nam = Nam;
        this.HocKi = HocKi;
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
        return Nam + " -  " + HocKi;
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
        final HocKi_NamHoc other = (HocKi_NamHoc) obj;
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
        final HocKi_NamHoc other = (HocKi_NamHoc) obj;
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

        return this;
    }

    @Override
    public Model clone() {
        return new HocKi_NamHoc(Nam, HocKi);
    }

    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();

        HocKi_NamHoc newHocKi_NamHoc = (HocKi_NamHoc) newModel;
        this.HocKi = newHocKi_NamHoc.getHocKi();
        this.Nam = newHocKi_NamHoc.getNam();

        return oldModel;
    }
}
