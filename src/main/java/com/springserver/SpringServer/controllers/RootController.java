package com.springserver.SpringServer.controllers;

import com.springserver.SpringServer.utils.MessageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Controller
class RootController extends BaseController{

	@RequestMapping("/")
	@ResponseBody
	Map<String, String> root() {
		return Map.ofEntries(
				Map.entry("path", "/"),
				Map.entry("success", "true")
		);
	}

	private void setCommonResponseHeader(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Server", "SPRING_SERVER");
		response.setLocale(request.getLocale());
	}

	@RequestMapping("**")
	Map<String, String> wildRequest(HttpServletRequest request, HttpServletResponse response) {
		String message, reason;
		Integer statusCode;

		reason = "URL_NOT_FOUND";
		message = MessageUtils.getMessage(reason);
		statusCode = 500;

		response.setStatus(statusCode);
		response.setHeader("Reason", reason);
		this.setCommonResponseHeader(request, response);

		return Map.ofEntries(
				Map.entry("error", "true"),
				Map.entry("statusCode", statusCode.toString()),
				Map.entry("message", message),
				Map.entry("date", new Date().toString())
		);
	}
}
