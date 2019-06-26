/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.Hoc;
import models.Model;

/**
 *
 * @author Computer
 */
public class HocController extends ModelController {

    private static HocController instance;

    private HocController() {
    }

    public static synchronized HocController getInstance() {
        if (instance == null) {
            instance = new HocController();
        }
        return instance;
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new Hoc(resultSet.getString("maLOP"),
                resultSet.getString("MAHS"),
                resultSet.getInt("NAM"),
                resultSet.getInt("HOCKY"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM HOC";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        Hoc hoc = (Hoc) model;

        return "INSERT INTO HOC (MAHS, MALOP, NAM, HOCKY) "
                + " VALUES ( "
                + "'" + hoc.getMaHs() + "',"
                + "'" + hoc.getMaLop() + "',"
                + "" + hoc.getNam() + ", "
                + "" + hoc.getHocKi() + ""
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        Hoc hoc_moi = (Hoc) newModel;
        Hoc hoc_cu = (Hoc) oldModel;

        return "UPDATE HOC SET "
                + "MAHS = '" + hoc_moi.getMaHs() + "',"
                + "MALOP = '" + hoc_moi.getMaLop() + "',"
                + "NAM = " + hoc_moi.getNam() + ", "
                + "HOCKY = " + hoc_moi.getHocKi() + ""
                + " WHERE "
                + "MAHS = '" + hoc_cu.getMaHs() + "' AND "
                + "MALOP = '" + hoc_cu.getMaLop() + "' AND "
                + "NAM = " + hoc_cu.getNam() + " AND "
                + "HOCKY = " + hoc_cu.getHocKi() + "";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        Hoc hoc = (Hoc) model;

        return "DELETE FROM HOC "
                + " WHERE "
                + "MAHS = '" + hoc.getMaHs() + "' AND "
                + "MALOP = '" + hoc.getMaLop() + "' AND "
                + "NAM = " + hoc.getNam() + " AND "
                + "HOCKY = " + hoc.getHocKi() + "";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        Hoc hoc = (Hoc) model;

        String query = "SELECT * FROM HOC ";

        String keyword = "";

        hoc.ChangeToNull();

        if (hoc.getMaHs() != null) {
            keyword += " MAHS LIKE '%" + hoc.getMaHs() + "%'";
        }

        if (hoc.getMaLop() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MALOP LIKE '%" + hoc.getMaLop() + "%'";
        }

        if (hoc.getNam() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " NAM =" + hoc.getNam() + "";
        }

        if (hoc.getHocKi() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " HOCKY =" + hoc.getHocKi() + "";
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
