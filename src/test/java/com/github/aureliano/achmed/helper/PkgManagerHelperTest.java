package com.github.aureliano.achmed.helper;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import com.github.aureliano.achmed.command.CommandBuilder;

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
	
	@Test
	public void testBuildCommand() {
		CommandBuilder command = PkgManagerHelper.buildCommand("command_name");
		
		assertEquals("command_name", command.getCommand());
		assertEquals(new Long(10000), command.getTimeout());
		assertEquals(new Integer(1), command.getTries());
		assertEquals(false, command.getVerbose());
		assertEquals(new File("").getAbsolutePath(), command.getWorkingDir());
	}
	
	@Test
	public void testParseCheckUpdate() {
		StringBuilder b = null;
		try (Scanner s = new Scanner(new File("src/test/resources/os/pkg/yum-check-update"))) {
			b = new StringBuilder();
			
			while (s.hasNextLine()) {
				b.append(s.nextLine()).append("\n");
			}
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
		
		List<Map<String, String>> list = PkgManagerHelper.parseCheckUpdate(b.toString());
		assertEquals(194, list.size());
	}
	
	@Test
	public void testParseHash() {
		Map<String, String> map = PkgManagerHelper.parseHash("perl-devel.x86_64", "4:5.10.1-141.el6_7.1");
		
		assertEquals("perl-devel", map.get("name"));
		assertEquals("x86_64", map.get("arch"));
		assertEquals("4", map.get("epoch"));
		assertEquals("5.10.1", map.get("version"));
		assertEquals("141.el6_7.1", map.get("release"));
	}
}