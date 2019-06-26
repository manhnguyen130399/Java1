/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.HocLuc;
import models.Model;

/**
 *
 * @author Computer
 */
public class HocLucController extends BaseModelController {

    private static HocLucController instance;

    private HocLucController() {
    }

    public static synchronized HocLucController getInstance() {
        if (instance == null) {
            instance = new HocLucController();
        }
        return instance;
    }

    @Override
    public String CreateQueryAutoID() {
        return "SELECT dbo.AutoIDHocLuc() as N'MaHL'";
    }

    @Override
    protected String ParseAutoIDFromResultSet(ResultSet resultSet) throws Exception {
        return resultSet.getString("MaHL");
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new HocLuc(resultSet.getString("MALOAIHL"),
                resultSet.getString("LOAIHOCLUC"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM HOCLUC";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        HocLuc hl = (HocLuc) model;

        String ma_hl = hl.getMaHL();

        if (ma_hl.isEmpty()) {
            ma_hl = "dbo.AUTO_IDLOAIHOCLUC()";
        } else {
            ma_hl = "'" + ma_hl + "'";
        }

        return "INSERT INTO HOCLUC (MALOAIHL, LOAIHOCLUC) "
                + " VALUES ( "
                + "" + ma_hl + ","
                + "N'" + hl.getTenHL() + "'"
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        HocLuc hl_moi = (HocLuc) newModel;
        HocLuc hl_cu = (HocLuc) oldModel;

        return "UPDATE HOCLUC SET "
                + "MALOAIHL = '" + hl_moi.getMaHL() + "',"
                + "LOAIHOCLUC = N'" + hl_moi.getTenHL() + "',"
                + " WHERE MALOAIHL = '" + hl_cu.getMaHL() + "'";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        HocLuc hl = (HocLuc) model;

        return "DELETE FROM HOCLUC WHERE MALOAIHL = '" + hl.getMaHL() + "'";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        HocLuc hl = (HocLuc) model;

        String query = "SELECT * FROM HOCLUC ";

        String keyword = "";

        hl.ChangeToNull();

        if (hl.getMaHL() != null) {
            keyword += " MALOAIHL LIKE '%" + hl.getMaHL() + "%'";
        }

        if (hl.getTenHL() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " LOAIHOCLUC LIKE N'%" + hl.getTenHL() + "%'";
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
