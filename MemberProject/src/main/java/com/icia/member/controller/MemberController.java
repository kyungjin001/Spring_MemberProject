package com.icia.member.controller;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;

	// 기본주소요청을 처리하는 메서드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		System.out.println("index 메서드 호출되었습니다.");
		return "index";
	}

	@RequestMapping(value = "/insertform", method = RequestMethod.GET)
	public String insertForm() {
		return "insert";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute MemberDTO members) {
		System.out.println("MemberController"+ members);

//		model.addAttribute("Member",members);
		int save = ms.insert(members);

		if (save > 0) {
			return "index";
		} else {

			return "insert";
		}

	}

	// 로그인 페이지 출력
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginForm(@ModelAttribute MemberDTO members) {
//		public String loginForm(@Requetsparm("m_id") String m_id, @Requetsparm("m_password") String m_password ) {
		String resultPage = ms.login(members);

		return resultPage;

		// if (result != null) {
//			return "index";
//		} else {
//			return "login";
//			
//		}

	}

	// 로그아웃 처리
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 세션에 저장된 데이터를 지움.
		session.invalidate();
		return "index";
	}

	// 회원목록 처리
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public String findAll(Model model) {
		List<MemberDTO> mList = ms.findAll();

		model.addAttribute("mList", mList);
		return "findAll";
	}

	// 상세조회 처리
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String findById(@RequestParam("m_number") long m_number, Model model) {
		System.out.println("findById" + m_number);

		MemberDTO members = ms.findById(m_number);

		model.addAttribute("members", members);

		return "detail";

	}

	// 삭제 처리
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("m_number") long m_number) {

		ms.delete(m_number);

		return "redirect:/findAll";
	}

	// 수정화면 요청
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateForm(@RequestParam("m_number") long m_number, Model model) {

		MemberDTO members = ms.findById(m_number);
		model.addAttribute("members", members);
		return "update";
	}

//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(@ModelAttribute MemberDTO members) {
//		System.out.println("Contoller.update()" + members);
//
//		ms.update(members);
//
//		return "redirect:/findAll";
//	}
	
	
	// 수정처리
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute MemberDTO members,Model model) {

		ms.update(members);
		
		//DB에서 데이터를 가지고 와서 detail.jsp로
		/*
		 * members = ms.findById(members.getM_number()); model.addAttribute("members",
		 * members); return "detail";
		 */

		// 컨트롤러의 detail 주소 요청
		String result = "redirect:/detail?m_number=" +  members.getM_number();
		
		model.addAttribute("members", members);

		return result;
	}
	
	//아이디 중복체크
	@RequestMapping(value="/idDuplicate", method=RequestMethod.POST)
	public @ResponseBody String idDuplicate(@RequestParam ("m_id") String m_id) {
		
		System.out.println("MemberController.idDuplicate() : "+m_id);
		String result=ms.idDuplicate(m_id);
		return result; // "ok" or "no"
	}
	 
	@RequestMapping(value = "/detailAjax", method = RequestMethod.POST)
	public @ResponseBody MemberDTO detailAjax(@RequestParam("m_number") long m_number) {
		MemberDTO members = ms.findById(m_number);
		return members; 
	}
}
