package ui;

import javax.swing.table.*;
import java.lang.reflect.*;
import java.util.*;

public class TableUtils {

    public static <T> TableModel buildTableModel(List<T> data,
                                                 String[] fieldNames,
                                                 String[] columnNames) {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            for (T obj : data) {
                Object[] row = new Object[fieldNames.length];

                for (int i = 0; i < fieldNames.length; i++) {
                    String getter = "get" + capitalize(fieldNames[i]);
                    Method m = obj.getClass().getMethod(getter);
                    row[i] = m.invoke(obj);
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return model;
    }

    private static String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }
}
