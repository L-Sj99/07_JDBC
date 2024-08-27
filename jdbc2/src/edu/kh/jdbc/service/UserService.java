package edu.kh.jdbc.service;

import java.sql.Connection;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.UserDao;
import edu.kh.jdbc.dto.User;

/* Service : 비즈니스 로직 처리
 * - DB에 CRUD 후 결과 반환 받기
 *  + DML 성공 여부에 따른 트랜잭션 제어 처리(commit / rollback)
 *  --> commit / rollback에는 Connection 객체가 필요하기 때문에 Connection 객체를
 *  	Service에서 생성 후 Dao에 전달하는 형식의 코드를 작성하게 됨
 */

public class UserService {
	// 필드
	private UserDao dao = new UserDao();
	
	// 메서드
	/** 전달 받은 아이디와 일치하는 User 정보 반환 
	 * @param input 입력된 아이디
	 * @return 아이디가 일치하는 회원 정보, 없으면 null
	 */
	public User selectId(String input) {
		// 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// Dao 메서드 호출 후 결과 반환 받기
		User user = dao.selectId(conn, input);
		
		// 다 쓴 커넥션 닫기
		JDBCTemplate.close(conn);
		return user; // DB 조회 결과 반환
	}
}
