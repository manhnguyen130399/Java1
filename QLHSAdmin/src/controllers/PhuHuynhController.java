/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.Model;
import models.PhuHuynh;

/**
 *
 * @author Computer
 */
public class PhuHuynhController extends BaseModelController {

    private static PhuHuynhController instance;

    private PhuHuynhController() {
    }

    public static synchronized PhuHuynhController getInstance() {
        if (instance == null) {
            instance = new PhuHuynhController();
        }
        return instance;
    }

    @Override
    public String CreateQueryAutoID() {
        return "SELECT dbo.AUTO_IDPHuHuynh() AS N'MaPH'";
    }

    @Override
    protected String ParseAutoIDFromResultSet(ResultSet resultSet) throws Exception {
        return resultSet.getString("MaPH");
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new PhuHuynh(resultSet.getString("MAPH"),
                resultSet.getString("TENPH"),
                resultSet.getString("SDT"),
                resultSet.getString("DIACHI"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM PHUHUYNH";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        PhuHuynh ph = (PhuHuynh) model;

        String ma_ph = ph.getMaPH();

        if (ma_ph.isEmpty()) {
            ma_ph = "dbo.AUTO_IDPHuHuynh()";
        } else {
            ma_ph = "'" + ma_ph + "'";
        }

        return "INSERT INTO PHUHUYNH (MAPH, TENPH, SDT, DIACHI) "
                + " VALUES ( "
                + "" + ma_ph + ","
                + "N'" + ph.getTenPH() + "',"
                + "'" + ph.getSDT() + "',"
                + "N'" + ph.getDiaChi() + "'"
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        PhuHuynh ph_moi = (PhuHuynh) newModel;
        PhuHuynh ph_cu = (PhuHuynh) oldModel;

        return "UPDATE PHUHUYNH SET "
                + "MAPH = '" + ph_moi.getMaPH() + "',"
                + "TENPH = N'" + ph_moi.getTenPH() + "',"
                + "SDT = N'" + ph_moi.getSDT() + "',"
                + "DIACHI = N'" + ph_moi.getDiaChi() + "'"
                + " WHERE MAPH = '" + ph_cu.getMaPH() + "'";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        PhuHuynh ph = (PhuHuynh) model;

        return "DELETE FROM PHUHUYNH WHERE MAPH = '" + ph.getMaPH() + "'";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        PhuHuynh ph = (PhuHuynh) model;

        String query = "SELECT * FROM PHUHUYNH ";

        String keyword = "";

        ph.ChangeToNull();

        if (ph.getMaPH() != null) {
            keyword += " MAPH LIKE '%" + ph.getMaPH() + "%'";
        }

        if (ph.getTenPH() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " TENPH LIKE '%" + ph.getTenPH() + "%'";
        }

        if (ph.getSDT() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " SDT LIKE N'%" + ph.getSDT() + "%'";
        }

        if (ph.getDiaChi() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " DIACHI LIKE N'%" + ph.getDiaChi() + "%'";
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
