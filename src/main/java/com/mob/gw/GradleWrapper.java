package com.mob.gw;

import java.io.File;

/**
 * @author David Truong
 */
public class GradleWrapper {
	public static void main(String[] args) throws Exception {
		GradleExecutor gradleExecutor = new GradleExecutor();

		gradleExecutor.execute(args);
	}
}
