//package com.example.demo.service;
//
//import java.io.File;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.DeleteObjectRequest;
//import com.amazonaws.services.s3.model.GetObjectRequest;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//
//@Service
//public class S3FileService {
//
//	@Autowired
//	private AmazonS3 s3client;
//
//	public void uploadFile(String bucketName, String keyName, File file) {
//		s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
//	}
//
//	public File downloadFile(String bucketName, String keyName) {
//		File file = new File(keyName); // 다운로드된 파일을 저장할 경로 및 이름 설정
//		s3client.getObject(new GetObjectRequest(bucketName, keyName), file);
//		return file;
//	}
//
//	public void deleteFile(String bucketName, String keyName) {
//		s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
//	}
//}