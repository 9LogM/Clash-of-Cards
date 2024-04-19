package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;

public class ButtonUtils {
    public static void customButton(JButton button) {
        button.setForeground(new Color(240, 240, 240));
        button.setBackground(new Color(50, 50, 50));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(280, 50));
        button.setFont(new Font("Consolas", Font.BOLD, 20));
    }
}