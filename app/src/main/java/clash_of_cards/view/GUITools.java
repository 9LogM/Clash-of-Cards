package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;

public class GUITools {
    public static void styleButton(JButton button) {
        button.setForeground(new Color(240, 240, 240));
        button.setBackground(new Color(50, 50, 50));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(280, 50));
        button.setFont(new Font("Consolas", Font.BOLD, 20));
    }

    public static void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 18));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setPreferredSize(new Dimension(150, 50));
    }

    public static void updatePanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }
}