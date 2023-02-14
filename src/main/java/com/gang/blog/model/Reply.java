package com.gang.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, 오토인크리멘트
	
	@Column(nullable = false, length = 200)
	private String content; 
	
	@ManyToOne // 여러개의 답변은 하나의 게시글에 존재 할 수 있다
	@JoinColumn(name = "boardId") // boardId라는 이름으로 board 엔티티를 조인 - 외래키 
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "userId") // 위와 같음
	private User user;
	
	@CreationTimestamp 
	private Timestamp createDate;
}
