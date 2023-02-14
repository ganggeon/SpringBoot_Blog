package com.gang.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gang.blog.model.RoleType;
import com.gang.blog.model.User;
import com.gang.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username){
		
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User(); //회원을 찾았는데 없으면 빈 객체를 리턴한다
		});
		return user;
	}
	
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 비밀번호 원본
		String encPassword = encoder.encode(rawPassword); // 비밀번호 암호화(해쉬화)
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) {
		//수정시에는 영속성 컨텍스트 user 오브젝트를 영속화시키고, 영속화된 user오브젝트를 수정
		//select을 해서 user오브젝트를 db로부터 가져오는 이유는 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 db에 update한다
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
		// validate 체크 = 카카오 로그인시 postman같은 걸로 회원 정보 수정하는 것 막기 - oauth에 값이 없으면 수정 가능
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}

		
	
		// 회원수정 함수 종료시 = 서비스 종료 시 = 트랜잭션 종료 = 커밋이 자동으로 된다
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려준다.
	}
	
	/*
	 * * 스프링 시큐리티 안 쓸 때의 전통적인 로그인
	@Transactional(readOnly = true) // 트랜젝션 시작부터 종료까지 정합성 유지
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	*/
}
