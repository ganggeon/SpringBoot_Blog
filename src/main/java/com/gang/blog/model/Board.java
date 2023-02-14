package com.gang.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, 오토인크리멘트

    @Column(nullable = false, length = 100)
	private String title; 
    
    @Lob // 대용량 데이터
    private String content; 
    
    private int count; // 조회수
    
    @ManyToOne // many = board, one = user 하나의 유저가 여러개의 게시글을 쓸 수 있다
    @JoinColumn(name="userId") // 오브젝트가 userId라는 이름으로 만들어짐 - 외래키
    private User user; // db는 오브젝트를 저장할 수 없다. fk, 자바는 오브젝트를 저장할 수 있다.
    
    @OneToMany(mappedBy="board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아닌다 = 외래키가 아니다. db에 컬럼을 만들지 말라. 단지 값만 얻을뿐이다
    @JsonIgnoreProperties({"board"}) // 무한 참조 방지
    @OrderBy("id desc")
    private List<Reply> replys;
    
    @CreationTimestamp 
	private Timestamp createDate;
}
