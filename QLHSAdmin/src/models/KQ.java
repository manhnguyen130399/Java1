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
public class KQ implements Model {

    private String MaHS;
    private String MaLoaiHL;
    private Integer HocKi;
    private String LoaiHK;
    private Integer Nam;
    private Double DTB;

    public KQ() {
    }

    public KQ(String MaHS, String MaLoaiHL, Integer HocKi, String LoaiHK, Integer Nam) {
        this.MaHS = MaHS;
        this.MaLoaiHL = MaLoaiHL;
        this.HocKi = HocKi;
        this.LoaiHK = LoaiHK;
        this.Nam = Nam;
    }

    public KQ(String MaHS, String MaLoaiHL, Integer HocKi, String LoaiHK, Integer Nam, Double DTB) {
        this.MaHS = MaHS;
        this.MaLoaiHL = MaLoaiHL;
        this.HocKi = HocKi;
        this.LoaiHK = LoaiHK;
        this.Nam = Nam;
        this.DTB = DTB;
    }

    public String getMaHS() {
        return MaHS;
    }

    public void setMaHS(String MaHS) {
        this.MaHS = MaHS;
    }

    public String getMaLoaiHL() {
        return MaLoaiHL;
    }

    public void setMaLoaiHL(String MaLoaiHL) {
        this.MaLoaiHL = MaLoaiHL;
    }

    public Integer getHocKi() {
        return HocKi;
    }

    public void setHocKi(Integer HocKi) {
        this.HocKi = HocKi;
    }

    public String getLoaiHK() {
        return LoaiHK;
    }

    public void setLoaiHK(String LoaiHK) {
        this.LoaiHK = LoaiHK;
    }

    public Integer getNam() {
        return Nam;
    }

    public void setNam(Integer Nam) {
        this.Nam = Nam;
    }

    public Double getDTB() {
        return DTB;
    }

    public void setDTB(Double DTB) {
        this.DTB = DTB;
    }

    @Override
    public String toString() {
        return "KQ{" + "MaHS=" + MaHS + ", MaLoaiHL=" + MaLoaiHL + ", HocKi=" + HocKi + ", LoaiHK=" + LoaiHK + ", Nam=" + Nam + ", DTB=" + DTB + '}';
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
        final KQ other = (KQ) obj;
        if (!Objects.equals(this.MaHS, other.MaHS)) {
            return false;
        }
        if (!Objects.equals(this.MaLoaiHL, other.MaLoaiHL)) {
            return false;
        }
        if (!Objects.equals(this.HocKi, other.HocKi)) {
            return false;
        }
        if (!Objects.equals(this.LoaiHK, other.LoaiHK)) {
            return false;
        }
        if (!Objects.equals(this.Nam, other.Nam)) {
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
        final KQ other = (KQ) obj;
        if (!Objects.equals(this.MaHS, other.MaHS)) {
            return false;
        }
        if (!Objects.equals(this.MaLoaiHL, other.MaLoaiHL)) {
            return false;
        }
        if (!Objects.equals(this.LoaiHK, other.LoaiHK)) {
            return false;
        }
        if (!Objects.equals(this.HocKi, other.HocKi)) {
            return false;
        }
        if (!Objects.equals(this.Nam, other.Nam)) {
            return false;
        }
        if (!Objects.equals(this.DTB, other.DTB)) {
            return false;
        }
        return true;
    }

    @Override
    public Model ChangeToNull() {
        if (this.DTB != null) {
            this.DTB = null;
        }
        if (this.LoaiHK != null) {
            if (this.LoaiHK.isEmpty()) {
                this.LoaiHK = null;
            }
        }
        if (this.MaHS != null) {
            if (this.MaHS.isEmpty()) {
                this.MaHS = null;
            }
        }
        if (this.MaLoaiHL != null) {
            if (this.MaLoaiHL.isEmpty()) {
                this.MaLoaiHL = null;
            }
        }
        if (this.HocKi != null) {
            if (this.HocKi < 0) {
                this.HocKi = null;
            }
        }
        if (this.Nam != null) {

            this.Nam = null;

        }

        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.DTB == null) {
            this.DTB = 0.0;
        }
        if (this.LoaiHK == null) {
            this.LoaiHK = "";
        }
        if (this.MaHS == null) {
            this.MaHS = "";
        }
        if (this.MaLoaiHL == null) {
            this.MaLoaiHL = "";
        }
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
        return new KQ(MaHS, MaLoaiHL, HocKi, LoaiHK, Nam, DTB);
    }

    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();

        KQ newKq = (KQ) newModel;
        this.DTB = newKq.getDTB();
        this.HocKi = newKq.getHocKi();
        this.LoaiHK = newKq.getLoaiHK();
        this.MaHS = newKq.getMaHS();
        this.MaLoaiHL = newKq.getMaLoaiHL();
        this.Nam = newKq.getNam();

        return oldModel;
    }
}
