package com.github.aureliano.achmed.server.control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.github.aureliano.achmed.server.model.RequestedFileModel;

public final class RequestedFilesControl {

	private static RequestedFilesControl instance;
	private Set<RequestedFileModel> requestedFiles;
	
	private RequestedFilesControl() {
		this.requestedFiles = new HashSet<>();
	}
	
	public static RequestedFilesControl instance() {
		if (instance == null) {
			instance = new RequestedFilesControl();
		}
		
		return instance;
	}
	
	public boolean addRequest(String id, String filePath) {
		return this.addRequest(new RequestedFileModel()
			.withId(id)
			.withFilePath(filePath)
			.withTime(System.currentTimeMillis()));
	}
	
	public boolean addRequest(RequestedFileModel request) {
		return this.requestedFiles.add(request);
	}
	
	public RequestedFileModel get(String id) {
		RequestedFileModel wanted = new RequestedFileModel().withId(id);
		Iterator<RequestedFileModel> iterator = this.requestedFiles.iterator();
		
		while (iterator.hasNext()) {
			RequestedFileModel model = iterator.next();
			if (model.equals(wanted)) {
				return model;
			}
		}
		
		return null;
	}
	
	public void removeOldRequests(long lessTimeMillis) {
		long seed = System.currentTimeMillis() - lessTimeMillis;
		Iterator<RequestedFileModel> iterator = this.requestedFiles.iterator();
		List<RequestedFileModel> removable = new ArrayList<>();
		
		while (iterator.hasNext()) {
			RequestedFileModel model = iterator.next();
			if (model.getTime() <= seed) {
				removable.add(model);
			}
		}
		
		for (RequestedFileModel model : removable) {
			this.requestedFiles.remove(model);
		}
	}
	
	public int size() {
		return this.requestedFiles.size();
	}
}