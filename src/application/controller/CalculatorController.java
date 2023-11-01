package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class CalculatorController implements Initializable {
	@FXML
	private SplitPane mainWindow;

	@FXML
	private Label lblInputNumber;

	@FXML
	private Label lblResult;

	private String operation;
	private double firstNumber;
	private double secondNumber;
	private Stage primaryStage;
	private double result;

	private boolean enteringSecondNumber = false;
	private boolean isNegativ = false;
	private boolean isDecimal = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setVariablesForStart();
		// ToDO
//		-clean up
//		- after calculating result be able to enter number again and continue
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private void setVariablesForStart() {
		lblInputNumber.setText("");
		lblResult.setText("");
		operation = null;
		firstNumber = 0;
		secondNumber = 0;
		enteringSecondNumber = false;
		isNegativ = false;
		isDecimal = false;

	}

	private void clearDatasAfterCalculation() {
		firstNumber = result;
		operation = null;
		secondNumber = 0;
		isNegativ = false;
		isDecimal = false;

	}

	@FXML
	public void clearAll() {
		setVariablesForStart();
	}

	@FXML
	public void numberClicked(ActionEvent event) {
		Button numberButton = (Button) event.getSource();
		Platform.runLater(() -> {
			if (enteringSecondNumber) {
				lblResult.setText("");
				lblResult.setText(firstNumber + operation);
				lblInputNumber.setText(numberButton.getText());
				enteringSecondNumber = false;
			} else {
				lblInputNumber.setText(lblInputNumber.getText() + numberButton.getText());
			}
		});
	}

	@FXML
	public void calculationType(ActionEvent event) {
		Button calculateButton = (Button) event.getSource();
		if (isFirstNumber()) {
			lblResult.setText(lblInputNumber.getText());
			firstNumber = Double.parseDouble(lblResult.getText());
			lblInputNumber.setText("");
			isDecimal = false;
			operation = calculateButton.getText();
			lblInputNumber.setText(operation);
			enteringSecondNumber = true;
		} else {
			calculateResult(event);
			lblResult.setText("");
			operation = calculateButton.getText();
			lblResult.setText(firstNumber + operation);
			lblInputNumber.setText(operation);
			enteringSecondNumber = true;
		}
		
	}

	@FXML
	public void calculateResult(ActionEvent event) {
		if (isFirstNumber()) {
			setVariablesForStart();
			;
		} else {
			try {
				secondNumber = Double.parseDouble(lblInputNumber.getText());
				calculateResultBasedOnOperationSign();
			} catch (Exception e) {
				setTextAfterCalculation(firstNumber);
			}
		}			
		clearDatasAfterCalculation();
	}

	@FXML
	public void backSpace(ActionEvent event) {
		lblInputNumber.setText(removeLastChar(lblInputNumber.getText()));
	}

	@FXML
	public void setToNegativOrPositiv(ActionEvent event) {
		if (isNegativ) {
			isNegativ = false;
			if (doesContainNegativOrPositivSign(lblInputNumber.getText())) {
				lblInputNumber.setText(removeFirstChar(lblInputNumber.getText()));
				changeSignBeforeNumberTo('-');
			} else {
				changeSignBeforeNumberTo('-');
			}
		} else {
			isNegativ = true;
			if (doesContainNegativOrPositivSign(lblInputNumber.getText())) {
				lblInputNumber.setText(removeFirstChar(lblInputNumber.getText()));
				changeSignBeforeNumberTo('+');
			} else {
				changeSignBeforeNumberTo('+');
			}
		}
	}

	public void calculateResultBasedOnOperationSign() {
		if (operation.equalsIgnoreCase("+"))
			setTextAfterCalculation(addNumbers(firstNumber, secondNumber));
		if (operation.equalsIgnoreCase("-"))
			setTextAfterCalculation(substractNumbers(firstNumber, secondNumber));
		if (operation.equalsIgnoreCase("*"))
			setTextAfterCalculation(multiplyNumbers(firstNumber, secondNumber));
		if (operation.equalsIgnoreCase("÷"))
			setTextAfterCalculation(divideNumbers(firstNumber, secondNumber));
		if (operation.equalsIgnoreCase("/"))
			setTextAfterCalculation(remainderOfNumbers(firstNumber, secondNumber));
	}

	public double remainderOfNumbers(double firstNumber, double secondNumber) {
		return firstNumber % secondNumber;
	}

	public double divideNumbers(double firstNumber, double secondNumber) {
		return firstNumber / secondNumber;
	}

	public double multiplyNumbers(double firstNumber, double secondNumber) {
		return firstNumber * secondNumber;

	}

	public double addNumbers(double firstNumber, double secondNumber) {
		return firstNumber + secondNumber;
	}

	public double substractNumbers(double firstNumber, double secondNumber) {
		return firstNumber - secondNumber;

	}

	private boolean isFirstNumber() {
		return lblResult.getText().isEmpty();
	}

	public boolean isNumberNullOrZero(double number) {
		return (String.valueOf(number).isEmpty() || number == 0) ? true : false;

	}

	public void setTextAfterCalculation(double calculationResult) {
		result = calculationResult;
		lblInputNumber.setText("" + result);
		lblResult.setText(lblResult.getText() + String.valueOf(secondNumber));
	}

	public void changeSignBeforeNumberTo(char charToChangeTo) {
		lblInputNumber.setText(charToChangeTo + lblInputNumber.getText());
	}

	public boolean doesContainNegativOrPositivSign(String string) {
		if (string.contains("-") || string.contains("+")) {
			return true;
		} else {
			return false;
		}
	}

	@FXML
	public void addDecimal(ActionEvent event) {
		if (!isDecimal) {
			lblInputNumber.setText(lblInputNumber.getText() + '.');
			isDecimal = true;
		}
	}

	public String removeLastChar(String text) {
		if (text != null && text.length() > 0) {
			return text.substring(0, text.length() - 1);
		}
		return text;
	}

	public String removeFirstChar(String text) {
		if (text != null && text.length() > 0) {
			return text.substring(1, text.length());
		}
		return text;
	}
}
