import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;



public class HoodpopperTest {

	static WebDriver driver = new HtmlUnitDriver();
	String code1 = "a = 3\nb = 4\nc = a * b\nputs \"c = \" + c";
	String code2 = "a=3\nb=4\nc=a/b";
	
	//begin each test with a fresh Hoodpopper page
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}
	
	//*****************************************************************************************
	//User Story 1
	//As a Hoodpopper user and Ruby developer
	//I would like to be able to tokenize the Ruby code
	//So that I can ensure the code is tokenized correctly.
	
	//Given that I am on the Hoodpopper site
	//And I type in code that contains spaces
	//When I use the tokenize button to tokenize the code
	//Then I should find a token containing a space in the results.
	@Test
	public void tokenizeSpaceTest() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code1);
			
			//click the tokenize button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Tokenize']"));
			btn.click();
			
			//check for spaces
			WebElement code = driver.findElement(By.xpath("//p[1]"));
			String results = code.getText();
			assertTrue(results.contains("on_sp, \" \""));			
		} catch (NoSuchElementException nseex) {
			fail();
		}	
	}
	
	//Given that I am on the Hoodpopper site
	//And I the code I type in does not contain a division operator
	//When I use the tokenize button to tokenize the code
	//Then I should not find a token containing a division operator in the results.
	@Test
	public void tokenizeDivisionTest() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code1);
			
			//click the tokenize button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Tokenize']"));
			btn.click();
			
			//check for division operator
			WebElement code = driver.findElement(By.xpath("//p[1]"));
			String results = code.getText();
			assertFalse(results.contains("on_op, \"/\""));			
		} catch (NoSuchElementException nseex) {
			fail();
		}	
	}
	
	//Given that I am on the Hoodpopper site
	//And I the code I type in does not contain any spaces
	//When I use the tokenize button to tokenize the code
	//Then I should not find a token containing a space in the results.
	@Test
	public void tokenizeNoSpaceTest() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code2);
			
			//click the tokenize button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Tokenize']"));
			btn.click();
			
			//check for spaces in the tokens
			WebElement code = driver.findElement(By.xpath("//p[1]"));
			String results = code.getText();
			assertFalse(results.contains("on_op, \" \""));	
		} catch (NoSuchElementException nseex) {
			fail();
		}	
	}
	
	//Given that I am on the Hoodpopper site
	//And I the code I type in contains the puts command
	//When I use the tokenize button to tokenize the code
	//Then I should find a token containing the puts identifier.
	@Test
	public void tokenizePutsTest() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code1);
			
			//click the tokenize button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Tokenize']"));
			btn.click();
			
			//check for the puts identifier
			WebElement code = driver.findElement(By.xpath("//p[1]"));
			String results = code.getText();
			assertTrue(results.contains("on_ident, \"puts\""));	
		} catch (NoSuchElementException nseex) {
			fail();
		}	
	}	
	
	//*********************************************************************	
	//User Story 2
	//As a Hoodpopper user and Ruby developer
	//I would like to see the parsed abstract syntax tree
	//So that I can verify that the correct tokens are in the tree.
	
	//Given that I am on the Hoodpopper site
	//And I the code I type in contains spaces
	//When I use the parse button to parse the code
	//Then I should not find any spaces in the abstract syntax tree.
	@Test
	public void parseNoSpace() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code1);
			
			//click the parse button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Parse']"));
			btn.click();
			
			//check the abstract syntax tree for spaces
			WebElement code = driver.findElement(By.xpath("/html/body/p[2]"));
			String results = code.getText();
			assertFalse(results.contains("- "));	
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I am on the Hoodpopper site
	//And I the code I type in contains the multiplication operator
	//When I use the parse button to parse the code
	//Then I should find the multiplication operator in the abstract syntax tree.
	@Test
	public void parseMultOperator() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code1);
			
			//click the parse button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Parse']"));
			btn.click();
			
			//check the abstract syntax tree for multiplication operator
			WebElement code = driver.findElement(By.xpath("/html/body/p[2]"));
			String results = code.getText();
			assertTrue(results.contains("-*"));	
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I am on the Hoodpopper site
	//And I the code I type in contains the puts operator
	//When I use the parse button to parse the code
	//Then I should find the puts operator in the abstract syntax tree following an @ident.
	@Test
	public void parsePutsOperator() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code1);
			
			//click the parse button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Parse']"));
			btn.click();
			
			//check the abstract syntax tree for puts operator
			WebElement code = driver.findElement(By.xpath("/html/body/p[2]"));
			String results = code.getText();
			assertTrue(results.contains("-@ident\n---puts"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//***************************************************************************************
	//User Story 3
	//As a Hoodpopper user and Ruby developer
	//I would like view the compiled bytecode
	//So that I can trace the code being run by the Ruby Virtual Machine.

	//Given that I am on the Hoodpopper site
	//And I the code I type in contains the puts operator
	//When I use the compile button to compile the code
	//Then I should find the putstring operator in the YARV code.
	@Test
	public void compilePutsOperator() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code1);
			
			//click the parse button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Compile']"));
			btn.click();
			
			//check the abstract syntax tree for putstring
			WebElement code = driver.findElement(By.xpath("/html/body/p[1]"));
			String results = code.getText();
			assertTrue(results.contains("putstring"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I am on the Hoodpopper site
	//And I the code I type in contains the multiplication operator
	//When I use the compile button to compile the code
	//Then I should find the opt_mult operator in the YARV code.
	@Test
	public void compileMultOperator() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code1);
			
			//click the parse button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Compile']"));
			btn.click();
			
			//check the abstract syntax tree for opt_mult
			WebElement code = driver.findElement(By.xpath("/html/body/p[1]"));
			String results = code.getText();
			assertTrue(results.contains("opt_mult"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I am on the Hoodpopper site
	//And I the code I type in does not contain the puts operator
	//When I use the compile button to compile the code
	//Then I should not find the putstring operator in the YARV code.
	@Test
	public void compilePutsOperatorMissing() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code2);
			
			//click the parse button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Compile']"));
			btn.click();
			
			//check the abstract syntax tree for spaces
			WebElement code = driver.findElement(By.xpath("/html/body/p[1]"));
			String results = code.getText();
			assertFalse(results.contains("putstring"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I am on the Hoodpopper site
	//And I the code I type in contains an integer value of 4 assignment to a variable
	//When I use the compile button to compile the code
	//Then I should find the putobject operator, followed by 4, in the YARV code.
	@Test
	public void compileIntAssignmentOperator() {
		try {
			//type code into screen
			WebElement e = driver.findElement(By.id("code_code"));
			e.sendKeys(code1);
			
			//click the parse button
			WebElement btn = driver.findElement(By.xpath("//input[@value='Compile']"));
			btn.click();
			
			//check the abstract syntax tree for putobject
			WebElement code = driver.findElement(By.xpath("/html/body/p[1]"));
			String results = code.getText();
			assertTrue(results.contains("putobject 4"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
}
