package com.github.aureliano.achmed.server.service;

import java.net.Socket;

public interface IService {

	public abstract void consume(Socket socket);
}