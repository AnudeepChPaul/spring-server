package com.springserver.SpringServer.controllers;

import com.springserver.SpringServer.enums.Enums;
import com.springserver.SpringServer.models.TodoModel;
import com.springserver.SpringServer.repositories.TodoRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
class TodoController extends BaseController {

	private final TodoRepo todoRepo;

	public TodoController() {
		this.todoRepo = new TodoRepo();
	}

	@RequestMapping(path = "todo", method = RequestMethod.GET)
	public Map<String, String> getTodos() {

		return Map.ofEntries(
				Map.entry("data", this.todoRepo.getTodos().toString()),
				Map.entry("success", "true")
		);
	}


	@RequestMapping(path = "todo/{id}", method = RequestMethod.GET)
	public Map<String, String> getTodo(@PathVariable(value = "id") String id) {

		return Map.ofEntries(
				Map.entry("data", this.todoRepo.getTodos(Integer.parseInt(id)).toString()),
				Map.entry("success", "true")
		);
	}


	@RequestMapping(path = "todo", method = RequestMethod.POST, produces = "application/json")
	public Map<String, String> postTodo(@RequestBody(required = true) ArrayList<HashMap<String, String>> arrayList) {

		try {
			for (HashMap<String, String> hashMap : arrayList) {
				String name = hashMap.get("name"),
						data = hashMap.get("data");

				this.todoRepo.addTodo(new TodoModel(name, data));
			}
		} catch (Exception e) {
			return Map.ofEntries(
					Map.entry("error", "true"),
					Map.entry("msg", "Save Failed!")
			);
		}

		return Map.ofEntries(
				Map.entry("success", "true"),
				Map.entry("msg", "Todo Saved!"),
				Map.entry("data", arrayList.toString())
		);
	}

	@RequestMapping(path = "todo/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Map<String, String> deleteTodo(@PathVariable(value = "id") String id) {

		try {
			int result = this.todoRepo.removeTodo(Integer.parseInt(id));

			switch (result) {
				case Enums.FAILURE:
					return Map.ofEntries(
							Map.entry("error", "true"),
							Map.entry("msg", "Todo Delete Failed!")
					);
				case Enums.NOT_EXISTS:
					return Map.ofEntries(
							Map.entry("error", "true"),
							Map.entry("msg", "Todo Do Not Exists!")
					);
				case Enums.SUCCESS:
				default:
					return Map.ofEntries(
							Map.entry("success", "true"),
							Map.entry("msg", "Todo Deleted!")
					);
			}

		} catch (Exception e) {
			return Map.ofEntries(
					Map.entry("error", "true"),
					Map.entry("msg", "Todo Delete Failed!")
			);
		}
	}
}
