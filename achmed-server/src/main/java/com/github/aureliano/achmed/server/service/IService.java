package com.github.aureliano.achmed.server.service;

import java.net.Socket;
import java.util.Map;

public interface IService {

	public abstract String consume(Socket socket, Map<String, String> parameters);
}