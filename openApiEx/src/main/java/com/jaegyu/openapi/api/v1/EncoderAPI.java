package com.jaegyu.openapi.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/encoder")
public class EncoderAPI {

	@RequestMapping(method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	@ResponseBody
	public String encodeHexString(@RequestParam("msg") String message)
			throws Exception {

		byte[] msgBytes = message.getBytes("UTF8");
		String result = "";

		for (byte value : msgBytes) {
			result += String.format("%02X", value);
		}

		return result;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String onException(Exception e) throws Exception {
		return e.getMessage();
	}
}
