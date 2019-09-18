package com.example.s3;

import java.io.File;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.iterable.S3Versions;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.SetBucketVersioningConfigurationRequest;

public class S3Example {

	public static void main(String[] args) {
		
		//Authentication
		AmazonS3 s3Obj = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_2).build();

		//create your first s3 bucket
		Bucket s3Bucket = s3Obj.createBucket("primaryinputbucket");
		//make sure you adhere to bucket naming policies
		
		System.out.println("My First S3 Bucket:: "+s3Bucket.getName());
		
		File file = new File ("D:\\TF\\README.md");
		
		//key is name of s3 file obj
		PutObjectResult objResult = s3Obj.putObject("primaryinputbucket", "README.md", file);		
		
		//Enable Versioning
		BucketVersioningConfiguration bucketVersioningConfig = new BucketVersioningConfiguration(BucketVersioningConfiguration.ENABLED);
		
		SetBucketVersioningConfigurationRequest verConfigReq = new SetBucketVersioningConfigurationRequest ("primaryinputbucket", bucketVersioningConfig);
		
		s3Obj.setBucketVersioningConfiguration(verConfigReq);
		
		//Deleting files in bucket
		s3Obj.deleteObject("primaryinputbucket", "README.md");
		
		//Removes all versions of the bucket
		for ( S3VersionSummary version : S3Versions.inBucket(s3Obj, "primaryinputbucket") ) {
		    String key = version.getKey();
		    String versionId = version.getVersionId();          
		    s3Obj.deleteVersion("primaryinputbucket", key, versionId);
		}

		//After removing all versions, delete the bucket
		s3Obj.deleteBucket("primaryinputbucket");
		
	}

}
