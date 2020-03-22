package com.example.location.util;

public class SessionDetail {
	private Object objectToStore;
	private String key;
	public Object getObjectToStore() {
		return objectToStore;
	}
	public void setObjectToStore(Object objectToStore) {
		this.objectToStore = objectToStore;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "SessionDetail [objectToStore=" + objectToStore + ", key=" + key + "]";
	}
	
	
}
