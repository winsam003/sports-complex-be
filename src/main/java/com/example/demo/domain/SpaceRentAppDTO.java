package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceRentAppDTO {
	private Integer sprnum;
	private String spaceCode;
	private String sprDate;
	private String id;
	private String appPhoneNum;
	private Integer numOfPeople;
	private String sprstate;
	private String payment;
	private String id2;
	private String sprstate2;
	private String payment2;
	private String appPhoneNum2;
	private Integer numOfPeople2;
	private String appdate2;
	private String appdate;
	
    // 문자열을 인자로 받는 생성자 추가
    public SpaceRentAppDTO(String id) {
        this.id = id;
    }
}
