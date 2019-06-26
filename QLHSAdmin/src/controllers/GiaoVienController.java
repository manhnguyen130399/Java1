/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.GiaoVien;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import models.Accounts;
import models.Model;

/**
 *
 * @author Computer
 */
public class GiaoVienController extends BaseModelController {

    private static GiaoVienController instance;

    private GiaoVienController() {
    }

    public static synchronized GiaoVienController getInstance() {
        if (instance == null) {
            instance = new GiaoVienController();
        }
        return instance;
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new GiaoVien(resultSet.getString("MAGV"),
                resultSet.getString("TENGV"),
                resultSet.getDate("NGAYSINH"),
                resultSet.getString("GIOITINH"),
                resultSet.getString("DIACHI"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM GIAOVIEN";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        GiaoVien gv = (GiaoVien) model;

        String ma_gv = gv.getMaGV();

        if (ma_gv.isEmpty()) {
            ma_gv = "dbo.AUTO_IDGiaoVien()";
        } else {
            ma_gv = "'" + ma_gv + "'";
        }

        return "INSERT INTO GIAOVIEN (MAGV, TENGV, NGAYSINH, GIOITINH, DIACHI) "
                + " VALUES ( "
                + "" + ma_gv + ","
                + "N'" + gv.getTenGV() + "',"
                + "'" + new SimpleDateFormat("yyyy-MM-dd").format(gv.getNgaySinh()) + "',"
                + "N'" + gv.getGioiTinh() + "',"
                + "N'" + gv.getDiaChi() + "'"
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        GiaoVien gv_moi = (GiaoVien) newModel;
        GiaoVien gv_cu = (GiaoVien) oldModel;

        return "UPDATE GIAOVIEN SET "
                + "MAGV = '" + gv_moi.getMaGV() + "',"
                + "TENGV = N'" + gv_moi.getTenGV() + "',"
                + "NGAYSINH = '" + new SimpleDateFormat("yyyy-MM-dd").format(gv_moi.getNgaySinh()) + "',"
                + "GIOITINH = N'" + gv_moi.getGioiTinh() + "',"
                + "DIACHI = N'" + gv_moi.getDiaChi() + "'"
                + " WHERE MAGV = '" + gv_cu.getMaGV() + "'";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        GiaoVien gv = (GiaoVien) model;
        String ma_gv = gv.getMaGV();

        return "DELETE FROM GIAOVIEN WHERE MAGV = '" + ma_gv + "'";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        GiaoVien gv = (GiaoVien) model;

        String query = "SELECT * FROM GIAOVIEN ";

        String keyword = "";

        gv.ChangeToNull();

        if (gv.getMaGV() != null) {
            keyword += " MAGV LIKE '%" + gv.getMaGV() + "%'";
        }

        if (gv.getTenGV() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " TENGV LIKE N'%" + gv.getTenGV() + "%'";
        }

        if (gv.getGioiTinh() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " GIOITINH LIKE N'%" + gv.getGioiTinh() + "%'";
        }

        if (gv.getNgaySinh() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " NGAYSINH = '" + new SimpleDateFormat("yyyy-MM-dd").format(gv.getNgaySinh()) + "'";
        }

        if (gv.getDiaChi() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " DIACHI LIKE N'%" + gv.getDiaChi() + "%'";
        }

        if (keyword.equals("") == false) {
            query += "WHERE " + keyword;
        }

        return query;
    }

    @Override
    public String CreateQueryAutoID() {
        return "SELECT dbo.AUTO_IDGiaoVien() AS N'MaGV'";
    }

    @Override
    protected String ParseAutoIDFromResultSet(ResultSet resultSet) throws Exception {
        return resultSet.getString("MaGV");
    }

    @Override
    public String CreateQueryGetList(Accounts accounts) {
        return CreateQueryGetList();
    }

    public String[] getArrGioiTinh() {
        return new String[]{"Nam", "Nữ", "Khác"};
    }
}
