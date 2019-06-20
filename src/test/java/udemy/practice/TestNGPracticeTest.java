package udemy.practice;

import static org.testng.AssertJUnit.assertFalse;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class TestNGPracticeTest {
	
	
	@BeforeClass
	public void testClassStart() {
		System.err.println("Before Class code executing...");
	}
	
	@BeforeTest
	public void testBefore() {
		System.err.println("Before Test code executing...");
	}
	
	@Test(invocationCount=2, threadPoolSize=2)
	public void testOne() {
		System.err.println("Test One method code executing...");
		AssertJUnit.assertEquals("Rama", "Rama");
	}

	@Test(dependsOnMethods = {"testThree","testFour"})
	public void testTwo() {
		System.out.println("Test Two method code executing...");
		assertNotEquals(1, 2);
	}
	
	@Test(enabled = true)
	public void testThree() {
		System.err.println("Test Three method code executing...");
		AssertJUnit.assertTrue(5>3);
	}
	
	@Test()
	public void testFour() {
		System.out.println("Test Four method code executing...");
		assertFalse(5>8);
	}
	
	//@Test
	public void testFive() {
		System.err.println("Test Five method code executing...");
		SoftAssert sa = new SoftAssert();
		Assert.fail();
		System.out.println("Failing...");
		
		AssertJUnit.assertEquals("SithaRama", "SithaRama");
		System.out.println("Passed..");
		
		sa.assertAll();
	}

	//@Test(timeOut=1000)
	public void testSix() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Test Six method code executing...");
	}
	
	@AfterTest
	public void testClassEnd() {
		System.out.println("After Test code executing...");
	}
	
}


class secondTestNGClass {
	
	@Test 
	public void test() {
		 System.err.println("Second class method");
	}
}

