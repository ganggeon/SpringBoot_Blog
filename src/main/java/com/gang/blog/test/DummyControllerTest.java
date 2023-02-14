package com.gang.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gang.blog.model.RoleType;
import com.gang.blog.model.User;
import com.gang.blog.repository.UserRepository;

//html파일이 아니라 data를 리턴해주는 컨트롤러 @RestController
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}") // 삭제
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return "삭제에 실패하였습니다.";
		}
		 return "삭제되었습니다 id : " + id;
	}
	
	//save함수는 id를 전달하지 않으면 insert를 해줌
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해줌
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해줌
	//email, password
	@Transactional // 함수 종료시에 자동으로 commit이 됨 -> 더티 체킹
	@PutMapping("/dummy/user/{id}") //업데이트
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{ //user객체의 id를 받아옴. 없으면 예외처리
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword()); //받아온 id에 입력한 password를 set
		user.setEmail(requestUser.getEmail());//받아온 id에 입력한 email를 set
		
		// userRepository.save(user); // set한 password와 email을 저장
		return user;
	}
	
	@GetMapping("/dummy/users") //리스트
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user") //페이징
	public List<User> pageList(@PageableDefault(size=2, sort= "id", direction = Sort.Direction.DESC ) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}

	
	// {id} 주소로 파라메터를 전달 받을 수 있다
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}") //유저 정보 불어보기
	public User detail(@PathVariable int id) {
		
		//optional로 내 user객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return하라
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() { //  Supplier는 인터페이스라 new하려면 익명클래스를 만들어야한다
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id :" +id);
			}
		});
		//요청은 웹브라우저에 하고 user객체는 자바 오브젝트이다
		//이에 웹브라우저가 이해 할 수 있는 데이터로 변환을 해 주어야 하는데 이때 사용하는 것이 json
		//스프링부트는 응답시에 messageconverter가 자동으로 작동하는데
		//만약에 자바 오브젝트를 리턴하게 되면 messageconverter가 jackson 라이브러리를 호출해서
		//user객체를 json으로 반환해서 브라우저에게 전송해준다
		return user;
		
	}
	// http://localhost:8000/blog/dummy/join (요청)
		// http의 body에 username, password, email 데이터를 가지고 (요청)
		@PostMapping("/dummy/join") // 회원가입
		public String join(User user) { // key=value (약속된 규칙)
			System.out.println("id : "+user.getId());
			System.out.println("username : "+user.getUsername());
			System.out.println("password : "+user.getPassword());
			System.out.println("email : "+user.getEmail());
			System.out.println("role : "+user.getRole());
			System.out.println("createDate : "+user.getCreateDate());
			
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return "회원가입이 완료되었습니다.";
		}
}
