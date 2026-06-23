package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StaffDTO {
	private String token;
	private String stfid;
	private String stfpassword;
	private String stfdmp;
	private String stflevel;
	private String stfname;
	private Integer stfpnum;
	private String stfcode;
	
	private List<StaffRole> roleList = new ArrayList<>();
}
