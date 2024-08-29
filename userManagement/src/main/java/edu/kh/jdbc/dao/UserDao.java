package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.dto.User;

public interface UserDao {
	/** 사용자 등록
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	int insertUser(Connection conn, User user) throws Exception;
	
	/** 아이디 중복 검사
	 * @param conn
	 * @param userId
	 * @return result
	 * @throws Exception
	 */
	int idCheckr(Connection conn, String userId) throws Exception;
	
	/**
	 * @param conn
	 * @param userId
	 * @param userPw
	 * @return loginUser
	 * @throws Exception
	 */
	User login(Connection conn, String userId, String userPw) throws Exception;

	List<User> selectAll(Connection conn) throws Exception;

}
