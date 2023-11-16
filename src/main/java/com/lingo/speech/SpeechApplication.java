package com.lingo.speech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@ComponentScan({"aws.apis.sound","com.lingoquiz.txt2speech"})
public class SpeechApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeechApplication.class, args);
		System.out.println("spring (core) version = "+SpringVersion.getVersion());
	}

}
