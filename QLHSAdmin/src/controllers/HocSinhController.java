/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.Accounts;
import models.HocSinh;
import models.Lop;
import models.Model;

/**
 *
 * @author Computer
 */
public class HocSinhController extends BaseModelController {

    private static HocSinhController instance;

    private HocSinhController() {
    }

    public static synchronized HocSinhController getInstance() {
        if (instance == null) {
            instance = new HocSinhController();
        }
        return instance;
    }

    public String[] getArrDanToc() {
        return new String[]{
            "Kinh",
            "Chứt",
            "Mường",
            "Thổ",
            "Bố Y",
            "Giáy",
            "Lào",
            "Lự",
            "Nùng",
            "Sán Chay",
            "Tày",
            "Thái",
            "Cờ Lao",
            "La Chí",
            "La Ha",
            "Pu Péo",
            "Ba Na",
            "Brâu",
            "Bru - Vân Kiều",
            "Chơ Ro",
            "Co",
            "Cơ Ho",
            "Cơ Tu",
            "Giẻ Triêng",
            "Hrê",
            "Kháng",
            "Khơ Me",
            "Khơ Mú",
            "Mạ",
            "Mảng",
            "M’Nông",
            "Ơ Đu",
            "Rơ Măm",
            "Tà Ôi",
            "Xinh Mun",
            "Xơ Đăng",
            "X’Tiêng",
            "Dao",
            "H’Mông",
            "Pà Thẻn",
            "Chăm",
            "Chu Ru",
            "Ê Đê",
            "Gia Rai",
            "Ra Glai",
            "Hoa",
            "Ngái",
            "Sán Dìu",
            "Cống",
            "Hà Nhì",
            "La Hủ",
            "Lô Lô",
            "Phù Lá",
            "Si La"
        };
    }

    public String[] getArrTonGiao() {
        return new String[]{
            "Không",
            "Phật giáo ",
            "Công giáo ",
            "Tin Lành ",
            "Cao Đài ",
            "Phật giáo Hòa Hảo ",
            "Hồi giáo ",
            "Bahá\"í ",
            "Tịnh độ cư sĩ Phật hội Việt Nam ",
            "Đạo Tứ Ân Hiếu Nghĩa ",
            "Đạo Bửu Sơn Kỳ Hương ",
            "Minh Sư Đạo ",
            "Minh Lý Đạo ",
            "Bà-la-môn "
        };
    }

    public String[] getArrGioiTinh() {
        return new String[]{"Nam", "Nữ", "Khác"};
    }

    @Override
    public String CreateQueryAutoID() {
        return "SELECT dbo.AUTO_IDHocSinh() AS N'MaHS'";
    }

    @Override
    protected String ParseAutoIDFromResultSet(ResultSet resultSet) throws Exception {
        return resultSet.getString("MaHS");
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new HocSinh(resultSet.getString("MAHS"),
                resultSet.getString("TENHS"),
                resultSet.getString("GIOITINH"),
                resultSet.getDate("NGAYSINH"),
                resultSet.getString("DANTOC"),
                resultSet.getString("TONGIAO"),
                resultSet.getString("DIACHI"));
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM HOCSINH";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        HocSinh hs = (HocSinh) model;
        hs.ChangeToNotNull();

        String ma_hs = hs.getMaHS();

        if (ma_hs.isEmpty()) {
            ma_hs = "dbo.AUTO_IDHOCSinh()";
        } else {
            ma_hs = "'" + ma_hs + "'";
        }

        return "INSERT INTO HOCSINH (MAHS, TENHS, NGAYSINH, GIOITINH, DIACHI, DANTOC, TONGIAO) "
                + " VALUES ( "
                + "" + ma_hs + ","
                + "N'" + hs.getTenHs() + "',"
                + "'" + new SimpleDateFormat("yyyy-MM-dd").format(hs.getNgaySinh()) + "',"
                + "N'" + hs.getGioiTinh() + "',"
                + "N'" + hs.getDiaChi() + "',"
                + "N'" + hs.getDanToc() + "',"
                + "N'" + hs.getTonGiao() + "'"
                + " )";
    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        HocSinh hs_moi = (HocSinh) newModel;
        HocSinh hs_cu = (HocSinh) oldModel;
        hs_cu.ChangeToNotNull();
        hs_moi.ChangeToNotNull();

        return "UPDATE HOCSINH SET "
                + "MAHS = '" + hs_moi.getMaHS() + "',"
                + "TENHS = N'" + hs_moi.getTenHs() + "',"
                + "NGAYSINH = '" + new SimpleDateFormat("yyyy-MM-dd").format(hs_moi.getNgaySinh()) + "',"
                + "GIOITINH = N'" + hs_moi.getGioiTinh() + "',"
                + "DIACHI = N'" + hs_moi.getDiaChi() + "',"
                + "DANTOC = N'" + hs_moi.getDanToc() + "',"
                + "TONGIAO = N'" + hs_moi.getTonGiao() + "'"
                + " WHERE MAHS = '" + hs_cu.getMaHS() + "'";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        HocSinh hs = (HocSinh) model;

        return "DELETE FROM HOCSINH WHERE MAHS = '" + hs.getMaHS() + "'";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        HocSinh hs = (HocSinh) model;

        String query = "SELECT * FROM HOCSINH ";

        String keyword = "";

        hs.ChangeToNull();

        if (hs.getMaHS() != null) {
            keyword += " MAHS LIKE '%" + hs.getMaHS() + "%'";
        }

        if (hs.getTenHs() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " TENHS LIKE N'%" + hs.getTenHs() + "%'";
        }

        if (hs.getDiaChi() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " DIACHI LIKE N'%" + hs.getDiaChi() + "%'";
        }

        if (hs.getGioiTinh() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }

            keyword += " GIOITINH LIKE N'%" + hs.getGioiTinh() + "%'";
        }

        if (hs.getDanToc() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }

            keyword += " DANTOC LIKE N'%" + hs.getDanToc() + "%'";
        }
        if (hs.getTonGiao() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }

            keyword += " TONGIAO LIKE N'%" + hs.getTonGiao() + "%'";
        }
        if (hs.getNgaySinh() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }

            keyword += " NGAYSINH = '" + new SimpleDateFormat("yyyy-MM-dd").format(hs.getNgaySinh()) + "'";
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
            return "SELECT * FROM HOCSINH WHERE HOCSINH.MAHS IN "
                    + "(SELECT HOC.MAHS FROM HOC JOIN GIANGDAY ON HOC.MALOP = GIANGDAY.MALOP "
                    + "WHERE GIANGDAY.MAGV = '" + accounts.getMaGV() + "')";
        }
    }

    public ArrayList<HocSinh> GetList(Lop lop) {
        return (ArrayList<HocSinh>) (ArrayList<? extends Model>) this.GetList("SELECT * FROM HOCSINH WHERE HOCSINH.MAHS IN "
                + "(SELECT HOC.MAHS FROM HOC WHERE HOC.MALOP = '" + lop.getMaLop() + "')");
    }

    public ArrayList<HocSinh> GetList(Lop lop, Accounts accounts) {
        return (ArrayList<HocSinh>) (ArrayList<? extends Model>) this.GetList("SELECT * FROM HOCSINH WHERE HOCSINH.MAHS IN "
                + "(SELECT HOC.MAHS FROM HOC WHERE HOC.MALOP = '" + lop.getMaLop() + "')");
    }
}
