package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import application.controller.CalculatorController;
 
public class TestCalculator {
	CalculatorController calcObj=new CalculatorController();
	@Test
	public void addNumbers() {
		double actual=calcObj.addNumbers(2.0,5.0);
		double expected=7.0;
		Assert.assertEquals(0, Double.compare(expected, actual));
	}
	@Test
	public void substractNumbers() {
		double actual=calcObj.substractNumbers(3,1);
		double expected=2.0;
		Assert.assertEquals(0, Double.compare(expected, actual));
	}
	@Test
	public void multiplyNumbers() {
		double actual=calcObj.multiplyNumbers(3,2);
		double expected=6.0;
		Assert.assertEquals(0, Double.compare(expected, actual));
	}
	@Test
	public void divideNumbers() {
		double actual=calcObj.divideNumbers(6,2);
		double expected=3.0;
		Assert.assertEquals(0, Double.compare(expected, actual));
	}
	
	@Test
	public void remainderOfNumbers() {
		double actual=calcObj.remainderOfNumbers(5,2);
		double expected=1.0;
		Assert.assertEquals(0, Double.compare(expected, actual));
	}
	
	@Test
	public void removeLastChar() {
		String actual =calcObj.removeLastChar("test");
		String expected="tes";
		Assert.assertTrue(actual.equals(expected));
	}
	
	@Test
	public void removeFirstChar() {
		String actual =calcObj.removeFirstChar("test");
		String expected="est";
		Assert.assertTrue(actual.equals(expected));
	}
	
	@Test
	public void doesContainNegativOrPizitivSign() {
		String text="-22";
	Assert.assertTrue(calcObj.doesContainNegativOrPositivSign(text));
	}
	
	
}
