/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.Lop;
import models.Model;

/**
 *
 * @author Computer
 */
public class LopController extends BaseModelController {

    private static LopController instance;

    private LopController() {
    }

    public static synchronized LopController getInstance() {
        if (instance == null) {
            instance = new LopController();
        }
        return instance;
    }

    @Override
    public String CreateQueryAutoID() {
        return "SELECT dbo.AUTO_IDLopHoc() AS N'MaLOP'";
    }

    @Override
    protected String ParseAutoIDFromResultSet(ResultSet resultSet) throws Exception {
        return resultSet.getString("MaLOP");
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new Lop(resultSet.getString("MALOP"),
                resultSet.getString("TENLOP"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM LOP";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        Lop l = (Lop) model;

        String ma_lop = l.getMaLop();

        if (ma_lop.isEmpty()) {
            ma_lop = "dbo.AUTO_IDLopHoc()";
        } else {
            ma_lop = "'" + ma_lop + "'";
        }

        return "INSERT INTO LOP (MALOP, TENLOP) "
                + " VALUES ( "
                + "" + ma_lop + ","
                + "N'" + l.getTenLop() + "'"
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        Lop lop_moi = (Lop) newModel;
        Lop lop_cu = (Lop) oldModel;

        return "UPDATE LOP SET "
                + "MALOP = '" + lop_moi.getMaLop() + "',"
                + "TENLOP = N'" + lop_moi.getTenLop() + "'"
                + " WHERE MALOP = '" + lop_cu.getMaLop() + "'";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        Lop l = (Lop) model;

        return "DELETE FROM LOP WHERE MALOP = '" + l.getMaLop() + "'";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        Lop l = (Lop) model;

        String query = "SELECT * FROM LOP ";

        String keyword = "";

        l.ChangeToNull();

        if (l.getMaLop() != null) {
            keyword += " MALOP LIKE '%" + l.getMaLop() + "%'";
        }

        if (l.getTenLop() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " TENLOP LIKE N'%" + l.getTenLop() + "%'";
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
            return "SELECT * FROM LOP WHERE LOP.MALOP IN (SELECT DISTINCT GIANGDAY.MALOP FROM GIANGDAY "
                    + "WHERE GIANGDAY.MAGV = '" + accounts.getMaGV() + "')";
        }
    }
}
