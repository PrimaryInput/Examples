package com.example.recogniseImages;

import java.util.List;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.Label;

public class LabelImage {

	public static void main(String[] args) {
		
		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
		
        String image = "Enter image name";
        String s3Bucket = "Enter bucket name";
        
        S3Object s3Obj = new S3Object();
        s3Obj.withBucket(s3Bucket);
        s3Obj.withName(image);
        
        Image img = new Image();
        img.withS3Object(s3Obj);
        
        DetectLabelsRequest request = new DetectLabelsRequest();
        request.withImage(img);
        request.withMaxLabels(10);
        request.withMinConfidence(90F);
        
        try {
        	
            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            List<Label> labels = result.getLabels();
            
            for(Label label: labels)
            {
            	System.out.println("Label ::" + label.getName());
            	System.out.println("Confidence ::" + label.getConfidence());
            }
        }
        catch(AmazonRekognitionException e)
        {
        	e.printStackTrace();
        }

	}

}
