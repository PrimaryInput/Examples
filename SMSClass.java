package com.example.sms;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;

public class SMSClass {

	public static void main(String[] args) {
		
		//Used for authenticating to AWS
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("Access_Key", "Secret_Access_Key");
		
		//Create SNS Client
		AmazonSNS snsClient = AmazonSNSClient
                .builder()
                .withRegion(Regions.AP_SOUTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
		
		String SMSMessage = "Sent using AWS SNS";
		String mobile = "+61XXXXX";//Enter your mobile number here
		
		snsClient.publish(new PublishRequest().withMessage(SMSMessage).withPhoneNumber(mobile));
		
		

	}

}
