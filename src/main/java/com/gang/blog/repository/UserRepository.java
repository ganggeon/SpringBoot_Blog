package com.gang.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gang.blog.model.User;

// @Repository 생략가능. 자동으로 bean등록
public interface UserRepository extends JpaRepository<User, Integer> { // user테이블의 primary key 설정, JpaRepository에는 기본적인 crud가 포함되어있다
	//JPA Naming 쿼리
	//select * from user where username = ?1 and password = ?2;
	//User findByUsernameAndPassword(String username, String password); //스프링 시큐리티 사용 안 할 때의 로그인
	//@Query(value="SELECT * FROM user WHERE username = ?1 and password = ?2", nativeQuery = true)
	//User login(String username, String password);
	
	//select * from user where username = 1?;
	Optional<User> findByUsername(String username);
}
