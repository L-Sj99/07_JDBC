package edu.kh.todolist.service;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import edu.kh.todolist.dto.Todo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import edu.kh.todolist.dto.Todo;

public interface TodoListService {
	
	/**  할 일 목록 반환 서비스
	 * @return todoList + 완료 개수
	 */ 
	public abstract Map<String, Object> todoListFullView() throws Exception;

	/** 할 일 추가
	 * @param title
	 * @param detail
	 * @return result
	 * @throws Exception
	 */
	public abstract int todoAdd(String title, String detail) throws Exception;

	/** 할 일 상세 조회
	 * @param todoNo
	 * @return todo
	 * @throws Exception
	 */
	public abstract Todo todoDetailView(int todoNo) throws Exception;
 
	/** 완료 여부 변경
	 * @param todoNo
	 * @return complete
	 * @throws Exception
	 */
	public abstract int todoComplete(int todoNo) throws Exception;

	
	/** 할 일 수정 
	 * @param todoNo
	 * @param title
	 * @param detail
	 * @return result
	 * @throws Exception
	 */
	public abstract int todoUpdate(int todoNo, String title, String detail)  throws Exception;

	
	/** 할 일 삭제
	 * @param todoNo
	 * @return result
	 * @throws Exception
	 */
	public abstract int todoDelete(int todoNo) throws Exception; 

	

}