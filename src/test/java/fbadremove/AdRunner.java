package fbadremove;

import org.testng.TestNG;

public class AdRunner {

	public static void main(String[] args) {
		TestNG runner = new TestNG();
		runner.setTestClasses(new Class[] { ADRemoveTest.class });
		runner.run();
	}
}
