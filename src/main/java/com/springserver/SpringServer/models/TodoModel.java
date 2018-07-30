package com.springserver.SpringServer.models;

public class TodoModel {
	private final String name;
	private final String data;

	public String getName() {
		return name;
	}

	public String getData() {
		return data;
	}

	public TodoModel(String name, String data) {
		this.name = name;
		this.data = data;
	}

	@Override
	public String toString() {
		return "{" +
				"name='" + getName() + '\'' +
				", data='" + getData() + '\'' +
				'}';
	}
}
