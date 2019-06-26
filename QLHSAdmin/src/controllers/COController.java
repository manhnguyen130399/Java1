/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.CO;
import models.Model;

/**
 *
 * @author Computer
 */
public class COController extends ModelController {

    private static COController instance;

    private COController() {
        loaiCo = new String[]{"Cha", "Mẹ"};
    }

    public static synchronized COController getInstance() {
        if (instance == null) {
            instance = new COController();
        }
        return instance;
    }

    private final String[] loaiCo;

    public String[] getLoaiCo() {
        return loaiCo;
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM CO";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        CO co = (CO) model;

        return "INSERT INTO CO (MAPH, MAHS, LOAI) "
                + " VALUES ( "
                + "N'" + co.getMaPH() + "',"
                + "N'" + co.getMaHS() + "',"
                + "N'" + co.getLoai() + "'"
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        CO co_moi = (CO) newModel;
        CO co_cu = (CO) oldModel;

        return "UPDATE CO SET "
                + "MAPH = '" + co_moi.getMaPH() + "',"
                + "MAHS = N'" + co_moi.getMaHS() + "',"
                + "LOAI = N'" + co_moi.getLoai() + "'"
                + " WHERE "
                + "MAPH = '" + co_cu.getMaPH() + "' AND "
                + "MAHS = N'" + co_cu.getMaHS() + "'";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        CO co = (CO) model;
        String ma_co = co.getMaPH();

        return "DELETE FROM CO "
                + " WHERE "
                + "MAPH = '" + co.getMaPH() + "' AND "
                + "MAHS = N'" + co.getMaHS() + "'";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        CO co = (CO) model;
        co.ChangeToNull();

        String query = "SELECT * FROM CO ";

        String keyword = "";

        co.ChangeToNull();

        if (co.getMaPH() != null) {
            keyword += " MAPH LIKE '%" + co.getMaPH() + "%'";
        }

        if (co.getMaHS() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MAHS LIKE N'%" + co.getMaHS() + "%'";
        }

        if (co.getLoai() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " LOAI LIKE N'%" + co.getLoai() + "%'";
        }

        if (keyword.equals("") == false) {
            query += "WHERE " + keyword;
        }

        return query;
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new CO(resultSet.getString("MAPH"),
                resultSet.getString("MAHS"),
                resultSet.getString("LOAI"));
    }

    @Override
    public String CreateQueryGetList(Accounts accounts) {
        return CreateQueryGetList();
    }
}
