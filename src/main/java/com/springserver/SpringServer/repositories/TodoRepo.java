package com.springserver.SpringServer.repositories;

import com.springserver.SpringServer.enums.Enums;
import com.springserver.SpringServer.models.TodoModel;

import java.util.ArrayList;
import java.util.List;

public class TodoRepo {

	private final List<TodoModel> todoModelList = new ArrayList<>();

	public TodoRepo() {
		this.todoModelList.add(new TodoModel("Todo", "some sample data in todo 1"));
		this.todoModelList.add(new TodoModel("Todo2", "some sample data in todo 2"));
		this.todoModelList.add(new TodoModel("Todo3", "some sample data in todo 3"));
	}

	public List<TodoModel> getTodos() {
		return todoModelList;
	}

	public List<TodoModel> getTodos(int id) {
		List<TodoModel> todoModels = new ArrayList<>();
		try {
			todoModels.add(todoModelList.get(id));
		} catch (Exception ignored) { }
		return todoModels;
	}

	public void addTodo(TodoModel todo) {
		this.todoModelList.add(todo);
	}

	public int removeTodo(int todoIndex) {
		try {
			this.todoModelList.remove(todoIndex);
			return Enums.SUCCESS;
		} catch (IndexOutOfBoundsException e) {
			return Enums.NOT_EXISTS;
		} catch (Exception e) {
			return Enums.FAILURE;
		}
	}
}
