package com.github.aureliano.achmed.server.service;

import java.util.Map;

public interface IService {

	public abstract byte[] consume(Map<String, String> parameters);
}