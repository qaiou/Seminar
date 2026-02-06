package ui;

import javax.swing.table.*;
import java.lang.reflect.*;
import java.util.*;

public class TableUtils {
    public static TableModel buildTableModel(List<?> data) {
        if (data == null || data.isEmpty()) return new DefaultTableModel();

        Field[] fields = data.get(0).getClass().getDeclaredFields();
        String[] cols = Arrays.stream(fields).map(Field::getName).toArray(String[]::new);

        Object[][] rows = new Object[data.size()][fields.length];

        try {
            for (int i = 0; i < data.size(); i++) {
                Object obj = data.get(i);
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    rows[i][j] = fields[j].get(obj);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(rows, cols);
    }
}
