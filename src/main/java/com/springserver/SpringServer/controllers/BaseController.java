package com.springserver.SpringServer.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
class BaseController {

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public Map<String, Object> errorHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		int statusCode;
		String message;

		System.out.println(exception.getMessage());
		switch (exception.getMessage()) {
			default: statusCode = 400;
			message = "Something went wrong!";
		}
		response.setStatus(500);
		return Map.ofEntries(
				Map.entry("status", statusCode),
				Map.entry("message", message)
		);
	}
}