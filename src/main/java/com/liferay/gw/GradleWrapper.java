package com.liferay.gw;

/**
 * @author David Truong
 * @author Gregory Amerson
 */
public class GradleWrapper {
	public static void main(String[] args) {
		WrapperExecutor wrapperExecutor = new WrapperExecutor("gradlew");

		try {
			wrapperExecutor.execute(args);
		}
		catch (Exception e) {
			System.err.println("Error executing gradle wrapper");

			e.printStackTrace();
		}
	}
}
