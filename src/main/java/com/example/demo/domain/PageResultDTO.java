package com.example.demo.domain;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

//** PageList 결과 처리 DTO
//=> JPA를 사용하는 Repository에서는 Page 처리결과를 
//  Page<Entity> Type으로 return하기 때문에 서비스계층에서 
// 이를 처리할 수 있도록 하는 DTO클래스가 필요함.
// - Page<Entity> 객체들을 DTO 객체로 변환해서 List에 담아줌
// - 화면출력을 위한 페이지 정보들 구성

@Data
public class PageResultDTO<DTO, EN> {
	// DTO List
	private List<DTO> dtoList;

	// 총 PageNo
	private int totalPage;

	// 요청받은 출력페이지, 페이지 당 출력 row 갯수
	private int page;
	private int size;

	// 이전, 다음
	private int start, end;
	private boolean prev, next;

	// 페이지 번호를 목록(list) 형태로 만듦
	private List<Integer> pageList;

	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());

		totalPage = result.getTotalPages();

		makePageList(result.getPageable());
	} // PageResultDTO

	private void makePageList(Pageable pageable) {
		// 화면에 출력할 번호는 1부터 시작해서 +1
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();

		// temp end page
		int tempEnd = (int) (Math.ceil(page / 5.0)) * 5;

		start = tempEnd - 4;

		prev = start > 1;

		end = totalPage > tempEnd ? tempEnd : totalPage;

		next = totalPage > tempEnd;

		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	} // makePageList

}
