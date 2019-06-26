/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import models.Accounts;
import models.Model;

/**
 *
 * @author Computer
 */
public abstract class ModelController {

    private StringProperty contentLoading;
    private DoubleProperty progressLoading;

    public void setContentLoading(StringProperty contentLoading) {
        this.contentLoading = contentLoading;
        this.contentLoading.set("");
    }

    public void setProgressLoading(DoubleProperty progressLoading) {
        this.progressLoading = progressLoading;
        this.progressLoading.set(0);
    }

    public ArrayList<Model> GetList(String query) {
        ArrayList<Model> result = new ArrayList<>();

        if (this.contentLoading != null) {
            this.contentLoading.set(query);
        }
        if (progressLoading != null) {
            this.progressLoading.set(0);
        }

        try {
            Connection connection = DataController.getInstance().ConnectDatabase();

            if (this.contentLoading != null) {
                this.contentLoading.set("Execute " + query);
            }
            ResultSet resultSet = DataController.getInstance().ExecuteQuery(connection, query);

            if (this.contentLoading != null) {
                this.contentLoading.set("Get result of " + query);
            }
            if (resultSet != null) {
                resultSet.last();
                int n = resultSet.getRow();
                int i = 1;

                resultSet.beforeFirst();
                while (resultSet.next()) {
                    if (progressLoading != null) {
                        this.progressLoading.set(i * 1.0 / n);
                        i++;
                    }

                    result.add(this.ParseModelFromResultSet(resultSet));
                }
            }

            DataController.getInstance().DisconnectDatabase(connection);
        } catch (SQLException ex) { 
            System.out.println("Get List Model error!" + ", e: " + ex.toString());
            Logger.getLogger(ModelController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println("Get List Model error!" + ", e: " + ex.toString());
            Logger.getLogger(ModelController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public ArrayList<Model> GetList() {
        return this.GetList(ModelController.this.CreateQueryGetList());
    }

    public ArrayList<Model> GetList(Accounts accounts) {
        return this.GetList(ModelController.this.CreateQueryGetList(accounts));
    }
    
    public Model GetModel(Model model){
        model.ChangeToNull();
        
        ArrayList<Model> alModels = this.Search(model);
        
        if (alModels.isEmpty()){
            return null;
        }else{
            return alModels.get(0);
        }
    }

    public boolean Add(Model model) {
        return DataController.getInstance().ExecuteUpdate(CreateQueryAdd(model)) > 0;
    }

    public boolean Edit(Model oldModel, Model newModel) {
        return DataController.getInstance().ExecuteUpdate(CreateQueryEdit(oldModel, newModel)) > 0;
    }

    public boolean Delete(Model model) {
        return DataController.getInstance().ExecuteUpdate(CreateQueryDelete(model)) > 0;
    }

    public ArrayList<Model> Search(Model model) {
        ArrayList<Model> result = new ArrayList<>();
        String query = CreateQuerySearch(model);

        try {
            Connection connection = DataController.getInstance().ConnectDatabase();

            ResultSet resultSet = DataController.getInstance().ExecuteQuery(connection, query);

            while (resultSet.next()) {
                result.add(this.ParseModelFromResultSet(resultSet));
            }

            DataController.getInstance().DisconnectDatabase(connection);
        } catch (Exception e) {
            System.out.println("Search Model error!" + ", e: " + e.toString());
        }

        return result;
    }

    protected abstract Model ParseModelFromResultSet(ResultSet resultSet) throws Exception;

    public abstract String CreateQueryGetList();

    public abstract String CreateQueryGetList(Accounts accounts);

    public abstract String CreateQueryAdd(Model model);

    public abstract String CreateQueryEdit(Model oldModel, Model newModel);

    public abstract String CreateQueryDelete(Model model);

    public abstract String CreateQuerySearch(Model model);
}
