/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.GiangDay;
import models.Model;

/**
 *
 * @author Computer
 */
public class GiangDayController extends ModelController {

    private static GiangDayController instance;

    private GiangDayController() {
    }

    public static synchronized GiangDayController getInstance() {
        if (instance == null) {
            instance = new GiangDayController();
        }
        return instance;
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new GiangDay(resultSet.getString("MAGV"),
                resultSet.getString("MAMH"),
                resultSet.getString("MALOP"),
                resultSet.getInt("NAM"),
                resultSet.getInt("HOCKY"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM GIANGDAY";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        GiangDay gd = (GiangDay) model;

        return "INSERT INTO GIANGDAY (MAGV, MAMH, MALOP, NAM, HOCKY) "
                + " VALUES ( "
                + "N'" + gd.getMaGV() + "', "
                + "N'" + gd.getMaMH() + "', "
                + "N'" + gd.getMaLop() + "', "
                + "" + gd.getNam() + ", "
                + "" + gd.getHocKi() + ""
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        GiangDay gd_moi = (GiangDay) newModel;
        GiangDay gd_cu = (GiangDay) oldModel;

        return "UPDATE GIANGDAY SET "
                + "MAGV = N'" + gd_moi.getMaGV() + "',"
                + "MAMH = N'" + gd_moi.getMaMH() + "', "
                + "MALOP = N'" + gd_moi.getMaLop() + "', "
                + "NAM = " + gd_moi.getNam() + ", "
                + "HOCKY = " + gd_moi.getHocKi() + ""
                + " WHERE "
                + "MAGV = N'" + gd_cu.getMaGV() + "' AND "
                + "MAMH = N'" + gd_cu.getMaMH() + "' AND "
                + "MALOP = N'" + gd_cu.getMaLop() + "' AND "
                + "NAM = " + gd_cu.getNam() + " AND "
                + "HOCKY = " + gd_cu.getHocKi() + "";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        GiangDay gd = (GiangDay) model;

        return "DELETE FROM GIANGDAY "
                + " WHERE "
                + "MAGV = N'" + gd.getMaGV() + "' AND "
                + "MAMH = N'" + gd.getMaMH() + "' AND "
                + "MALOP = N'" + gd.getMaLop() + "' AND "
                + "NAM = " + gd.getNam() + " AND "
                + "HOCKY = " + gd.getHocKi() + "";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        GiangDay gd = (GiangDay) model;

        String query = "SELECT * FROM GIANGDAY ";

        String keyword = "";

        gd.ChangeToNull();

        if (gd.getMaGV() != null) {
            keyword += " MAGV LIKE '%" + gd.getMaGV() + "%'";
        }

        if (gd.getMaMH() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MAMH LIKE '%" + gd.getMaMH() + "%'";
        }

        if (gd.getMaLop() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MALOP LIKE '%" + gd.getMaLop() + "%'";
        }

        if (gd.getNam() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " NAM = " + gd.getNam() + "";
        }

        if (gd.getHocKi() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " HOCKY = " + gd.getHocKi() + "";
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
