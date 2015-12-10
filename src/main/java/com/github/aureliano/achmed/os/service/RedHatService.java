package com.github.aureliano.achmed.os.service;

import com.github.aureliano.achmed.command.CommandResponse;

public class RedHatService extends LinuxService {

	public RedHatService() {
		super();
	}

	@Override
	public CommandResponse enableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public CommandResponse disableBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public boolean isEnabledInBootstrap() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
}