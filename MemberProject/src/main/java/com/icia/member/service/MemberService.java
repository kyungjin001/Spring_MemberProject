package com.icia.member.service;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.member.dto.MemberDTO;
import com.icia.member.repository.MemberRepository;

@Service
public class MemberService {

	
	
	@Autowired // 어노테이션은 밑에 한줄만 적용가능, 어노테이션 밑에 두 줄 불가능
	private MemberRepository mr;
	
	
	@Autowired
	private HttpSession session;
	

	public int insert(MemberDTO members) {
				
		
		System.out.println("MemberService" + members);
		int result = mr.insert(members);
		
		
		return result;

		
	}


	
	public String login(MemberDTO members) {
		
		MemberDTO loginMember = mr.login(members);
		
		if (loginMember != null) {
			//	 로그인 성공(로그인정보(아이디를)세션에 저장)
			session.setAttribute("loginId", members.getM_id());
			session.setAttribute("loginNumber", loginMember.getM_number());

			return "main";
		} else {
			//	 로그인 실패
			return "login";
			
		}	}


	
	public List<MemberDTO> findAll() {
		List<MemberDTO> mList = mr.findAll();
		
		// tList를 출력하는 sysout
		for(MemberDTO m : mList)
			System.out.println(m);
		
		return mList;
	}


	public MemberDTO findById(long m_number) {
		MemberDTO members = mr.findById(m_number);

		return members;
	}

	
	
	public void delete(long m_number) {
		mr.delete(m_number);
		
//		return trainee;	
		}

	public MemberDTO update(MemberDTO members) {
		System.out.println("MemberService.update(): " + members);
		mr.update(members);
		return members;
	}


	public String idDuplicate(String m_id) {
		String result=mr.idDuplicate(m_id);
		if (result==null)
			return "ok"; //조회결과가 없기 때문에 해당 아이디는 사용 가능
		else
			return "no"; //조회결과가 있기 때문에 해당 아이디는 사용 불가능
	}




	
	
}
