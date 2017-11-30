package com.liferay.gw;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author David Truong
 * @author Gregory Amerson
 */
public class WrapperExecutor {

	private String _executableName;

	public WrapperExecutor(String executableName) {
		_executableName = executableName;
	}

	private String getExecutable(File dir) throws Exception {
		String executable = null;

		File wrapper = findWrapper(dir);

		if (wrapper != null && wrapper.exists()) {
			executable = wrapper.getCanonicalPath();

			String os = System.getProperty("os.name");

			os = os.toLowerCase();

			if (os.startsWith("windows")) {
				executable = findWindowsWrapper(wrapper.getAbsoluteFile());
			}
		}

		return executable;
	}

	private String findWindowsWrapper(File dir) {
		if (dir == null) {
			return null;
		}

		String retval = null;

		File wrapperBat = new File(dir, _executableName + ".bat");
		File wrapperCmd = new File(dir, _executableName + ".cmd");

		if (wrapperBat.exists()) {
			retval = wrapperBat.getAbsolutePath();
		}
		else if (wrapperCmd.exists()) {
			retval = wrapperCmd.getAbsolutePath();
		}

		return retval;
	}

	private File findWrapper(File dir) {
		if (dir == null) {
			return null;
		}

		File wrapper = new File(dir, _executableName);

		if (wrapper.exists()) {
			return wrapper;
		}

		return findWrapper(dir.getParentFile());
	}

	public int execute(String[] args) throws Exception {
		File currentDir = new File(System.getProperty("user.dir"));

		List<String> commands = new ArrayList<>();

		commands.add(getExecutable(currentDir));
		commands.addAll(Arrays.asList(args));

		ProcessBuilder processBuilder = new ProcessBuilder();

		processBuilder.inheritIO();
		processBuilder.command(commands);
		processBuilder.directory(currentDir);

		Process process = processBuilder.start();

		return process.waitFor();
	}
}
