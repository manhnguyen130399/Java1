/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.AccountsController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Computer
 */
public final class SessionData {

    private final ObservableList<Accounts> olAccountses;
    private final ObservableList<CO> olCOs;
    private final ObservableList<ChuNhiem> olChuNhiems;
    private final ObservableList<GiangDay> olGiangDays;
    private final ObservableList<GiaoVien> olGiaoViens;
    private final ObservableList<HanhKiem> olHanhKiems;
    private final ObservableList<Hoc> olHocs;
    private final ObservableList<HocKi_NamHoc> olHocKi_NamHocs;
    private final ObservableList<HocLuc> olHocLucs;
    private final ObservableList<HocSinh> olHocSinhs;
    private final ObservableList<KQ> olKQs;
    private final ObservableList<KT> olKTs;
    private final ObservableList<Lop> olLops;
    private final ObservableList<MonHoc> olMonHocs;
    private final ObservableList<PhuHuynh> olPhuHuynhs;

    private boolean isInitializing;
    private final ObjectProperty<Accounts> accountsLogined;

    public SessionData() {
        this.olAccountses = FXCollections.observableArrayList();
        this.olCOs = FXCollections.observableArrayList();
        this.olChuNhiems = FXCollections.observableArrayList();
        this.olGiangDays = FXCollections.observableArrayList();
        this.olGiaoViens = FXCollections.observableArrayList();
        this.olHanhKiems = FXCollections.observableArrayList();
        this.olHocs = FXCollections.observableArrayList();
        this.olHocKi_NamHocs = FXCollections.observableArrayList();
        this.olHocLucs = FXCollections.observableArrayList();
        this.olHocSinhs = FXCollections.observableArrayList();
        this.olKQs = FXCollections.observableArrayList();
        this.olKTs = FXCollections.observableArrayList();
        this.olLops = FXCollections.observableArrayList();
        this.olMonHocs = FXCollections.observableArrayList();
        this.olPhuHuynhs = FXCollections.observableArrayList();

        this.isInitializing = true;
        this.accountsLogined = new SimpleObjectProperty<>(null);

        ///Thêm các sự kiện vào session data///
        this.accountsLogined.addListener((observable, oldValue, newValue) -> {
            if (this.isInitializing == false) {
                if (newValue.equalsAll(oldValue) == false) {
                    AccountsController.getInstance().Edit(oldValue, newValue);

                    int index = olAccountses.indexOf(oldValue);
                    if (index >= 0) {
                        olAccountses.get(index).ReplaceBy(newValue);
                    }
                }
            }
        });
    }

    public void ClearData() {
        this.olAccountses.clear();
        this.olCOs.clear();
        this.olChuNhiems.clear();
        this.olGiangDays.clear();
        this.olGiaoViens.clear();
        this.olHanhKiems.clear();
        this.olHocs.clear();
        this.olHocKi_NamHocs.clear();
        this.olHocLucs.clear();
        this.olHocSinhs.clear();
        this.olKQs.clear();
        this.olKTs.clear();
        this.olLops.clear();
        this.olMonHocs.clear();
        this.olPhuHuynhs.clear();
    }

    public void CopyOf(SessionData sessionData) {
        this.ClearData();
        this.olAccountses.addAll(sessionData.getOlAccountses());
        this.olCOs.addAll(sessionData.getOlCOs());
        this.olChuNhiems.addAll(sessionData.getOlChuNhiems());
        this.olGiangDays.addAll(sessionData.getOlGiangDays());
        this.olGiaoViens.addAll(sessionData.getOlGiaoViens());
        this.olHanhKiems.addAll(sessionData.getOlHanhKiems());
        this.olHocs.addAll(sessionData.getOlHocs());
        this.olHocKi_NamHocs.addAll(sessionData.getOlHocKi_NamHocs());
        this.olHocLucs.addAll(sessionData.getOlHocLucs());
        this.olHocSinhs.addAll(sessionData.getOlHocSinhs());
        this.olKQs.addAll(sessionData.getOlKQs());
        this.olKTs.addAll(sessionData.getOlKTs());
        this.olLops.addAll(sessionData.getOlLops());
        this.olMonHocs.addAll(sessionData.getOlMonHocs());
        this.olPhuHuynhs.addAll(sessionData.getOlPhuHuynhs());
    }

    public ObservableList<Accounts> getOlAccountses() {
        return olAccountses;
    }

    public ObservableList<CO> getOlCOs() {
        return olCOs;
    }

    public ObservableList<ChuNhiem> getOlChuNhiems() {
        return olChuNhiems;
    }

    public ObservableList<GiangDay> getOlGiangDays() {
        return olGiangDays;
    }

    public ObservableList<GiaoVien> getOlGiaoViens() {
        return olGiaoViens;
    }

    public ObservableList<HanhKiem> getOlHanhKiems() {
        return olHanhKiems;
    }

    public ObservableList<Hoc> getOlHocs() {
        return olHocs;
    }

    public ObservableList<HocKi_NamHoc> getOlHocKi_NamHocs() {
        return olHocKi_NamHocs;
    }

    public ObservableList<HocLuc> getOlHocLucs() {
        return olHocLucs;
    }

    public ObservableList<HocSinh> getOlHocSinhs() {
        return olHocSinhs;
    }

    public ObservableList<KQ> getOlKQs() {
        return olKQs;
    }

    public ObservableList<KT> getOlKTs() {
        return olKTs;
    }

    public ObservableList<Lop> getOlLops() {
        return olLops;
    }

    public ObservableList<MonHoc> getOlMonHocs() {
        return olMonHocs;
    }

    public ObservableList<PhuHuynh> getOlPhuHuynhs() {
        return olPhuHuynhs;
    }

    public Boolean getIsInitializing() {
        return isInitializing;
    }

    public void setIsInitializing(boolean isInitializing) {
        this.isInitializing = isInitializing;
    }

    public ObjectProperty<Accounts> getAccountsLogined() {
        return accountsLogined;
    }

    public void setAccountsLogined(Accounts accountsLogined) {
        this.accountsLogined.set(accountsLogined);
    }
}
