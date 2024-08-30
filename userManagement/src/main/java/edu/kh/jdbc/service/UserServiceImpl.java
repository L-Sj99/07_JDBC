package edu.kh.jdbc.service;

// 지정된 클래스의 Static 메서드를 모두 언어와 사용
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.UserDao;
import edu.kh.jdbc.dao.UserDaoImpl;
import edu.kh.jdbc.dto.User;

public class UserServiceImpl implements UserService{
	// 필드
	private UserDao dao = new UserDaoImpl();
	
	@Override
	public int insertUser(User user) throws Exception {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환
		int result = dao.insertUser(conn, user);
		// insert하면 결과가 int로 반환
		
		// 3. DML 수행 -> 트랜잭션 처리
		if(result > 0) commit(conn);
		else rollback(conn);
		
		// 4. 사용 완료된 Connection 반환
		close(conn);
		
		return result;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		Connection conn = getConnection();
		int result = dao.idCheckr(conn, userId);
		close(conn);
		return result;
	}

	@Override
	public User login(String userId, String userPw) throws Exception {
		Connection conn = getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받기
		User loginUser = dao.login(conn, userId, userPw);
		close(conn);
		return loginUser;
	}
	@Override
	public List<User> selectAll() throws Exception {
			Connection conn = getConnection();
			List<User> userList = dao.selectAll(conn);
			close(conn);
			return userList;
	}

	@Override
	public List<User> search(String searchId) throws Exception {
		
		// 커넥션 생성
		Connection conn = getConnection();
		
		// 데이터 가공(없으면 패스)
		searchId = '%' + searchId + '%'; // %검색어% 형태로 가공
		
		// DAO 메서드 호출 후 결과 반환
		List<User> userList = dao.search(conn, searchId);
		close(conn);
		return userList;
	}
	@Override
	public User selectUser(int input) throws Exception{
		Connection conn = getConnection();
		User userNo = dao.selectUser(conn, input);
		close(conn);
		return userNo;
	}

	@Override
	public int deleteUser(int userNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.deleteUser(conn, userNo);
		if(result > 0) commit(conn);
		else rollback(conn);		
		close(conn);
		return result;
	}

	@Override
	public int updateUser(User user) throws Exception {
		Connection conn = getConnection();
		int result = dao.updateUser(conn, user);
		if(result > 0) commit(conn);
		else rollback(conn);		
		close(conn);
		return result;
	}
}
