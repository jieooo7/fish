package com.fish.controller;

import com.fish.model.temple.Greeting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name,
							 @RequestParam(value = "tel", defaultValue = "13200000000") String tel, @RequestParam(value = "tel", defaultValue = "passwd") String passwd) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}