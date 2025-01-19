package table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TablePanel {
    public JTable createTable() {
        JTable table = new JTable(createTableModel());
        configureTableHeader(table.getTableHeader());
        configureTableStyle(table);

        return table;
    }

    private DefaultTableModel createTableModel() {
        return new DefaultTableModel(new Object[][] {},
                new String[] { "ID", "NAME", "ROLL #", "PROGRAM" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void configureTableHeader(JTableHeader header) {
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(52, 168, 83));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));

        header.setDefaultRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JLabel label = new JLabel(value == null ? "" : value.toString());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            return label;
        });
    }

    private void configureTableStyle(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(232, 242, 254));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(new Color(220, 220, 220));
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);

        table.setDefaultRenderer(Object.class, (table1, value, isSelected, hasFocus, row, column) -> {
            JLabel label = new JLabel(value == null ? "" : value.toString());
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            return label;
        });

        setColumnWidths(table);
        
    }

    private void setColumnWidths(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);
    }

}