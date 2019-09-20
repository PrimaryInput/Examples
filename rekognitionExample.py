import boto3
import pprint

# Create an boto client for Amazon Rekognition
client = boto3.client(
    "rekognition",
    aws_access_key_id="Enter Access Key",
    aws_secret_access_key="Enter Secret Access Key",
    region_name="ap-southeast-2"
)

# Example to detect text.
response = client.detect_text(
    Image={

        'S3Object': {
            'Bucket': 'testrekognition123',
            'Name': 'primaryInput.JPG'
        }
    }
)

#print the json response
pprint.pprint(response)


