package clash_of_cards.view;

import clash_of_cards.model.WinCountManager;
import clash_of_cards.util.GUITools;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class HighScoresView {
    private JFrame frame;
    private JPanel panel;
    private JPanel buttonPanel;
    private JButton backButton, resetScoresButton;
    private JLabel highScoresLabel;
    private JTable scoresTable;
    private WinCountManager scoresManager;

    public HighScoresView(ActionListener backListener, WinCountManager scoresManager) {
        this.scoresManager = scoresManager;
        initializeComponents(backListener);
        setupUI();
        updateHighScores();
    }

    private void initializeComponents(ActionListener backListener) {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(80, 80, 80));
        panel.setBorder(BorderFactory.createEmptyBorder());

        highScoresLabel = new JLabel("High Scores", SwingConstants.CENTER);
        highScoresLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        highScoresLabel.setFont(new Font("Consolas", Font.BOLD, 60));
        highScoresLabel.setForeground(new Color(240, 240, 240));
        panel.add(highScoresLabel, BorderLayout.NORTH);

        backButton = new JButton("Back to Main Menu");
        resetScoresButton = new JButton("Reset Scores");
        GUITools.styleButton(backButton);
        GUITools.styleButton(resetScoresButton);
        backButton.addActionListener(backListener);
        resetScoresButton.addActionListener(e -> resetScores());

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(80, 80, 80));
        buttonPanel.add(backButton);
        buttonPanel.add(resetScoresButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        scoresTable = new JTable();
        scoresTable.setShowGrid(false);
        scoresTable.setFont(new Font("Consolas", Font.PLAIN, 40));
        scoresTable.setRowHeight(50);
        scoresTable.setForeground(new Color(240, 240, 240));
        scoresTable.setBackground(new Color(50, 50, 50));
        scoresTable.setIntercellSpacing(new Dimension(0, 0));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        scoresTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(scoresTable);
        scrollPane.getViewport().setBackground(new Color(50, 50, 50));         
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        JTableHeader header = scoresTable.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                setBackground(new Color(50, 50, 50));
                setForeground(new Color(255, 205, 0));
                setFont(new Font("Consolas", Font.BOLD, 45));
                return this;
            }
        });
    }

    private void resetScores() {
        scoresManager.resetWinCounts();
        updateHighScores();
    }

    private void setupUI() {
        frame = new JFrame("Clash of Cards - High Scores");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(panel);
        frame.setVisible(false);
    }

    public void show() {
        updateHighScores();
        frame.setVisible(true);
    }

    public void hide() {
        frame.dispose();
    }

    private void updateHighScores() {
        DefaultTableModel model = (DefaultTableModel) scoresTable.getModel();
        model.setColumnIdentifiers(new Object[]{"Name", "Games Won"});
        model.setRowCount(0);

        Properties scores = scoresManager.getWinCounts();
        java.util.List<Map.Entry<Object, Object>> list = new java.util.ArrayList<>(scores.entrySet());
        list.sort((a, b) -> Integer.compare(Integer.parseInt(b.getValue().toString()), Integer.parseInt(a.getValue().toString())));

        int count = 0;
        for (Map.Entry<Object, Object> entry : list) {
            if (count++ == 20) break;
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }
}
