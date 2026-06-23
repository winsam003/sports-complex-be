package com.example.demo.domain; 

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BannerDTO {
	
	public int bannerNum;
	public int eventcode;
	public String bannerImage;
	public MultipartFile bannerfilef;
	
	
}
