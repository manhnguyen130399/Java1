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
public class KT implements Model {

    private String MaMH;
    private String LoaiKT;
    private Double Diem;
    private String MaGV;
    private String MaLop;
    private Integer Nam;
    private Integer HocKi;
    private Double HeSo;
    private String MaHS;

    public KT() {
    }

    public KT(String MaMH, String MaGV, String MaLop, Integer Nam, Integer HocKi, String MaHS, String LoaiKT) {
        this.MaMH = MaMH;
        this.MaGV = MaGV;
        this.MaLop = MaLop;
        this.Nam = Nam;
        this.HocKi = HocKi;
        this.MaHS = MaHS;
        this.LoaiKT = LoaiKT;
    }

    public KT(String MaMH, String LoaiKT, Double Diem, String MaGV, String MaLop, Integer Nam, Integer HocKi, Double HeSo, String MaHS) {
        this.MaMH = MaMH;
        this.LoaiKT = LoaiKT;
        this.Diem = Diem;
        this.MaGV = MaGV;
        this.MaLop = MaLop;
        this.Nam = Nam;
        this.HocKi = HocKi;
        this.HeSo = HeSo;
        this.MaHS = MaHS;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String MaMH) {
        this.MaMH = MaMH;
    }

    public String getLoaiKT() {
        return LoaiKT;
    }

    public void setLoaiKT(String LoaiKT) {
        this.LoaiKT = LoaiKT;
    }

    public Double getDiem() {
        return Diem;
    }

    public void setDiem(Double Diem) {
        this.Diem = Diem;
    }

    public String getMaGV() {
        return MaGV;
    }

    public void setMaGV(String MaGV) {
        this.MaGV = MaGV;
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

    public Double getHeSo() {
        return HeSo;
    }

    public void setHeSo(Double HeSo) {
        this.HeSo = HeSo;
    }

    public String getMaHS() {
        return MaHS;
    }

    public void setMaHS(String MaHS) {
        this.MaHS = MaHS;
    }

    @Override
    public String toString() {
        return "KT{" + "MaMH=" + MaMH + ", LoaiKT=" + LoaiKT + ", Diem=" + Diem + ", MaGV=" + MaGV + ", MaLop=" + MaLop + ", Nam=" + Nam + ", HocKi=" + HocKi + ", HeSo=" + HeSo + ", MaHS=" + MaHS + '}';
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
        final KT other = (KT) obj;
        if (!Objects.equals(this.MaMH, other.MaMH)) {
            return false;
        }
        if (!Objects.equals(this.MaGV, other.MaGV)) {
            return false;
        }
        if (!Objects.equals(this.MaLop, other.MaLop)) {
            return false;
        }
        if (!Objects.equals(this.MaHS, other.MaHS)) {
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
        final KT other = (KT) obj;
        if (!Objects.equals(this.MaMH, other.MaMH)) {
            return false;
        }
        if (!Objects.equals(this.LoaiKT, other.LoaiKT)) {
            return false;
        }
        if (!Objects.equals(this.MaGV, other.MaGV)) {
            return false;
        }
        if (!Objects.equals(this.MaLop, other.MaLop)) {
            return false;
        }
        if (!Objects.equals(this.MaHS, other.MaHS)) {
            return false;
        }
        if (!Objects.equals(this.Diem, other.Diem)) {
            return false;
        }
        if (!Objects.equals(this.Nam, other.Nam)) {
            return false;
        }
        if (!Objects.equals(this.HocKi, other.HocKi)) {
            return false;
        }
        if (!Objects.equals(this.HeSo, other.HeSo)) {
            return false;
        }
        return true;
    }

    

    @Override
    public Model ChangeToNull() {
        if (this.Diem != null) {
            this.Diem = null;
        }
        if (this.LoaiKT != null) {
            if (this.LoaiKT.isEmpty()) {
                this.LoaiKT = null;
            }
        }
        if (this.MaMH != null) {
            if (this.MaMH.isEmpty()) {
                this.MaMH = null;
            }
        }
        if (this.HeSo != null) {
            if (this.HeSo < 0.0) {
                this.HeSo = null;
            }
        }
        if (this.HocKi != null) {
            if (this.HocKi.doubleValue() < 0) {
                this.HocKi = null;
            }
        }
        if (this.MaGV != null) {
            if (this.MaGV.isEmpty()) {
                this.MaGV = null;
            }
        }
        if (this.MaLop != null) {
            if (this.MaLop.isEmpty()) {
                this.MaLop = null;
            }
        }
        if (this.Nam != null) {
            if (this.Nam.doubleValue() < 0) {
                this.Nam = null;
            }
        }
        if (this.MaHS != null) {
            if (this.MaHS.isEmpty()) {
                this.MaHS = null;
            }
        }

        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.Diem == null) {
            this.Diem = 0.0;
        }
        if (this.LoaiKT == null) {
            this.LoaiKT = "";
        }
        if (this.MaMH == null) {
            this.MaMH = "";
        }
        if (this.HeSo == null) {
            this.HeSo = 0.0;
        }
        if (this.HocKi == null) {
            this.HocKi = 0;
        }
        if (this.MaGV == null) {
            this.MaGV = "";
        }
        if (this.MaHS == null) {
            this.MaHS = "";
        }
        if (this.MaLop == null) {
            this.MaLop = "";
        }
        if (this.Nam == null) {
            this.Nam = 0;
        }
        return this;
    }

    @Override
    public Model clone() {
        return new KT(MaMH, LoaiKT, Diem, MaGV, MaLop, Nam, HocKi, HeSo, MaHS);
    }
    
    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        KT newKt = (KT)newModel;
        this.Diem = newKt.getDiem();
        this.HeSo = newKt.getHeSo();
        this.HocKi = newKt.getHocKi();
        this.LoaiKT = newKt.getLoaiKT();
        this.MaGV = newKt.getMaGV();
        this.MaHS = newKt.getMaHS();
        this.MaLop = newKt.getMaLop();
        this.MaMH = newKt.getMaMH();
        this.Nam = newKt.getNam();
        
        return oldModel;
    }
}
