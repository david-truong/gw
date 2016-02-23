package com.liferay.gw;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author David Truong
 */
public class GradleWrapperTest {
	@Test
	public void testGradleWrapper() throws Exception {
		GradleExecutor gradleExecutor = new GradleExecutor();

		Assert.assertTrue(gradleExecutor.execute(new String[] {"tasks"}) == 0);
	}
}
