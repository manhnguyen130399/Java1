/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.Model;
import models.MonHoc;

/**
 *
 * @author Computer
 */
public class MonHocController extends BaseModelController {

    private static MonHocController instance;

    private MonHocController() {
    }

    public static synchronized MonHocController getInstance() {
        if (instance == null) {
            instance = new MonHocController();
        }
        return instance;
    }

    @Override
    public String CreateQueryAutoID() {
        return "SELECT dbo.AUTO_IDMonHoc() AS N'MaMH'";
    }

    @Override
    protected String ParseAutoIDFromResultSet(ResultSet resultSet) throws Exception {
        return resultSet.getString("MaMH");
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new MonHoc(resultSet.getString("MAMH"),
                resultSet.getString("TENMH"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM MONHOC";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        MonHoc mh = (MonHoc) model;

        String ma_mh = mh.getMaMH();

        if (ma_mh.isEmpty()) {
            ma_mh = "dbo.AUTO_IDMONHOC()";
        } else {
            ma_mh = "'" + ma_mh + "'";
        }

        return "INSERT INTO MONHOC (MAMH, TENMH) "
                + " VALUES ( "
                + "" + ma_mh + ","
                + "N'" + mh.getTenMH() + "'"
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        MonHoc mh_moi = (MonHoc) newModel;
        MonHoc mh_cu = (MonHoc) oldModel;

        return "UPDATE MONHOC SET "
                + "MAMH = '" + mh_moi.getMaMH() + "',"
                + "TENMH = N'" + mh_moi.getTenMH() + "'"
                + " WHERE MAMH = '" + mh_cu.getMaMH() + "'";

    }

    @Override
    public String CreateQueryDelete(Model model) {
        MonHoc mh = (MonHoc) model;

        return "DELETE FROM MONHOC WHERE MAMH = '" + mh.getMaMH() + "'";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        MonHoc mh = (MonHoc) model;

        String query = "SELECT * FROM MONHOC ";

        String keyword = "";

        mh.ChangeToNull();

        if (mh.getMaMH() != null) {
            keyword += " MAMH LIKE '%" + mh.getMaMH() + "%'";
        }

        if (mh.getTenMH() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " TENMH LIKE N'%" + mh.getTenMH() + "%'";
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
