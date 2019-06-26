/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author Computer
 */
public class Accounts implements Model {

    private String Username;
    private String Password;
    private String Displayname;
    private String Type;
    private String MaGV;

    public Accounts() {
    }

    public Accounts(String username, String password, String displayname, String type) {
        this.Username = username;
        this.Password = password;
        this.Displayname = displayname;
        this.Type = type;
    }

    public Accounts(String username, String password, String displayname, String type, String MaGV) {
        this.Username = username;
        this.Password = password;
        this.Displayname = displayname;
        this.Type = type;
        this.MaGV = MaGV;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getDisplayname() {
        return Displayname;
    }

    public void setDisplayname(String displayname) {
        this.Displayname = displayname;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getMaGV() {
        return MaGV;
    }

    public void setMaGV(String MaGV) {
        this.MaGV = MaGV;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Accounts other = (Accounts) obj;
        if (!Objects.equals(this.Username, other.Username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accounts{" + "username=" + Username + ", password=" + Password + ", displayname=" + Displayname + ", type=" + Type + ", MaGV=" + MaGV + '}';
    }

    @Override
    public Model ChangeToNull() {
        if (this.Username != null) {
            if (this.Username.isEmpty()) {
                this.Username = null;
            }
        }
        if (this.Password != null) {
            if (this.Password.isEmpty()) {
                this.Password = null;
            }
        }
        if (this.Displayname != null) {
            if (this.Displayname.isEmpty()) {
                this.Displayname = null;
            }
        }
        if (this.Type != null) {
            if (this.Type.isEmpty()) {
                this.Type = null;
            }
        }
        if (this.MaGV != null) {
            if (this.MaGV.isEmpty()) {
                this.MaGV = null;
            }
        }

        return this;
    }

    @Override
    public Model ChangeToNotNull() {
        if (this.Username == null) {
            this.Username = "";
        }
        if (this.Password == null) {
            this.Password = "";
        }
        if (this.Displayname == null) {
            this.Displayname = "";
        }
        if (this.Type == null) {
            this.Type = "";
        }
        if (this.MaGV == null) {
            this.MaGV = "";
        }

        return this;
    }

    @Override
    public boolean equalsAll(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Accounts other = (Accounts) obj;
        if (!Objects.equals(this.Username, other.Username)) {
            return false;
        }
        if (!Objects.equals(this.Password, other.Password)) {
            return false;
        }
        if (!Objects.equals(this.Displayname, other.Displayname)) {
            return false;
        }
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        if (!Objects.equals(this.MaGV, other.MaGV)) {
            return false;
        }
        return true;
    }

    @Override
     public Model clone() {
         return new Accounts(Username, Password, Displayname, Type, MaGV);
    }

    @Override
    public Model ReplaceBy(Model newModel) {
        Model oldModel = this.clone();
        
        Accounts newAccounts = ((Accounts)newModel);
        this.Displayname = newAccounts.getDisplayname();
        this.MaGV = newAccounts.getMaGV();
        this.Password = newAccounts.getPassword();
        this.Type = newAccounts.getType();
        this.Username = newAccounts.getUsername();
        
        return oldModel;
    }
    
    
}
