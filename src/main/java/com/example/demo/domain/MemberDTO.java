package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
	private String token;
	private String id;
	private String membercode;
	private String name;
	private String password;
	private String birth;
	private String phonenum;
	private String homenum;
	private String address;
	private String address1;
	private String address2;
	private String email;
	private boolean snsagr;
	private boolean emailagr;
	private String carnum;
	private Date parkuse;
	
	private List<MemberRole> roleList = new ArrayList<>();

	
}
