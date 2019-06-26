/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import models.Accounts;
import models.Model;

/**
 *
 * @author Computer
 */
public class AccountsController extends ModelController {

    private static AccountsController instance;

    private AccountsController() {
    }

    public static synchronized AccountsController getInstance() {
        if (instance == null) {
            instance = new AccountsController();
        }
        return instance;
    }

    public Accounts Login(String username, String password) {
        ArrayList<Model> resultSearch = this.Search(new Accounts(username, password, null, null));

        if (resultSearch.size() != 1) {
            return null;
        } else {
            return (Accounts) resultSearch.get(0);
        }
    }

    public boolean ResetPassword(Accounts accounts) {
        Accounts oldModel = (Accounts) accounts.clone();
        Accounts newModel = (Accounts) accounts.clone();
        newModel.setPassword("user");
        return this.Edit(oldModel, newModel);
    }

    @Override
    public String CreateQueryGetList() {
        return "SELECT * FROM ACCOUNT";
    }

    @Override
    public String CreateQueryAdd(Model model) {
        Accounts accounts = (Accounts) model;

        String magv = "null";
        if (accounts.getMaGV() != null) {
            if (accounts.getMaGV().isEmpty() == false) {
                magv = "'" + accounts.getMaGV() + "'";
            }
        }

        return "INSERT INTO ACCOUNT (USERNAME, PASSWORD, DISPLAYNAME, TYPE, MAGV) VALUES "
                + "("
                + "N'" + accounts.getUsername() + "', "
                + "N'" + accounts.getPassword() + "', "
                + "N'" + accounts.getDisplayname() + "', "
                + "N'" + accounts.getType() + "', "
                + magv
                + ")";

    }

    @Override
    public String CreateQueryEdit(Model oldModel, Model newModel) {
        Accounts newAccount = (Accounts) newModel;
        Accounts oldAccount = (Accounts) oldModel;

        String MaGV = "null";
        if (newAccount.getMaGV() != null) {
            if (newAccount.getMaGV().isEmpty() == false) {
                MaGV = "'" + newAccount.getMaGV() + "'";
            }
        }

        return "UPDATE ACCOUNT SET"
                + " USERNAME = '" + newAccount.getUsername() + "', "
                + " PASSWORD = N'" + newAccount.getPassword() + "', "
                + " DISPLAYNAME = N'" + newAccount.getDisplayname() + "', "
                + " TYPE = N'" + newAccount.getType() + "', "
                + " MAGV = " + MaGV
                + " WHERE USERNAME = '" + oldAccount.getUsername() + "'";
    }

    @Override
    public String CreateQueryDelete(Model model) {
        Accounts accounts = (Accounts) model;

        return "DELETE FROM ACCOUNT WHERE "
                + " USERNAME = N'" + accounts.getUsername() + "' ";
    }

    @Override
    public String CreateQuerySearch(Model model) {
        Accounts accounts = (Accounts) model;
        model.ChangeToNull();

        String query = "SELECT * FROM ACCOUNT ";

        String keyword = "";

        accounts.ChangeToNull();

        if (accounts.getMaGV() != null) {
            keyword += " MAGV LIKE '%" + accounts.getMaGV() + "%'";
        }

        if (accounts.getUsername() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " USERNAME LIKE N'%" + accounts.getUsername() + "%'";
        }

        if (accounts.getPassword() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " PASSWORD LIKE N'%" + accounts.getPassword() + "%'";
        }

        if (accounts.getDisplayname() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " DISPLAYNAME LIKE N'%" + accounts.getDisplayname() + "%'";
        }

        if (accounts.getType() != null) {
            if (keyword.equals("") == false) {
                keyword += " AND ";
            }
            keyword += " TYPE LIKE N'%" + accounts.getType() + "%'";
        }

        if (keyword.equals("") == false) {
            query += "WHERE " + keyword;
        }

        return query;
    }

    @Override
    protected Model ParseModelFromResultSet(ResultSet resultSet) throws Exception {
        return new Accounts(resultSet.getString("USERNAME"),
                "@Password@Password@Password",
                resultSet.getString("DISPLAYNAME"),
                resultSet.getString("TYPE"),
                resultSet.getString("MAGV"));
    }

    @Override
    public String CreateQueryGetList(Accounts accounts) {
        if (accounts.getType().equals("admin")) {
            return CreateQueryGetList();
        } else {
            return "";
        }
    }

    public String[] getListAccountType() {
        return new String[]{"admin", "user"};
    }
}
