/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.KT;
import models.Model;

/**
 *
 * @author Computer
 */
public class KTController extends ModelController {

    private static KTController instance;

    private KTController() {
    }

    public static synchronized KTController getInstance() {
        if (instance == null) {
            instance = new KTController();
        }
        return instance;
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new KT(resultSet.getString("MAMH"),
                resultSet.getString("LOAIKT"),
                resultSet.getDouble("DIEM"),
                resultSet.getString("MAGV"),
                resultSet.getString("MALOP"),
                resultSet.getInt("NAM"),
                resultSet.getInt("HOCKY"),
                resultSet.getDouble("HESO"),
                resultSet.getString("MAHS"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM KT";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        KT k_t = (KT) model;

        return "INSERT INTO KT (MAMH, MAGV, MAHS, MALOP, NAM, HOCKY, DIEM, HESO, LOAIKT) "
                + " VALUES ( "
                + "'" + k_t.getMaMH() + "',"
                + "'" + k_t.getMaGV() + "',"
                + "'" + k_t.getMaHS() + "',"
                + "'" + k_t.getMaLop() + "',"
                + "" + k_t.getNam() + ","
                + "" + k_t.getHocKi() + ","
                + "" + k_t.getDiem() + ","
                + "" + k_t.getHeSo() + ","
                + "N'" + k_t.getLoaiKT() + "'"
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        KT k_t_moi = (KT) newModel;
        KT k_t_cu = (KT) oldModel;

        return "UPDATE KT SET "
                + " MAMH = '" + k_t_moi.getMaMH() + "',"
                + " MAGV = '" + k_t_moi.getMaGV() + "',"
                + " MAHS = '" + k_t_moi.getMaHS() + "',"
                + " MALOP = '" + k_t_moi.getMaLop() + "',"
                + " NAM = " + k_t_moi.getNam() + ","
                + " HOCKY = " + k_t_moi.getHocKi() + ","
                + " DIEM = " + k_t_moi.getDiem() + ","
                + " HESO = " + k_t_moi.getHeSo() + ","
                + " LOAIKT = N'" + k_t_moi.getLoaiKT() + "'"
                + " WHERE "
                + " MAMH = '" + k_t_cu.getMaMH() + "' AND "
                + " MAGV = '" + k_t_cu.getMaGV() + "' AND "
                + " MAHS = '" + k_t_cu.getMaHS() + "' AND "
                + " MALOP = '" + k_t_cu.getMaLop() + "' AND "
                + " NAM = " + k_t_cu.getNam() + " AND "
                + " HOCKY = " + k_t_cu.getHocKi() + " AND "
                + " LOAIKT = N'" + k_t_cu.getLoaiKT() + "'";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        KT k_t = (KT) model;

        return "DELETE FROM KT "
                + " WHERE "
                + " MAMH = '" + k_t.getMaMH() + "' AND "
                + " MAGV = '" + k_t.getMaGV() + "' AND "
                + " MAHS = '" + k_t.getMaHS() + "' AND "
                + " MALOP = '" + k_t.getMaLop() + "' AND "
                + " NAM = " + k_t.getNam() + " AND "
                + " HOCKY = " + k_t.getHocKi() + "";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        KT k_t = (KT) model;

        String query = "SELECT * FROM KT ";

        String keyword = "";

        k_t.ChangeToNull();

        if (k_t.getMaMH() != null) {
            keyword += " MAMH LIKE '%" + k_t.getMaMH() + "%'";
        }

        if (k_t.getMaGV() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MAGV LIKE '%" + k_t.getMaGV() + "%'";
        }

        if (k_t.getMaHS() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MAHS LIKE '%" + k_t.getMaHS() + "%'";
        }

        if (k_t.getMaLop() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MALOP LIKE '%" + k_t.getMaLop() + "%'";
        }

        if (k_t.getNam() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " NAM =" + k_t.getNam() + " ";
        }

        if (k_t.getHocKi() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " HOCKY =" + k_t.getHocKi() + " ";
        }

        if (k_t.getDiem() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " DIEM =" + k_t.getDiem() + "";
        }

        if (k_t.getLoaiKT() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " LOAIKT LIKE N'" + k_t.getDiem() + "'";
        }

        if (k_t.getHeSo() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " HESO =" + k_t.getHeSo() + "";
        }

        if (keyword.equals("") == false) {
            query += "WHERE " + keyword;
        }

        return query;
    }

    @Override
    public String CreateQueryGetList(Accounts accounts) {
        if (accounts.getType().equals("admin")) {
            return CreateQueryGetList();
        } else {
            return "SELECT * FROM KT WHERE MAGV = '" + accounts.getMaGV() + "'";
        }
    }
}
