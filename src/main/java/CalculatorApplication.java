import frames.CalculatorFrame;

import javax.swing.*;

public class CalculatorApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorFrame frame = new CalculatorFrame();
            frame.setVisible(true);
        });
    }

}
