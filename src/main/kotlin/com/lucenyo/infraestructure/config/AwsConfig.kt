package com.lucenyo.infraestructure.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig(
  @Value("\${amazon.aws.access-key}") val accessKey: String,
  @Value("\${amazon.aws.secret-key}") val secretKey: String,
  @Value("\${amazon.aws.region}") val region: String
) {

  @Bean
  fun amazonS3Client(): AmazonS3 {
    return AmazonS3ClientBuilder
      .standard()
      .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
      .withRegion(region)
      .build()
  }

}