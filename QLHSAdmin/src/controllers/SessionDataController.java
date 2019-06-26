/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import models.Accounts;
import models.CO;
import models.ChuNhiem;
import models.GiangDay;
import models.GiaoVien;
import models.HanhKiem;
import models.Hoc;
import models.HocKi_NamHoc;
import models.HocLuc;
import models.HocSinh;
import models.KQ;
import models.KT;
import models.Lop;
import models.Model;
import models.MonHoc;
import models.PhuHuynh;
import models.SessionData;

/**
 *
 * @author Computer
 */
public class SessionDataController {

    private static SessionDataController instance;

    public synchronized static SessionDataController getInstance() {
        if (instance == null) {
            instance = new SessionDataController();
        }
        return instance;
    }

    private SessionDataController() {
    }

    private StringProperty contentLoading;
    private DoubleProperty progressLoading;

    public void setContentLoading(StringProperty contentLoading) {
        this.contentLoading = contentLoading;
    }

    public void setProgressLoading(DoubleProperty progressLoading) {
        this.progressLoading = progressLoading;
    }

    public ArrayList<Accounts> getListAccountses(Accounts accounts) {
        AccountsController.getInstance().setContentLoading(contentLoading);
        AccountsController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<Accounts>) ((ArrayList<? extends Model>) AccountsController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<CO> getListCOs(Accounts accounts) {
        COController.getInstance().setContentLoading(contentLoading);
        COController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<CO>) ((ArrayList<? extends Model>) COController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<ChuNhiem> getListChuNhiems(Accounts accounts) {
        ChuNhiemController.getInstance().setContentLoading(contentLoading);
        ChuNhiemController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<ChuNhiem>) ((ArrayList<? extends Model>) ChuNhiemController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<GiangDay> getListGiangDays(Accounts accounts) {
        GiangDayController.getInstance().setContentLoading(contentLoading);
        GiangDayController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<GiangDay>) ((ArrayList<? extends Model>) GiangDayController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<GiaoVien> getListGiaoViens(Accounts accounts) {
        GiaoVienController.getInstance().setContentLoading(contentLoading);
        GiaoVienController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<GiaoVien>) ((ArrayList<? extends Model>) GiaoVienController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<HanhKiem> getListHanhKiems(Accounts accounts) {
        HanhKiemController.getInstance().setContentLoading(contentLoading);
        HanhKiemController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<HanhKiem>) ((ArrayList<? extends Model>) HanhKiemController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<Hoc> getListHocs(Accounts accounts) {
        HocController.getInstance().setContentLoading(contentLoading);
        HocController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<Hoc>) ((ArrayList<? extends Model>) HocController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<HocKi_NamHoc> getListHocKi_NamHocs(Accounts accounts) {
        HocKi_NamHocController.getInstance().setContentLoading(contentLoading);
        HocKi_NamHocController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<HocKi_NamHoc>) ((ArrayList<? extends Model>) HocKi_NamHocController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<HocLuc> getListHocLucs(Accounts accounts) {
        HocLucController.getInstance().setContentLoading(contentLoading);
        HocLucController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<HocLuc>) ((ArrayList<? extends Model>) HocLucController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<HocSinh> getListHocSinhs(Accounts accounts) {
        HocSinhController.getInstance().setContentLoading(contentLoading);
        HocSinhController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<KQ> getListKQs(Accounts accounts) {
        KQController.getInstance().setContentLoading(contentLoading);
        KQController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<KQ>) ((ArrayList<? extends Model>) KQController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<KT> getListKTs(Accounts accounts) {
        KTController.getInstance().setContentLoading(contentLoading);
        KTController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<KT>) ((ArrayList<? extends Model>) KTController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<Lop> getListLops(Accounts accounts) {
        LopController.getInstance().setContentLoading(contentLoading);
        LopController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<Lop>) ((ArrayList<? extends Model>) LopController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<MonHoc> getListMonHocs(Accounts accounts) {
        MonHocController.getInstance().setContentLoading(contentLoading);
        MonHocController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<MonHoc>) ((ArrayList<? extends Model>) MonHocController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<PhuHuynh> getListPhuHuynhs(Accounts accounts) {
        PhuHuynhController.getInstance().setContentLoading(contentLoading);
        PhuHuynhController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<PhuHuynh>) ((ArrayList<? extends Model>) PhuHuynhController
                .getInstance()
                .GetList(accounts));
    }

    public ArrayList<Accounts> getListAccountses() {
        AccountsController.getInstance().setContentLoading(contentLoading);
        AccountsController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<Accounts>) ((ArrayList<? extends Model>) AccountsController
                .getInstance()
                .GetList());
    }

    public ArrayList<CO> getListCOs() {
        COController.getInstance().setContentLoading(contentLoading);
        COController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<CO>) ((ArrayList<? extends Model>) COController
                .getInstance()
                .GetList());
    }

    public ArrayList<ChuNhiem> getListChuNhiems() {
        ChuNhiemController.getInstance().setContentLoading(contentLoading);
        ChuNhiemController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<ChuNhiem>) ((ArrayList<? extends Model>) ChuNhiemController
                .getInstance()
                .GetList());
    }

    public ArrayList<GiangDay> getListGiangDays() {
        GiangDayController.getInstance().setContentLoading(contentLoading);
        GiangDayController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<GiangDay>) ((ArrayList<? extends Model>) GiangDayController
                .getInstance()
                .GetList());
    }

    public ArrayList<GiaoVien> getListGiaoViens() {
        GiaoVienController.getInstance().setContentLoading(contentLoading);
        GiaoVienController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<GiaoVien>) ((ArrayList<? extends Model>) GiaoVienController
                .getInstance()
                .GetList());
    }

    public ArrayList<HanhKiem> getListHanhKiems() {
        HanhKiemController.getInstance().setContentLoading(contentLoading);
        HanhKiemController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<HanhKiem>) ((ArrayList<? extends Model>) HanhKiemController
                .getInstance()
                .GetList());
    }

    public ArrayList<Hoc> getListHocs() {
        HocController.getInstance().setContentLoading(contentLoading);
        HocController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<Hoc>) ((ArrayList<? extends Model>) HocController
                .getInstance()
                .GetList());
    }

    public ArrayList<HocKi_NamHoc> getListHocKi_NamHocs() {
        HocKi_NamHocController.getInstance().setContentLoading(contentLoading);
        HocKi_NamHocController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<HocKi_NamHoc>) ((ArrayList<? extends Model>) HocKi_NamHocController
                .getInstance()
                .GetList());
    }

    public ArrayList<HocLuc> getListHocLucs() {
        HocLucController.getInstance().setContentLoading(contentLoading);
        HocLucController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<HocLuc>) ((ArrayList<? extends Model>) HocLucController
                .getInstance()
                .GetList());
    }

    public ArrayList<HocSinh> getListHocSinhs() {
        HocSinhController.getInstance().setContentLoading(contentLoading);
        HocSinhController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController
                .getInstance()
                .GetList());
    }

    public ArrayList<KQ> getListKQs() {
        KQController.getInstance().setContentLoading(contentLoading);
        KQController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<KQ>) ((ArrayList<? extends Model>) KQController
                .getInstance()
                .GetList());
    }

    public ArrayList<KT> getListKTs() {
        KTController.getInstance().setContentLoading(contentLoading);
        KTController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<KT>) ((ArrayList<? extends Model>) KTController
                .getInstance()
                .GetList());
    }

    public ArrayList<Lop> getListLops() {
        LopController.getInstance().setContentLoading(contentLoading);
        LopController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<Lop>) ((ArrayList<? extends Model>) LopController
                .getInstance()
                .GetList());
    }

