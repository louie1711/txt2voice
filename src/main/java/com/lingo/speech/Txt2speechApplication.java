package com.lingo.speech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;


// tell spring where to component scan
//@ComponentScan("aws.apis.sound")
//@Configuration  dont need this one

@SpringBootApplication
@ComponentScan({"aws.apis.sound","com.lingoquiz.txt2speech"})
public class Txt2speechApplication {

	public static void main(String[] args) {
		SpringApplication.run(Txt2speechApplication.class, args);
		
		System.out.println("spring (core) version = "+SpringVersion.getVersion());
	}
	
}
