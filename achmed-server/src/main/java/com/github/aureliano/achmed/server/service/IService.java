package com.github.aureliano.achmed.server.service;

import java.io.OutputStream;
import java.util.Map;

public interface IService {

	public abstract byte[] consume(OutputStream outputStream, Map<String, String> parameters);
}