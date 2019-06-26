/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.ChuNhiem;
import models.Model;

/**
 *
 * @author Computer
 */
public class ChuNhiemController extends ModelController {

    private static ChuNhiemController instance;

    private ChuNhiemController() {
    }

    public static synchronized ChuNhiemController getInstance() {
        if (instance == null) {
            instance = new ChuNhiemController();
        }
        return instance;
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new ChuNhiem(resultSet.getString("MAGV"),
                resultSet.getString("MALOP"),
                resultSet.getInt("NAM"),
                resultSet.getInt("HOCKY"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM CHUNHIEM";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        ChuNhiem cn = (ChuNhiem) model;

        return "INSERT INTO CHUNHIEM (MAGV, MALOP, NAM, HOCKY) "
                + " VALUES ( "
                + "'" + cn.getMaGV() + "',"
                + "'" + cn.getMaLop() + "',"
                + "" + cn.getNam() + ","
                + "" + cn.getHocKi() + ""
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        ChuNhiem cn_moi = (ChuNhiem) newModel;
        ChuNhiem cn_cu = (ChuNhiem) oldModel;

        return "UPDATE CHUNHIEM SET "
                + "MAGV = '" + cn_moi.getMaGV() + "',"
                + "MALOP = '" + cn_moi.getMaLop() + "',"
                + "NAM = " + cn_moi.getNam() + ","
                + "HOCKY = " + cn_moi.getHocKi() + ""
                + " WHERE "
                + "MAGV = '" + cn_cu.getMaGV() + "' AND "
                + "MALOP = '" + cn_cu.getMaLop() + "' AND"
                + "NAM = " + cn_cu.getNam() + " AND "
                + "HOCKY = " + cn_cu.getHocKi();
    }

    @Override
    public String CreateQueryDelete(Model model) {
        ChuNhiem cn = (ChuNhiem) model;

        return "DELETE FROM CHUNHIEM WHERE "
                + "MAGV = '" + cn.getMaGV() + "' AND "
                + "MALOP = '" + cn.getMaLop() + "' AND "
                + "NAM = " + cn.getNam() + " AND "
                + "HOCKY = " + cn.getHocKi();
    }

    @Override
    public String CreateQuerySearch(Model model) {
        ChuNhiem cn = (ChuNhiem) model;

        String query = "SELECT * FROM CHUNHIEM ";

        String keyword = "";

        cn.ChangeToNull();

        if (cn.getMaGV() != null) {
            keyword += " MAGV LIKE '%" + cn.getMaGV() + "%'";
        }

        if (cn.getMaLop() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MALOP LIKE N'%" + cn.getMaLop() + "%'";
        }

        if (cn.getNam() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " NAM =" + cn.getNam() + "";
        }

        if (cn.getHocKi() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " HOCKY =" + cn.getHocKi() + "";
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
}
