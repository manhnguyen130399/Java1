/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.datepickertablecell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class DatePickerTableCell<S, T> extends TableCell<S, Date> {

    public static <S, Date> Callback<TableColumn<S, Date>, TableCell<S, Date>> forTableColumn() {
        return new Callback<TableColumn<S, Date>, TableCell<S, Date>>() {
            @Override
            public TableCell<S, Date> call(TableColumn<S, Date> list) {
                return (TableCell<S, Date>) new DatePickerTableCell<S, Date>();
            }
        };
    }

    @Override
    protected void updateItem(Date item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            this.setGraphic(null);
        } else {
            this.setGraphic(new Label(new SimpleDateFormat("dd-MM-yyyy").format(item)));
        }
    }

    @Override
    public void updateSelected(boolean selected) {
        super.updateSelected(selected);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        this.setGraphic(new Label(new SimpleDateFormat("dd-MM-yyyy").format(this.getItem())));
    }

    @Override
    public void commitEdit(Date newValue) {
        super.commitEdit(newValue);
        this.setItem(newValue);
        this.setGraphic(new Label(new SimpleDateFormat("dd-MM-yyyy").format(this.getItem())));
    }

    @Override
    public void startEdit() {
        super.startEdit();

        Date date_item = this.getItem();

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.of(date_item.getYear() + 1900, date_item.getMonth() + 1, date_item.getDate()));
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(date);
                } else {
                    return null;
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && string.isEmpty() == false) {
                    return LocalDate.parse(string, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } else {
                    return null;
                }
            }
        });

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            Date date_newValue = null;

            try {
                date_newValue = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(
                        DateTimeFormatter.ofPattern("dd-MM-yyyy").format(newValue));
            } catch (ParseException ex) {
                System.out.println("\n===> ParseException on startEdit, e: " + ex.toString());
            }

            this.commitEdit(date_newValue);
        });

        this.setGraphic(datePicker);
    }
}