    public ArrayList<MonHoc> getListMonHocs() {
        MonHocController.getInstance().setContentLoading(contentLoading);
        MonHocController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<MonHoc>) ((ArrayList<? extends Model>) MonHocController
                .getInstance()
                .GetList());
    }

    public ArrayList<PhuHuynh> getListPhuHuynhs() {
        PhuHuynhController.getInstance().setContentLoading(contentLoading);
        PhuHuynhController.getInstance().setProgressLoading(progressLoading);

        return (ArrayList<PhuHuynh>) ((ArrayList<? extends Model>) PhuHuynhController
                .getInstance()
                .GetList());
    }

    public SessionData getSessionData() {
        SessionData sessionData = new SessionData();
        sessionData.setIsInitializing(true);

        sessionData.getOlAccountses().clear();
        sessionData.getOlAccountses().addAll(this.getListAccountses());

        sessionData.getOlCOs().clear();
        sessionData.getOlCOs().addAll(this.getListCOs());

        sessionData.getOlChuNhiems().clear();
        sessionData.getOlChuNhiems().addAll(this.getListChuNhiems());

        sessionData.getOlGiangDays().clear();
        sessionData.getOlGiangDays().addAll(this.getListGiangDays());

        sessionData.getOlGiaoViens().clear();
        sessionData.getOlGiaoViens().addAll(this.getListGiaoViens());

        sessionData.getOlHanhKiems().clear();
        sessionData.getOlHanhKiems().addAll(this.getListHanhKiems());

        sessionData.getOlHocs().clear();
        sessionData.getOlHocs().addAll(this.getListHocs());

        sessionData.getOlHocKi_NamHocs().clear();
        sessionData.getOlHocKi_NamHocs().addAll(this.getListHocKi_NamHocs());

        sessionData.getOlHocLucs().clear();
        sessionData.getOlHocLucs().addAll(this.getListHocLucs());

        sessionData.getOlHocSinhs().clear();
        sessionData.getOlHocSinhs().addAll(this.getListHocSinhs());

        sessionData.getOlKQs().clear();
        sessionData.getOlKQs().addAll(this.getListKQs());

        sessionData.getOlKTs().clear();
        sessionData.getOlKTs().addAll(this.getListKTs());

        sessionData.getOlLops().clear();
        sessionData.getOlLops().addAll(this.getListLops());

        sessionData.getOlMonHocs().clear();
        sessionData.getOlMonHocs().addAll(this.getListMonHocs());

        sessionData.getOlPhuHuynhs().clear();
        sessionData.getOlPhuHuynhs().addAll(this.getListPhuHuynhs());

        sessionData.setIsInitializing(false);
        return sessionData;
    }

    public SessionData getSessionData(Accounts accounts) {
        SessionData sessionData = new SessionData();
        sessionData.setIsInitializing(true);

        sessionData.getOlAccountses().clear();
        sessionData.getOlAccountses().addAll(this.getListAccountses(accounts));

        sessionData.getOlCOs().clear();
        sessionData.getOlCOs().addAll(this.getListCOs(accounts));

        sessionData.getOlChuNhiems().clear();
        sessionData.getOlChuNhiems().addAll(this.getListChuNhiems(accounts));

        sessionData.getOlGiangDays().clear();
        sessionData.getOlGiangDays().addAll(this.getListGiangDays(accounts));

        sessionData.getOlGiaoViens().clear();
        sessionData.getOlGiaoViens().addAll(this.getListGiaoViens(accounts));

        sessionData.getOlHanhKiems().clear();
        sessionData.getOlHanhKiems().addAll(this.getListHanhKiems(accounts));

        sessionData.getOlHocs().clear();
        sessionData.getOlHocs().addAll(this.getListHocs(accounts));

        sessionData.getOlHocKi_NamHocs().clear();
        sessionData.getOlHocKi_NamHocs().addAll(this.getListHocKi_NamHocs(accounts));

        sessionData.getOlHocLucs().clear();
        sessionData.getOlHocLucs().addAll(this.getListHocLucs(accounts));

        sessionData.getOlHocSinhs().clear();
        sessionData.getOlHocSinhs().addAll(this.getListHocSinhs(accounts));

        sessionData.getOlKQs().clear();
        sessionData.getOlKQs().addAll(this.getListKQs(accounts));

        sessionData.getOlKTs().clear();
        sessionData.getOlKTs().addAll(this.getListKTs(accounts));

        sessionData.getOlLops().clear();
        sessionData.getOlLops().addAll(this.getListLops(accounts));

        sessionData.getOlMonHocs().clear();
        sessionData.getOlMonHocs().addAll(this.getListMonHocs(accounts));

        sessionData.getOlPhuHuynhs().clear();
        sessionData.getOlPhuHuynhs().addAll(this.getListPhuHuynhs(accounts));

        sessionData.setIsInitializing(false);
        return sessionData;
    }
}
