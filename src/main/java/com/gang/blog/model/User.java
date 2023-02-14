package com.gang.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@DynamicInsert // insert시에 null인 필드를 제외

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
		private int id; // 시퀀스, 오토인크리멘트
	    
	    @Column(nullable = false, length = 100, unique = true)
		private String username; // 아이디
	    
	    @Column(nullable = false, length = 100)
		private String password;
	    
	    @Column(nullable = false, length = 50)
		private String email;
	    
	    //@ColumnDefault("'user'")
	    @Enumerated(EnumType.STRING)
	    private RoleType role; // Enum을 쓰는게 좋다. 회원 권한 주기
	    
	    private String oauth; // 카카오 로그인 확인용
	    
	    @CreationTimestamp // 회원가입 시간 자동 입력
		private Timestamp createDate;
		
}
