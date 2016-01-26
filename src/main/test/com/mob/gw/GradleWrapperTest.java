package com.mob.gw;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by dtruong on 1/26/16.
 */
public class GradleWrapperTest {
	@Test
	public void testGradleWrapper() throws Exception {
		GradleExecutor gradleExecutor = new GradleExecutor();

		Assert.assertTrue(gradleExecutor.execute(new String[] {"tasks"}) == 0);
	}
}
