package com.icia.member.repository;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.member.dto.MemberDTO;

@Repository
public class MemberRepository {

	@Autowired
	private SqlSessionTemplate sql;

	public int insert(MemberDTO members) {
		System.out.println("MemberRepository.insert() :" + members);
		return sql.insert("Member.insertMember", members);	
	}


	
	
	public MemberDTO login(MemberDTO members) {
//		System.out.println(check);
		return sql.selectOne("Member.login", members);	
		
	}

	public List<MemberDTO> findAll() {
		return sql.selectList("Member.findAll");	
	}

	public MemberDTO findById(long m_number) {
		return sql.selectOne("Member.findById", m_number);	
	}

	
	
	public void delete(long m_number) {
		sql.delete("Member.delete", m_number);	
	}

	public void update(MemberDTO members) {
		System.out.println("MemberRepository.update(): " + members);
		sql.update("Member.update", members);			
	}

	public String idDuplicate(String m_id) {
		return sql.selectOne("Member.idDuplicate",m_id);
	}

	

	

}
