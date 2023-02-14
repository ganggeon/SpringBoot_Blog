package com.gang.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gang.blog.config.auth.PrincipalDetail;
import com.gang.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//컨트롤러에서 세션을 어떻게 찾는가?
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size=5, sort= "id", direction = Sort.Direction.DESC ) Pageable pageable) { 
		
		model.addAttribute("boards", boardService.글목록(pageable));
		
	    int startPage = ((pageable.getPageNumber()-1) / 10) * 10 + 1;
	    pageable.getPageSize();
	    int endPage = startPage + 10 - 1  > pageable.getPageSize() ? pageable.getPageSize() : startPage + 10 - 1;
	    model.addAttribute("startPageNo", startPage);
	    model.addAttribute("endPageNo", endPage);
		
		return "index"; // 글목록함수에서 받은 값을 boards에 담아 index로 전송, model은 request 정보
	}
	
	@GetMapping("/board/{id}")
	public String findByid(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}
	
	//USER권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
		
	}
}
