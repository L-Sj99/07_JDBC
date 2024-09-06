package edu.kh.todolist.dao;
import static edu.kh.todolist.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import edu.kh.todolist.dto.Todo;

public interface TodoListDao {

	/** 할 일 전체 조회
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	List<Todo> todoListFullView(Connection conn) throws Exception;

	/** 완료된 할 일 개수 조회
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	int getCompleteCount(Connection conn) throws Exception;

	/** 할 일 추가
	 * @param conn
	 * @param title
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	int todoAdd(Connection conn, String title, String detail) throws Exception;

	/** 할 일 상세 조회
	 * @param conn
	 * @param todoNo
	 * @return
	 * @throws Exception
	 */
	Todo todoDetailView(Connection conn, int todoNo) throws Exception;

	/**  완료 여부 변경
	 * @param conn
	 * @param todoNo
	 * @return
	 * @throws Exception
	 */
	int todoComplete(Connection conn, int todoNo) throws Exception;

	/** 할 일 수정
	 * @param conn
	 * @param todoNo
	 * @param title
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	int todoUpdate(Connection conn, int todoNo, String title, String detail) throws Exception;

	/** 할 일 삭제
	 * @param conn
	 * @param todoNo
	 * @return
	 * @throws Exception
	 */
	int todoDelete(Connection conn, int todoNo) throws Exception;
}