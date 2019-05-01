/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.gw;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David Truong
 * @author Gregory Amerson
 */
public class WrapperExecutor {

	public WrapperExecutor(String executableName) {
		_executableName = executableName;
	}

	public int execute(String[] args) throws Exception {
		File currentDir = new File(System.getProperty("user.dir"));

		List<String> commands = new ArrayList<>();

		commands.add(_getExecutable(currentDir));

		for (String arg : args) {
			if (_executableName.equals("gradlew") && !arg.startsWith("-") && arg.contains(File.separator)) {
				commands.add(arg.replace(File.separatorChar, ':'));
			}
			else {
				commands.add(arg);
			}
		}

		ProcessBuilder processBuilder = new ProcessBuilder();

		processBuilder.inheritIO();
		processBuilder.command(commands);
		processBuilder.directory(currentDir);

		Process process = processBuilder.start();

		return process.waitFor();
	}

	private String _findWindowsWrapper(File dir) {
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

	private File _findWrapper(File dir) {
		if (dir == null) {
			return null;
		}

		File wrapper = new File(dir, _executableName);

		if (wrapper.exists()) {
			return wrapper;
		}

		return _findWrapper(dir.getParentFile());
	}

	private String _getExecutable(File dir) throws Exception {
		String executable = null;

		File wrapper = _findWrapper(dir);

		if ((wrapper != null) && wrapper.exists()) {
			executable = wrapper.getCanonicalPath();

			String os = System.getProperty("os.name");

			os = os.toLowerCase();

			if (os.startsWith("windows")) {
				executable = _findWindowsWrapper(wrapper.getAbsoluteFile());
			}
		}

		return executable;
	}

	private String _executableName;

}