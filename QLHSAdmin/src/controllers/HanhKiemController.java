/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.HanhKiem;
import models.Model;

/**
 *
 * @author Computer
 */
public class HanhKiemController extends BaseModelController {

    private static HanhKiemController instance;

    private HanhKiemController() {
    }

    public static synchronized HanhKiemController getInstance() {
        if (instance == null) {
            instance = new HanhKiemController();
        }
        return instance;
    }

    @Override
    public String CreateQueryAutoID() {
        return "SELECT dbo.AutoIDHanhKiem() as N'MaHK'";
    }

    @Override
    protected String ParseAutoIDFromResultSet(ResultSet resultSet) throws Exception {
        return resultSet.getString("MaHK");
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new HanhKiem(resultSet.getString("MALOAIHK"),
                resultSet.getString("TENHK"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM HANHKIEM";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        HanhKiem hk = (HanhKiem) model;
        String ma_hk = hk.getMaHK();

        if (ma_hk.isEmpty()) {
            ma_hk = "dbo.AUTO_IDLOAIHANHKIEM()";
        } else {
            ma_hk = "N'" + ma_hk + "'";
        }

        return "INSERT INTO HANHKIEM (MALOAIHK, TENHK) "
                + " VALUES ( "
                + "" + ma_hk + ", "
                + "N'" + hk.getTenHK() + "'"
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        HanhKiem hk_moi = (HanhKiem) newModel;
        HanhKiem hk_cu = (HanhKiem) oldModel;

        return "UPDATE HANHKIEM SET "
                + "MALOAIHK = N'" + hk_moi.getMaHK() + "', "
                + "TENHK = N'" + hk_moi.getTenHK() + "'"
                + " WHERE MALOAIHK = '" + hk_cu.getMaHK() + "'";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        HanhKiem hk = (HanhKiem) model;

        return "DELETE FROM HANHKIEM WHERE MALOAIHK = '" + hk.getMaHK() + "'";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        HanhKiem hk = (HanhKiem) model;

        String query = "SELECT * FROM HANHKIEM ";

        String keyword = "";

        hk.ChangeToNull();

        if (hk.getMaHK() != null) {
            keyword += " MALOAIHK LIKE '%" + hk.getMaHK() + "%'";
        }

        if (hk.getTenHK() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " TENHK LIKE N'%" + hk.getTenHK() + "%'";
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
