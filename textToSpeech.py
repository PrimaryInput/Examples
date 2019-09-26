import boto3
import pprint

# Create an boto client for Amazon Polly
client = boto3.client(
    "polly",
    aws_access_key_id="Enter Access Key",
    aws_secret_access_key="Enter Secret Access Key",
    region_name="ap-southeast-2"
)

textFile = open("TwinkleNurseryRhyme.txt","r")
response = client.synthesize_speech(Text=textFile.read(), OutputFormat="mp3",
                                        VoiceId="Nicole")


#print the json response
pprint.pprint(response)

speechFile = open("NurseryRhyme.mp3","wb")
speechFile.write(response['AudioStream'].read())
speechFile.close()

textFile.close()




