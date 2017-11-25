package frames;

import presenters.CalculatorPresenter;
import views.ICalculatorView;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculatorFrame extends JFrame implements ICalculatorView {

    private final CalculatorPresenter presenter;
    private JLabel numberTextArea, expressionTextArea;
    private JPanel buttonsPanel;

    public CalculatorFrame() {
        super("Calculadora");
        presenter = new CalculatorPresenter(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        initializeComponents();
    }

    private void initializeComponents() {
        expressionTextArea = new JLabel();
        expressionTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        expressionTextArea.setFont(new Font("Courier", Font.PLAIN, 20));
        expressionTextArea.setHorizontalAlignment(SwingConstants.RIGHT);
        expressionTextArea.setForeground(Color.lightGray);

        numberTextArea = new JLabel();
        numberTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        numberTextArea.setFont(new Font("Courier", Font.PLAIN, 30));
        numberTextArea.setHorizontalAlignment(SwingConstants.RIGHT);
        numberTextArea.setForeground(Color.black);

        JPanel resultsPanel = new JPanel(new GridLayout(2, 1));
        resultsPanel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        resultsPanel.setBackground(Color.WHITE);
        resultsPanel.add(expressionTextArea);
        resultsPanel.add(numberTextArea);

        buttonsPanel = new JPanel(new GridBagLayout());
        createButton('0', 3, 1);
        createButton('1', 2, 0);
        createButton('2', 2, 1);
        createButton('3', 2, 2);
        createButton('4', 1, 0);
        createButton('5', 1, 1);
        createButton('6', 1, 2);
        createButton('7', 0, 0);
        createButton('8', 0, 1);
        createButton('9', 0, 2);
        createButton('/', 0, 3);
        createButton('*', 1, 3);
        createButton('-', 2, 3);
        createButton('+', 3, 3);
        createButton('=', 3, 2);
        createButton('.', 3, 0);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(300, 400));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 10));
        mainPanel.add(resultsPanel);
        mainPanel.add(buttonsPanel);

        add(mainPanel);
        pack();
        presenter.onViewCreated();
    }

    private void createButton(Character symbol, int row, int col) {
        JButton btn = new JButton(symbol.toString());
        btn.setFont(new Font("Courier", Font.PLAIN, 25));
        btn.addActionListener(e -> presenter.onClickCalculatorBtn(symbol));
        btn.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.weighty = 0.25;
        constraints.weightx = 0.25;
        buttonsPanel.add(btn, constraints);
    }

    @Override
    public void setNumberTextArea(String result) {
        numberTextArea.setText(result);
    }

    @Override
    public void setExpression(String expression) {
        expressionTextArea.setText(expression);
    }
}
