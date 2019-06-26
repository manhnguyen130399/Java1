/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.datepickertablecell;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Computer
 */
public class DefaultDateCovertter extends DateConverter<Date> {

    @Override
    public Date toDate(Date value) {
        if (value != null) {
            return value;
        }

        LocalDate ld = LocalDate.now();
        return new java.sql.Date(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
    }

    @Override
    public Date fromDate(Date value) {
        return value;
    }

}
