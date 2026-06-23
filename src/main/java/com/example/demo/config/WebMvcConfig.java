package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//** WebMvcConfigurer
//=> 스프링의 자동설정에 원하는 설정을 추가 설정할수있는 메서드들을 제공하는 인터페이스. 
//=> 스프링부트 컨트롤러 매핑메서드에서는 "/" 무시됨 -> addViewControllers 메서드로 해결  

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/banner/bannerinsert")
			.addResourceLocations("file:///home/ubuntu/app/resources/banner/");
		registry
			.addResourceHandler("/banner/bannerimages")
			.addResourceLocations("file:///home/ubuntu/app/resources/banner/");				// banner
        registry
        	.addResourceHandler("/notice/noticeSubmit")
        	.addResourceLocations("file:///home/ubuntu/app/resources/notice/");
        registry
        	.addResourceHandler("/notice/downloadFile")
        	.addResourceLocations("file:///home/ubuntu/app/resources/notice/");
        registry
    		.addResourceHandler("/notice/noticeModify")
    		.addResourceLocations("file:///home/ubuntu/app/resources/notice/");				// notice
        registry
        	.addResourceHandler("/event/eventinsert")
        	.addResourceLocations("file:///home/ubuntu/app/resources/event/");
        registry
        	.addResourceHandler("/event/eventimages")
        	.addResourceLocations("file:///home/ubuntu/app/resources/event/");
        registry
        	.addResourceHandler("/event/eventupdate")
        	.addResourceLocations("file:///home/ubuntu/app/resources/event/");				// event
		registry
			.addResourceHandler("/qna/qnaInsert")
			.addResourceLocations("file:///home/ubuntu/app/resources/qna/");
		registry
			.addResourceHandler("/qna/downloadFile")
			.addResourceLocations("file:///home/ubuntu/app/resources/qna/");				// qna
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/home");
	} // addViewControllers

	private final long MAX_AGE_SECS = 3600; // 단위:초

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 모든 경로에 대해
		registry.addMapping("/**")
		// Origin이 http:localhost:3000에 대해
//				.allowedOrigins("http://localhost:3000")
				// 탄력적 ip주소 할당해주기	
				.allowedOrigins("http://localhost:3000",
						"http://fitnest.s3-website.ap-northeast-2.amazonaws.com/")
//						"http://dbrghl-bucket.s3-website.ap-northeast-2.amazonaws.com/")
				// GET, POST, PUT, PATCH, DELETE, OPTIONS 메서드를 허용한다.
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedHeaders("*")
				.allowCredentials(true).maxAge(MAX_AGE_SECS);
	}
} // class
