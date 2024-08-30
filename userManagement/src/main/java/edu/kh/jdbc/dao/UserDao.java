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

	/** 사용자 목록 조회
	 * @param conn
	 * @return userList
	 * @throws Exception
	 */
	List<User> selectAll(Connection conn) throws Exception;

	/** 검색어가 아이디에 포함된 사용자 조회
	 * @param conn
	 * @param searchId
	 * @return userList
	 * @throws Exception
	 */
	List<User> search(Connection conn, String searchId) throws Exception;
	
	/**
	 * @param conn
	 * @param input
	 * @return userNo
	 * @throws Exception
	 */
	User selectUser(Connection conn, int input) throws Exception;

	/** 삭제하기
	 * @param conn
	 * @param userNo
	 * @return return
	 * @throws Exception
	 */
	int deleteUser(Connection conn, int userNo) throws Exception;
	
	/** 수정하기
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	int updateUser(Connection conn, User user) throws Exception;
	

}
