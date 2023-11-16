package aws.apis.sound;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.lingo.speech.controllers.VoiceController;
import com.lingo.speech.model.Voice;
import com.lingo.speech.sound.SpeechToSound;

//import com.amazonaws.auth.AWSCredentials;

// keep
// https://stackoverflow.com/questions/51184130/aws-polly-java-example
// https://github.com/aws-amplify/amplify-js/issues/11046
import com.lingo.speech.*;

@Component
public class TxtToSpeech implements SpeechToSound{		
	
	
	@Value("${txt2voice.access}")
	private String access;
	@Value("${txt2voice.secret}")
	private String secret;

	@Override
	public String makeSpeech(Voice voice) {		
		
		var creds = new AWSStaticCredentialsProvider(new BasicAWSCredentials(access, secret));
		
		
		var polly = new AmazonPollyClient(creds, new ClientConfiguration());
		polly.setRegion(Region.getRegion(Regions.US_WEST_2));


		var synthReq = new SynthesizeSpeechRequest()
							.withText("hello my name is amy , I was joking before.")
							.withVoiceId("Amy")
							.withOutputFormat(OutputFormat.Mp3);

		var speechResult = polly.synthesizeSpeech(synthReq);		
		var soundStream = speechResult.getAudioStream();		
		
		Logger logger = LoggerFactory.getLogger(TxtToSpeech.class);
		
		try {
		
		  File targetFile = new File("targetFile.mp3");
		  	logger.warn("========= "+targetFile.getAbsolutePath());

		    java.nio.file.Files.copy(
		    		soundStream, 
		      targetFile.toPath(), 
		      StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException ie) {
			System.out.println(" issue occured when closing the stream ");
		}

		    

		
		
		return "todo";
	}
	
	

}
