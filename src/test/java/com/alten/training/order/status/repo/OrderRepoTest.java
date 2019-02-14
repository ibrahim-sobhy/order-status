package com.alten.training.order.status.repo;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class OrderRepoTest {

    @Test
    public void shouldDownloadFromS3NormalSDK() throws IOException {

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJ2DRTBRBQMIKHJ3Q","CBy80ROiquEHlw5DWlJimlbiXlouEamzsfUam9Cn");
        String region = "us-east-1";
        String bucketName = "alten-order-track";

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, "test.txt"));

        System.out.println("Content Type " + s3Object.getObjectMetadata().getContentType());
        System.out.println("File size " + s3Object.getObjectMetadata().getContentLength());
    }
}