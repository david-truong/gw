package com.liferay.gw;

/**
 * @author Gregory Amerson
 */
public class MavenWrapper {
	public static void main(String[] args) {
		WrapperExecutor wrapperExecutor = new WrapperExecutor("mvnw");

		try {
			wrapperExecutor.execute(args);
		}
		catch (Exception e) {
			System.err.println("Error executing gradle wrapper");

			e.printStackTrace();
		}
	}
}
