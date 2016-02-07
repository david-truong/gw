package com.mob.gw;

import java.io.File;

/**
 * Created by dtruong on 1/26/16.
 */
public class GradleExecutor {

	private String getExecutable() throws Exception {
		if (executable != null && !executable.equals("")) {
			return executable;
		}

		String currentPath = System.getProperty("user.dir");

		File dir = new File(currentPath);

		File gradlew = findGradleWrapper(dir);

		if (gradlew.exists()) {
			executable = gradlew.getCanonicalPath();

			String os = System.getProperty("os.name");

			os = os.toLowerCase();

			if (os.startsWith("windows")) {
				executable = executable + ".bat";
			}

			return executable;
		}
		else {
			return executable = "gradle";
		}
	}

	private File findGradleWrapper(File dir) {
		if (dir == null) {
			return null;
		}

		File gradlew = new File(dir, "gradlew");

		if (gradlew.exists()) {
			return gradlew;
		}

		return findGradleWrapper(dir.getParentFile());
	}

	public int execute(String[] args) throws Exception {
		ProcessBuilder processBuilder = new ProcessBuilder();

		processBuilder.inheritIO();

		StringBuffer sb = new StringBuffer(args.length);

		for (String arg : args) {
			sb.append(arg);
		}

		processBuilder.command(getExecutable(), sb.toString());

		Process process = processBuilder.start();

		return process.waitFor();
	}

	private String executable;
}
