package com.github.aureliano.achmed.os.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.logging.Logger;

import com.github.aureliano.achmed.client.command.CommandFacade;
import com.github.aureliano.achmed.client.command.CommandResponse;
import com.github.aureliano.achmed.client.exception.ServiceResourceException;
import com.github.aureliano.achmed.common.helper.StringHelper;
import com.github.aureliano.achmed.common.logging.LoggingFactory;

public class DebianService extends LinuxService {

	private static final Logger logger = LoggingFactory.createLogger(DebianService.class);
	private static final String SERVICE = "/usr/sbin/service";
	private static final String UPDATE_RC = "/usr/sbin/update-rc.d";
	private static final String INVOKE_RC = "/usr/sbin/invoke-rc.d";
	
	public DebianService() {
		super();
	}
	
	@Override
	public CommandResponse start() {
		if (this.isRunning()) {
			logger.fine("Service " + super.properties.getName() + " is already running.");
			return null;
		}
		
		if (StringHelper.isEmpty(super.properties.getBinary())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE, super.properties.getName(), "start");
			super.assertServiceIsRunning();
			
			return res;
		}
		
		CommandResponse res = super.start();
		super.assertServiceIsRunning();
		
		return res;
	}
	
	@Override
	public CommandResponse stop() {
		if (!this.isRunning()) {
			logger.fine("Service " + super.properties.getName() + " is not running.");
			return null;
		}
		
		if (StringHelper.isEmpty(super.properties.getBinary())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE, super.properties.getName(), "stop");
			super.assertServiceIsStopped();
			
			return res;
		}
		
		CommandResponse res = super.stop();
		super.assertServiceIsStopped();
		
		return res;
	}
	
	@Override
	public CommandResponse restart() {
		if ((super.properties.getHasRestart() != null) && (super.properties.getHasRestart())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE, super.properties.getName(), "restart");
			super.assertServiceIsRunning();
			
			return res;
		}
		
		CommandResponse res = super.restart();
		super.assertServiceIsRunning();
		
		return res;
	}
	
	@Override
	public boolean isRunning() {
		if ((super.properties.getHasStatus() != null) && (super.properties.getHasStatus())) {
			CommandResponse res = CommandFacade.executeCommand(SERVICE, super.properties.getName(), "status");
			return (res.getExitStatusCode() == 0);
		}
		
		return super.isRunning();
	}
	
	public boolean isEnabledInBootstrap() {
		CommandResponse res = CommandFacade.executeCommand(
			INVOKE_RC, "--quiet", "--query", super.properties.getName(), "start");
		
		if (Arrays.asList(104, 106).contains(res.getExitStatusCode())) {
			return true;
		} else if (Arrays.asList(101, 105).contains(res.getExitStatusCode())) {
			return (this.getStartLinkCount() >= 4);
		} else {
			return false;
		}
	}

	public CommandResponse enableBootstrap() {
		if (this.isEnabledInBootstrap()) {
			logger.fine("Service " + super.properties.getName() + " is already enabled to initialize in bootstrap.");
			return null;
		}
		
		CommandResponse res = CommandFacade.executeCommand(UPDATE_RC, "-f", super.properties.getName(), "remove");
		if (!res.isOK()) {
			throw new ServiceResourceException(res);
		}
		
		res = CommandFacade.executeCommand(UPDATE_RC, super.properties.getName(), "defaults");
		super.assertServiceIsEnabledInBootstrap();
		
		return res;
	}

	public CommandResponse disableBootstrap() {
		if (!this.isEnabledInBootstrap()) {
			logger.fine("Service " + super.properties.getName() + " is not enabled to initialize in bootstrap.");
			return null;
		}
		
		CommandResponse res = CommandFacade.executeCommand(UPDATE_RC, "-f", super.properties.getName(), "remove");
		if (!res.isOK()) {
			throw new ServiceResourceException(res);
		}
		
		res = CommandFacade.executeCommand(
			UPDATE_RC, super.properties.getName(), "stop", "00", "1", "2", "3", "4", "5", "6", ".");
		super.assertServiceIsDisabledInBootstrap();
		
		return res;
	}
	
	private int getStartLinkCount() {
		EtcFileVisitor matcherVisitor = new EtcFileVisitor();
		
		try {
			Files.walkFileTree(Paths.get("/etc"), matcherVisitor);
		} catch (IOException ex) {
			throw new ServiceResourceException(ex);
		}
		
		return matcherVisitor.getCounter();
	}
	
	private class EtcFileVisitor extends SimpleFileVisitor<Path> {
		
		private PathMatcher matcher;
		private int counter = 0;
		
		public EtcFileVisitor() {
			String pattern = "glob:/etc/rc*.d/S??" + "nginx";
			this.matcher = FileSystems.getDefault().getPathMatcher(pattern);
		}
		
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if (matcher.matches(file)) {
				System.out.println(file);
			}
			
			return FileVisitResult.CONTINUE;
		}
		
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			return FileVisitResult.CONTINUE;
		}
		
		public int getCounter() {
			return counter;
		}
	}
}