/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.HocKi_NamHoc;
import models.Model;

/**
 *
 * @author Computer
 */
public class HocKi_NamHocController extends BaseModelController {

    private static HocKi_NamHocController instance;

    private HocKi_NamHocController() {
    }

    public static synchronized HocKi_NamHocController getInstance() {
        if (instance == null) {
            instance = new HocKi_NamHocController();
        }
        return instance;
    }

    @Override
    public String CreateQueryAutoID() {
        return "";
    }

    @Override
    protected String ParseAutoIDFromResultSet(ResultSet resultSet) throws Exception {
        return "";
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new HocKi_NamHoc(resultSet.getInt("NAM"),
                resultSet.getInt("HOCKY"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM HOCKY_NAMHOC";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        HocKi_NamHoc hk_nh = (HocKi_NamHoc) model;

        return "INSERT INTO HOCKY_NAMHOC (NAM, HOCKY) "
                + " VALUES ( "
                + "" + hk_nh.getNam() + ","
                + "" + hk_nh.getHocKi() + ""
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        HocKi_NamHoc hk_nh_moi = (HocKi_NamHoc) newModel;
        HocKi_NamHoc hk_nh_cu = (HocKi_NamHoc) oldModel;

        return "UPDATE HOCKY_NAMHOC SET "
                + "NAM = " + hk_nh_moi.getNam() + ","
                + "HOCKY = " + hk_nh_moi.getHocKi() + ""
                + " WHERE NAM = " + hk_nh_cu.getNam() + " AND HOCKY = " + hk_nh_cu.getHocKi();
    }

    @Override
    public String CreateQueryDelete(Model model) {
        HocKi_NamHoc hk_nh = (HocKi_NamHoc) model;

        return "DELETE FROM HOCKY_NAMHOC "
                + " WHERE NAM = " + hk_nh.getNam() + " AND HOCKY = " + hk_nh.getHocKi();
    }

    @Override
    public String CreateQuerySearch(Model model) {
        HocKi_NamHoc hk_nh = (HocKi_NamHoc) model;

        String query = "SELECT * FROM HOCKY_NAMHOC ";

        String keyword = "";

        hk_nh.ChangeToNull();

        if (hk_nh.getNam() != null) {
            keyword += " NAM = " + hk_nh.getNam() + "";
        }

        if (hk_nh.getHocKi() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " HOCKY = " + hk_nh.getHocKi() + "";
        }

        if (keyword.equals("") == false) {
            query += "WHERE " + keyword;
        }

        return query;
    }

    @Override
    public String CreateQueryGetList(Accounts accounts) {
        return CreateQueryGetList();
    }
    
    public HocKi_NamHoc GetCurrentHocKi_NamHoc(){
        return new HocKi_NamHoc(2019, 1);
    }
}
