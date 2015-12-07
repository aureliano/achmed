package com.github.aureliano.achmed.helper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PkgManagerHelperTest {

	@Test
	public void testScanRepositoryOptions() {
		List<String> options = Arrays.asList(
			"test", "--disablerepo =  whatever", "-q 45", "abc",
			"--disableexcludes= test", "--enablerepo=defgh");
		
		List<String> expected = Arrays.asList("--disablerepo =  whatever", "--disableexcludes= test", "--enablerepo=defgh");
		List<String> actual = PkgManagerHelper.scanRepositoryOptions(options);
		assertEquals(expected, actual);
	}
}