package com.github.aureliano.achmed.os.service;

import com.github.aureliano.achmed.command.CommandResponse;

public interface IService {

	public abstract CommandResponse start();
	
	public abstract CommandResponse stop();
	
	public abstract CommandResponse restart();
	
	public abstract boolean isRunning();
	
	public abstract CommandResponse enableBootstrap();
	
	public abstract CommandResponse disableBootstrap();
}