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

/**
 * @author David Truong
 * @author Gregory Amerson
 */
public class GradleWrapper {

	public static void main(String[] args) {
		WrapperExecutor wrapperExecutor = new WrapperExecutor("gradlew");

		int exitCode = 0;

		try {
			exitCode = wrapperExecutor.execute(args);
		}
		catch (Exception e) {
			System.err.println("Error executing gradle wrapper");

			e.printStackTrace();

			exitCode = -1;
		}

		System.exit(exitCode);
	}

}