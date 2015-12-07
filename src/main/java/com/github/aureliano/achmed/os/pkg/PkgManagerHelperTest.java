package com.github.aureliano.achmed.os.pkg;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.aureliano.achmed.helper.PkgManagerHelper;

public class PkgManagerHelperTest {

	@Test
	public void testScanRepositoryOptions() {
		List<String> options = Arrays.asList(
			"test", "--disablerepo =  whatever", "-q 45", "abc",
			"--disableexcludes= test", "--enablerepo=defgh");
		System.out.println(PkgManagerHelper.scanRepositoryOptions(options));
	}
}