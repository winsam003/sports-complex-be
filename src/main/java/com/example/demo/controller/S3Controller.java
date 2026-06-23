//package com.example.demo.controller;

//@RequiredArgsConstructor
//@RestController
//public class S3Controller {
//	private final S3Uploader s3Uploader;
//
//	@PostMapping("/{userId}/image")
//    public ResponseEntity<UserResponseDto> updateUserImage(@RequestParam("images") MultipartFile multipartFile) {
//        try {
//            s3Uploader.uploadFiles(multipartFile, "static");
//        } catch (Exception e) { return new ResponseEntity(HttpStatus.BAD_REQUEST); }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }