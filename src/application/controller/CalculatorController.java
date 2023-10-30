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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setVariablesForStart();
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
		}
		operation = calculateButton.getText();
		lblInputNumber.setText(operation);
		enteringSecondNumber = true;
	}

	@FXML
	public void calculateResult(ActionEvent event) {
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

	public void setTextAfterCalculation(double result) {
		lblResult.setText(lblResult.getText() + String.valueOf(secondNumber) + "=" + result);
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

	}

	@FXML
	public void addDecimal(ActionEvent event) {

	}

	@FXML
	public void clearAll() {
		setVariablesForStart();
	}

	private String removeLastChar(String text) {
		if (text != null && text.length() > 0) {
			return text.substring(0, text.length() - 1);
		}
		return text;
	}
}
