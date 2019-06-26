/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Computer
 */
public abstract class BaseModelController extends ModelController {

    public String CreateAutoID() {
        String result = "";
        String query = CreateQueryAutoID();

        try {
            Connection connection = DataController.getInstance().ConnectDatabase();

            ResultSet resultSet = DataController.getInstance().ExecuteQuery(connection, query);

            if (resultSet.next()) {
                result = ParseAutoIDFromResultSet(resultSet);
            }

            DataController.getInstance().DisconnectDatabase(connection);
        } catch (Exception e) {
            System.out.println("Execute getAutoID Model error!" + ", e: " + e.toString());
        }

        return result;
    }

    public abstract String CreateQueryAutoID();
    protected abstract String ParseAutoIDFromResultSet(ResultSet resultSet) throws Exception;
}
