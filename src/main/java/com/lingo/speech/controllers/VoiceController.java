
package com.lingo.speech.controllers;

import org.slf4j.Logger;
//import org.slf4j.LoggerFactory; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lingo.speech.model.Voice;
import com.lingo.speech.sound.SpeechToSound;



@RestController
public class VoiceController {
	
	Logger logger = LoggerFactory.getLogger(VoiceController.class);
	
	@Autowired
	SpeechToSound speechToSound;

	@PostMapping("/voices")
	public Voice makeTxtToSpeech(@RequestBody Voice voice) {
		
		
				
		System.out.println(" --- vosice id = "+voice.voiceId() +" txt = "+voice.txt());
		
		logger.warn("in makeTxtToSpeech controller : ");
		
		String heresSpeech = speechToSound.makeSpeech(voice);
		
		System.out.println("from the bean : "+heresSpeech+" , real obj :"+speechToSound);
		
		return new Voice(voice.voiceId(), voice.txt());		
		
	}
}