/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Computer
 */
public interface Model {

    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);
    
    public abstract boolean equalsAll(Object obj);
    
    public abstract Model clone();
    
    public abstract Model ReplaceBy(Model newModel);

    public Model ChangeToNull();

    public Model ChangeToNotNull();
}
