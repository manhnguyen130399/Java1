/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabthongke;

import controllers.DataController;
import controllers.HocSinhController;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class TKContentController implements Initializable {

    @FXML
    private Label labelTongHocSinh;

    @FXML
    private Label labelTongHsNu;

    @FXML
    private Label labelTongHsNuDanToc;

    @FXML
    private Label labelTongHsNam;

    @FXML
    private Label labelTongHsNamDanToc;

    @FXML
    private Label labelTongHsTheoTonGiao;

    @FXML
    private Label labelTongSoPhuHuynh;

    @FXML
    private PieChart piechartTiLeNamNu;

    @FXML
    private PieChart piechartTiLeHsCacKhoiLop;

    @FXML
    private PieChart piechartTiLeTonGiao;

    @FXML
    private PieChart piechartTiLeDanToc;

    @FXML
    private PieChart piechartTiLeXepLoaiHs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ResultSet resultSet;
            Connection connection;
            String query;

            ///Tính tổng hs///
            connection = DataController.getInstance().ConnectDatabase();
            query = "SELECT COUNT(*) AS 'TONGHS' FROM HOCSINH ";
            resultSet = DataController.getInstance().ExecuteQuery(connection, query);
            if (resultSet.next()) {
                this.labelTongHocSinh.setText(Integer.toString(resultSet.getInt("TONGHS")));
            }
            DataController.getInstance().DisconnectDatabase(connection);

            ///Tính tổng hs nữ///
            connection = DataController.getInstance().ConnectDatabase();
            query = "SELECT COUNT(*) AS 'TONGHSNU' FROM HOCSINH WHERE GIOITINH LIKE N'%Nữ%'";
            resultSet = DataController.getInstance().ExecuteQuery(connection, query);
            if (resultSet.next()) {
                this.labelTongHsNu.setText(Integer.toString(resultSet.getInt("TONGHSNU")));
            }
            DataController.getInstance().DisconnectDatabase(connection);

            ///Tính tổng hs nữ dân tộc///
            connection = DataController.getInstance().ConnectDatabase();
            query = "SELECT COUNT(*) AS 'TONGHSNUDANTOC' FROM HOCSINH WHERE GIOITINH LIKE N'%Nữ%' AND DANTOC <> N'Kinh'";
            resultSet = DataController.getInstance().ExecuteQuery(connection, query);
            if (resultSet.next()) {
                this.labelTongHsNuDanToc.setText(Integer.toString(resultSet.getInt("TONGHSNUDANTOC")));
            }
            DataController.getInstance().DisconnectDatabase(connection);

            ///Tính tổng hs nam///
            connection = DataController.getInstance().ConnectDatabase();
            query = "SELECT COUNT(*) AS 'TONGHSNAM' FROM HOCSINH WHERE GIOITINH LIKE N'%Nam%'";
            resultSet = DataController.getInstance().ExecuteQuery(connection, query);
            if (resultSet.next()) {
                this.labelTongHsNam.setText(Integer.toString(resultSet.getInt("TONGHSNAM")));
            }
            DataController.getInstance().DisconnectDatabase(connection);

            ///Tính tổng hs nam dân tộc///
            connection = DataController.getInstance().ConnectDatabase();
            query = "SELECT COUNT(*) AS 'TONGHSNAMDANTOC' FROM HOCSINH WHERE GIOITINH LIKE N'%Nam%' AND DANTOC <> N'Kinh'";
            resultSet = DataController.getInstance().ExecuteQuery(connection, query);
            if (resultSet.next()) {
                this.labelTongHsNamDanToc.setText(Integer.toString(resultSet.getInt("TONGHSNAMDANTOC")));
            }
            DataController.getInstance().DisconnectDatabase(connection);

            //Tính tổng hs theo tôn giáo///
            connection = DataController.getInstance().ConnectDatabase();
            query = "SELECT COUNT(*) AS 'TONGHSTHEOTONGIAO' FROM HOCSINH WHERE  TONGIAO NOT LIKE N'Không'";
            resultSet = DataController.getInstance().ExecuteQuery(connection, query);
            if (resultSet.next()) {
                this.labelTongHsTheoTonGiao.setText(Integer.toString(resultSet.getInt("TONGHSTHEOTONGIAO")));
            }
            DataController.getInstance().DisconnectDatabase(connection);

            ///Tính tổng phụ huynh///
            connection = DataController.getInstance().ConnectDatabase();
            query = "SELECT COUNT(*) AS 'TONGPH' FROM PHUHUYNH ";
            resultSet = DataController.getInstance().ExecuteQuery(connection, query);
            if (resultSet.next()) {
                this.labelTongSoPhuHuynh.setText(Integer.toString(resultSet.getInt("TONGPH")));
            }
            DataController.getInstance().DisconnectDatabase(connection);

            ///Tạo data cho piechart tỉ lệ nam nữ///
            PieChart.Data soluongNam = new PieChart.Data("Nam", Integer.parseInt(this.labelTongHsNam.getText()));
            PieChart.Data soluongNu = new PieChart.Data("Nữ", Integer.parseInt(this.labelTongHsNu.getText()));
            this.piechartTiLeNamNu.getData().addAll(soluongNam, soluongNu);

            ///Tạo data cho piechart tỉ lệ học sinh các khối lớp///
            for (int i = 10; i <= 12; i++) {
                connection = DataController.getInstance().ConnectDatabase();
                query = "SELECT COUNT(*) AS 'TONGHSLOP' \n"
                        + "	FROM HOC JOIN LOP ON HOC.MALOP = LOP.MALOP\n"
                        + "	WHERE LEFT(LOP.TENLOP, 2) = '" + i + "'";
                resultSet = DataController.getInstance().ExecuteQuery(connection, query);
                if (resultSet.next()) {
                    this.piechartTiLeHsCacKhoiLop.getData().addAll(
                            new PieChart.Data("Lớp " + i, resultSet.getInt("TONGHSLOP")));
                }
            }

            ///Tạo data cho piechart tỉ lệ tôn giáo//
            for (String i : HocSinhController.getInstance().getArrTonGiao()) {
                connection = DataController.getInstance().ConnectDatabase();
                query = "SELECT COUNT(*) AS 'TONGHS' FROM HOCSINH WHERE TONGIAO = N'" + i + "'";
                resultSet = DataController.getInstance().ExecuteQuery(connection, query);
                if (resultSet.next()) {
                    this.piechartTiLeTonGiao.getData().addAll(
                            new PieChart.Data(i, resultSet.getInt("TONGHS")));
                }
            }

            ///Tạo data cho piechart tỉ lệ dân tộc//
            for (String i : HocSinhController.getInstance().getArrDanToc()) {
                connection = DataController.getInstance().ConnectDatabase();
                query = "SELECT COUNT(*) AS 'TONGHS' FROM HOCSINH WHERE DANTOC = N'" + i + "'";
                resultSet = DataController.getInstance().ExecuteQuery(connection, query);
                if (resultSet.next()) {
                    this.piechartTiLeDanToc.getData().addAll(
                            new PieChart.Data(i, resultSet.getInt("TONGHS")));
                }
            }

            ///Tạo data cho piechart tỉ lệ điểm//
            for (int i = 0; i < 4; i++) {
                connection = DataController.getInstance().ConnectDatabase();
                query = "SELECT COUNT(*) AS 'TONGHS' FROM KQ WHERE KQ.DTB > " + ((i + 1) * 2);
                resultSet = DataController.getInstance().ExecuteQuery(connection, query);
                if (resultSet.next()) {
                    this.piechartTiLeXepLoaiHs.getData().addAll(
                            new PieChart.Data("Loại " + ((char) (i + 65)), resultSet.getInt("TONGHS")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
