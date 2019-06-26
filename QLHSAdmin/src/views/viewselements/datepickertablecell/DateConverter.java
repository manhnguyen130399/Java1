/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.datepickertablecell;

import java.util.Date;

/**
 *
 * @author Computer
 */

/**
 * Converter defines conversion behavior between dates and objects. The type of
 * objects and formats of dates are defined by the subclasses of Converter.
 *
 * @param <T> is the type of Objects
 */
public abstract class DateConverter<T> {

    /**
     * Converts the object provided into its date form. Format of the returned
     * date is defined by the specific converter.
     *
     * @param object Objects to convert to Dates
     * @return a string representation of the object passed in.
     */
    public abstract Date toDate(T object);

    /**
     * Converts the date provided into an object defined by the specific
     * converter. Format of the date and type of the resulting object is
     * defined by the specific converter.
     *
     * @param date the date to convert to object
     * @return an object representation of the string passed in.
     */
    public abstract T fromDate(Date date);

}
