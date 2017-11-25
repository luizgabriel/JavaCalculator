package presenters;

import models.Calculator;
import views.ICalculatorView;

import java.text.NumberFormat;

public class CalculatorPresenter extends BasePresenter<ICalculatorView> {

    private final Calculator calculator;

    public CalculatorPresenter(ICalculatorView view) {
        super(view);
        calculator = new Calculator();
    }

    private void displayDemo() {
        calculator.appendExpression("1.2 + 5.2");
        display(calculator.getResult());
        calculator.clear();
    }

    private void display(double number) {
        view.setExpression(calculator.getExpression());
        String displayResult = NumberFormat.getInstance().format(number);
        view.setNumberTextArea(displayResult);
    }

    public void onViewCreated() {
        displayDemo();
    }

    public void onClickCalculatorBtn(Character token) {
        if (token == '=') {
            display(calculator.getResult());
            calculator.clear();
        } else {
            calculator.appendToken(token);
            display(calculator.getCurrentNumber());
        }
    }
}
