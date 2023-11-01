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
		//ToDO 
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

	private boolean isFirstNumber() {
		return lblResult.getText().isEmpty();
	}

	@FXML
	public void numberClicked(ActionEvent event) {
		Button numberButton = (Button) event.getSource();
		Platform.runLater(() -> {
			if (enteringSecondNumber) {
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
		}
		operation = calculateButton.getText();
		lblInputNumber.setText(operation);
		enteringSecondNumber = true;
	}

	@FXML
	public void calculateResult(ActionEvent event) {
		if (isNumbersNullOrZero(firstNumber, secondNumber)) {
			setTextAfterCalculation(0);
		} else {
			secondNumber = Double.parseDouble(lblInputNumber.getText());
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
	}

	public boolean isNumbersNullOrZero(double firstNumber, double secondNumber) {
		return (String.valueOf(firstNumber).isEmpty() || String.valueOf(secondNumber).isEmpty()||firstNumber == 0 || secondNumber == 0) ? true : false;

	}

	public void setTextAfterCalculation(double result) {
		lblInputNumber.setText("" + result);
		lblResult.setText(lblResult.getText() + String.valueOf(secondNumber) + "=");
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

	@FXML
	public void clearAll() {
		setVariablesForStart();
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
