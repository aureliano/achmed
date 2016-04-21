package com.github.aureliano.achmed.client.helper;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.github.aureliano.achmed.client.command.CommandBuilder;
import com.github.aureliano.achmed.client.command.CommandFacade;
import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.client.exception.PackageResourceException;
import com.github.aureliano.achmed.common.helper.StringHelper;

public final class PkgManagerHelper {

	private PkgManagerHelper() {
		throw new InstantiationError(this.getClass().getName() + " cannot be instantiated.");
	}
	
	public static List<String> scanRepositoryOptions(List<String> options) {
		List<String> scannedOptions = new ArrayList<>();
		
		if (options.isEmpty()) {
			return scannedOptions;
		}
		
		String[] repoParams = { "enablerepo", "disablerepo", "disableexcludes" };
		String regex = "^\\s*--${repoParam}\\s*=?\\s*";
		
		for (String opt : options) {
			for (String repoParam : repoParams) {
				String _regex = regex.replaceFirst("\\$\\{repoParam\\}", repoParam);
				if (!StringHelper.match(_regex, opt).isEmpty()) {
					scannedOptions.add(opt);
					break;
				}
			}
		}
		
		return scannedOptions;
	}
	
	public static List<Map<String, String>> yumCheckUpdates(String packageName, List<String> options) {
		List<String> command = Arrays.asList("yum", "check-update");
		command.addAll(options);
		
		CommandResponse res = CommandFacade.executeCommand(command.toArray(new String[0]));
		if (res.getExitStatusCode() == 100) {
			return parseCheckUpdate(res.getOutput());
		} else if (res.getExitStatusCode() == 0) {
			return new ArrayList<>();
		} else {
			throw new PackageResourceException(
				String.format("Could not check for updates, '%s' exited with %d.", res.getCommand(), res.getExitStatusCode()));
		}
	}
	
	public static CommandBuilder buildCommand(String cmd) {
		return new CommandBuilder()
			.withCommand(cmd)
			.withVerbose(false)
			.withWorkingDir(new File("").getAbsolutePath())
			.withTries(1)
			.withTimeout(CommandFacade.DEFAULT_TIMEOUT_EXECUTION);
	}
	
	protected static List<Map<String, String>> parseCheckUpdate(String output) {
		String[] parts = output.split("\\n\\n");
		output = parts[parts.length - 1];
		
		Scanner scanner = new Scanner(output);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		while (scanner.hasNextLine()) {
			String[] tuple = scanner.nextLine().split("\\s+");
			if (!StringHelper.match("^(Obsoleting|Security:|Update)", tuple[0]).isEmpty()) {
				break;
			}
			
			Map<String, String> hash = parseHash(tuple[0], tuple[1]);
			list.add(hash);
		}
				
		return list;
	}
	
	protected static Map<String, String> parseHash(String name, String version) {
		String[] pkg = name.split("\\.");
		List<String> match = StringHelper.match("^(?:(\\d+):)?(\\S+)-(\\S+)$", version);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", pkg[0]);
		map.put("arch", pkg[1]);
		map.put("epoch", (StringHelper.isEmpty(match.get(1))) ? "0" : match.get(1));
		map.put("version", match.get(2));
		map.put("release", match.get(3));
		
		return map;
	}
}