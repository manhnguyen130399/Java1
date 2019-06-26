/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import models.Accounts;
import models.KQ;
import models.Model;

/**
 *
 * @author Computer
 */
public class KQController extends ModelController {

    private static KQController instance;

    private KQController() {
    }

    public static synchronized KQController getInstance() {
        if (instance == null) {
            instance = new KQController();
        }
        return instance;
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new KQ(resultSet.getString("MAHS"),
                resultSet.getString("MALOAIHL"),
                resultSet.getInt("HOCKY"),
                resultSet.getString("MALOAIHK"),
                resultSet.getInt("NAM"),
                resultSet.getDouble("DTB"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM KQ";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        KQ kq = (KQ) model;

        return "INSERT INTO KQ (MAHS, HOCKY, MALOAIHL, NAM, MALOAIHK, DTB) "
                + " VALUES ( "
                + "" + kq.getMaHS() + ","
                + "" + kq.getHocKi() + ","
                + "N'" + kq.getMaLoaiHL() + "',"
                + "" + kq.getNam() + ","
                + "N'" + kq.getLoaiHK() + "',"
                + "" + kq.getDTB() + ""
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        KQ kq_moi = (KQ) newModel;
        KQ kq_cu = (KQ) oldModel;

        String dtb = "null";
        if (kq_moi.getDTB() != null) {
            dtb = kq_moi.getDTB().toString();
        }

        return "UPDATE KQ SET "
                + "MAHS = '" + kq_moi.getMaHS() + "',"
                + "HOCKY = " + kq_moi.getHocKi() + ","
                + "MALOAIHL = N'" + kq_moi.getMaLoaiHL() + "',"
                + "NAM = " + kq_moi.getNam() + ","
                + "MALOAIHK = N'" + kq_moi.getLoaiHK() + "',"
                + "DTB = " + kq_moi.getDTB() + ""
                + " WHERE "
                + "MAHS = '" + kq_cu.getMaHS() + "' AND "
                + "HOCKY = " + kq_cu.getHocKi() + " AND "
                + "MALOAIHL = '" + kq_cu.getMaLoaiHL() + "' AND "
                + "NAM = " + kq_cu.getNam() + " AND "
                + "MALOAIHK = N'" + kq_cu.getLoaiHK() + "";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        KQ kq = (KQ) model;

        return "DELETE FROM KQ "
                + " WHERE "
                + "MAHS = '" + kq.getMaHS() + "' AND "
                + "HOCKY = " + kq.getHocKi() + " AND "
                + "MALOAIHL = '" + kq.getMaLoaiHL() + "' AND "
                + "NAM = " + kq.getNam() + " AND "
                + "MALOAIHK = N'" + kq.getLoaiHK() + "";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        KQ kq = (KQ) model;
        String query = "SELECT * FROM KQ ";

        String keyword = "";

        kq.ChangeToNull();

        if (kq.getMaHS() != null) {
            keyword += " MAHS LIKE '%" + kq.getMaHS() + "%'";
        }

        if (kq.getHocKi() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " HOCKY = " + kq.getHocKi() + "";
        }

        if (kq.getMaLoaiHL() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MALOAIHL LIKE N'%" + kq.getMaLoaiHL() + "%'";
        }

        if (kq.getLoaiHK() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " MALOAIHK LIKE N'%" + kq.getLoaiHK() + "%'";
        }

        if (kq.getNam() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " NAM = " + kq.getNam() + "";
        }

        if (kq.getDTB() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " DTB =" + kq.getDTB() + "";
        }

        if (keyword.equals("") == false) {
            query += " WHERE " + keyword;
        }

        return query;
    }

    @Override
    public String CreateQueryGetList(Accounts accounts) {
        if (accounts.getType().equals("admin")) {
            return CreateQueryGetList();
        } else {
            return "SELECT * FROM KQ  WHERE KQ.MAHS IN "
                    + "(SELECT HOC.MAHS FROM HOC JOIN GIANGDAY ON HOC.MALOP = GIANGDAY.MALOP "
                    + "WHERE MAGV = '" + accounts.getMaGV() + "')";
        }
    }
}
