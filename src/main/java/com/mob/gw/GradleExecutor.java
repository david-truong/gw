package com.mob.gw;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dtruong on 1/26/16.
 */
public class GradleExecutor {

	private String getExecutable(File dir) throws Exception {

		File gradlew = findGradleWrapper(dir);

		String executable = "gradle";

		if (gradlew.exists()) {
			executable = gradlew.getCanonicalPath();

			String os = System.getProperty("os.name");

			os = os.toLowerCase();

			if (os.startsWith("windows")) {
				executable = executable + ".bat";
			}
		}

		return executable;
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
		File currentDir = new File(System.getProperty("user.dir"));

		ProcessBuilder processBuilder = new ProcessBuilder();

		processBuilder.inheritIO();

		List<String> commands = new ArrayList<>(args.length + 1);

		commands.add(getExecutable(currentDir));
		commands.addAll(Arrays.asList(args));

		processBuilder.command(commands);

		processBuilder.directory(currentDir);

		Process process = processBuilder.start();

		return process.waitFor();
	}
}
