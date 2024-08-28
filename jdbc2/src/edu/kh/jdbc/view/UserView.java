package edu.kh.jdbc.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;

public class UserView {
	// 필드
	private UserService service = new UserService();
	private Scanner sc = new Scanner(System.in);
	
	/* JDBCTemplate 사용 테스트 */
	public void test() {
		// 입력된 ID와 일치하는 USER 정보 조회
		System.out.print("ID 입력 : ");
		String input = sc.nextLine();
		
		// 서비스 호출 후 결과 반환 받기
		User user = service.selectId(input);
		
		// 결과 출력
		System.out.println(user);		
	}
	/* User 관리 프로그램 메인 메뉴 */
	public void mainMenu() {
		int input = 0;
		do {
			try {
				System.out.println("\n == User 관리 프로그램 == \n");
				System.out.println("1. User 등록(INSERT)");
				System.out.println("2. User 전체 조회(SELECT)");
				System.out.println("3. User 중 이름에 검색어가 포함된 회원 조회 (SELECT)");
				System.out.println("4. USER_NO를 입력 받아 일치하는 User 조회 (SELECT)");
				System.out.println("5. USER_NO를 입력 받아 일치하는 User 삭제 (DELETE)");
				System.out.println("6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE)");
				System.out.println("7. User 등록(아이디 중복 검사)");
				System.out.println("8. 여러 User 등록하기");
				System.out.println("0. 프로그램 종료");
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 버퍼에 남은 개행문자 제거
				
				switch(input) {
				case 1 : insertUser(); break;
				case 2 : selectAll(); break;
				case 3 : selectName(); break;
				case 4 : selectUser(); break;
				case 5 : deleteUser(); break;
				case 6 : updateName(); break;
				case 7 : insertUser2(); break;
				case 8 : multiInsertUser(); break;
				case 0 : System.out.println("\n [프로그램 종료.] \n"); break;
				default : System.out.println("\n [메뉴 번호만 입력해 주세요.] \n");
				}
				System.out.println("\n===============================================================\n");
				
			} catch(InputMismatchException e) {
				// Scanner를 이용한 입력 시 자료형이 잘못된 경우
				System.out.println("\n 잘못 입력 하셨습니다. \n");
				input = -1; // 잘못 입력해서 while문 멈추는걸 방지
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자 제거
				
			} catch (Exception e) { 
				// 발생되는 예외를 모두 해당 catch 구문으로 모아서 처리
								
				e.printStackTrace();
			}
		} while(input != 0); 
	} // mainMenu() 종료
		
	/* 1. User 등록
	 * @throws Exception
	 */
	private void insertUser() throws Exception {
		System.out.println("\n 1. User 등록 \n");
		System.out.print("ID : ");
		String userId = sc.next();
		System.out.print("PW : ");
		String userPw = sc.next();
		System.out.print("NAME : ");
		String userName = sc.next();
		
		// 입력 받은 값 3개를 한 번에 묶어서 전달할 수 있도록 User DTO 객체를 생성한 후 필드에 값을 세팅
		User user = new User();
		
		// lombok을 이용해 자동 생성된 setter 이용
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		// 서비스 호출(INSERT) 후 결과(삽입된 행의 개수, int) 반환 받기
		int result = service.insertUser(user);
		
		// 반환된 결과에 따라 출력할 내용 선택
		if(result > 0) {
			System.out.println("\n" + userId + "사용자가 등록 되었습니다. \n");
		} else {
			System.out.println("\n 등록 실패. \n"); }
	}
	
	/* 2. User 전체 조회(SELECT) */
	private void selectAll() throws Exception {
		System.out.println("\n 2. User 전체 조회(SELECT) \n");
		
		// 서비스 메서드(SELECT) 호출 후 결과(List<User>) 반환 받기
		List<User> userList = service.selectAll();
		
		// 조회 결과가 없을 경우
		if(userList.isEmpty()) {
			System.out.println("\n 조회 결과가 없습니다. \n");
			return;
		}
		// 향상된 for문
		for(User user : userList) {
			System.out.println(user); // 자동으로 user.toString() 호출
		}
	}
	/* 3.  User 중 이름에 검색어가 포함된 회원 조회 (SELECT) */
	private void selectName() throws Exception{
		System.out.println("\n 3. User 중 이름에 검색어가 포함된 회원 조회(SELECT) \n");
		
		System.out.print("검색어 입력 : ");
		String keyword = sc.nextLine();
		
		// 서비스(SELECT) 호출 후 결과(List<User>) 반환 받기
		List<User> searchList = service.selectName(keyword);
		
		if(searchList.isEmpty()) {
			System.out.println("\n 검색 결과 없음. \n");
			return;
		}
		for(User user : searchList) {
			System.out.println(user);
		}
	}
	/* 4.  USER_NO를 입력 받아 일치하는 User 조회 (SELECT) */
	private void selectUser() throws Exception {
		System.out.println("\n 4. USER_NO를 입력 받아 일치하는 User 조회 (SELECT) \n");
		System.out.print("사용자 번호 입력 : ");
		int input = sc.nextInt();
		
		// 사용자 번호는 == PK == 중복이 있을 수 없다 == 일치하는 사용자가 있다면 1행만 조회된다
		// -> 1행의 조회 결과를 담기 위해 사용하는 객체 == User DTO
		User user = service.selectUser(input);
		
		// 조회 결과가 없으면 null, 있으면 null 아님
		if(user == null) {
			System.out.println("\n USER_NO가 일치하는 회원이 없습니다. \n");
			return;
		} System.out.println(user);
	}
	/* 5. USER_NO를 입력 받아 일치하는 User 삭제 (DELETE) */
	private void deleteUser() throws Exception {
		System.out.println("\n 5. USER_NO를 입력 받아 일치하는 User 삭제 (DELETE) \n");
		System.out.print("삭제할 사용자 번호 입력 : ");
		int input = sc.nextInt();
		
		User user = service.selectUser(input);
		
		// 서비스(DELETE) 호출 후 결과(삭제된 행의 개수, int) 반환 받기
		int result = service.deleteUser(input);
		
		if(result > 0) System.out.println("삭제 성공.");
		else System.out.println("사용자 번호가 일치하는 User가 존재하지 않습니다.");
	}
	/* 6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE) */
	private void updateName() throws Exception {
		System.out.println("\n 6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE) \n");
		System.out.print("ID : ");
		String userId = sc.nextLine();
		System.out.print("PW : ");
		String userPw = sc.nextLine();
		
		// 입력 받은 ID, PW가 일치하는 회원 조회(SELECT) --> USER_NO 조회
		int userNo = service.selectUserNo(userId, userPw);
		if(userNo == 0) { // 조회결과 없음
			System.out.println("아이디 또는 비밀번호가 불일치합니다.");
			return;
		}
		// 조회된 사용자 번호가 있을 경우
		System.out.print("수정할 이름 입력 : ");
		String userName = sc.nextLine();
		
		// 이름 수정 서비스(UPDATE) 호출 후 결과(수정된 행의 개수, int) 반환 받기
		int result = service.updateName(userName, userNo);
		if(result > 0) System.out.println("수정 성공.");
		else System.out.println("수정 실패.");
	}
	/* 7. User 등록(아이디 중복 검사) */
	private void insertUser2() throws Exception {
		System.out.println("\n 7. User 등록(아이디 중복 검사) \n");
		String userId = null; // 입력된 아이디를 저장할 변수
		while(true) {
			System.out.print("ID : ");
			userId = sc.nextLine();
			
			// 입력 받은 userId가 중복인지 검사하는 서비스(SELECT) 호출 후 결과(int, 중복 == 1, 아니면 == 0) 반환받기
			int count = service.idCheck(userId);
			if(count == 0) { // 중복이 아닌 경우
				System.out.println("사용 가능한 아이디 입니다.");
				break;
			}
			System.out.println("이미 사용 중인 아이디 입니다. 다시 입력 해주세요.");
		}
		// 아이디가 중복이 아닌 겨우 while 종료 후 pw, name 입력 받기
		System.out.print("PW : ");
		String userPw = sc.nextLine();
		System.out.print("NAME : ");
		String userName = sc.nextLine();
		
		// 입력 받은 ID, PW, NAME을 User 객체로 묶어서 서비스 호출
		User user = new User();
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		// 1번에 만든 service 메서드 재활용
		int result = service.insertUser(user);
		
		// 반환된 결과에 따라 출력할 내용 선택
		if(result > 0) {
			System.out.println("\n" + userId + "사용자가 등록 되었습니다. \n");
		} else {
			System.out.println("\n 등록 실패. \n"); }
	}
	/* 8. 여러 User 등록하기 */
	private void multiInsertUser() throws Exception {
		// 등록할 User 수 : 2
		
		// 1번쨰 userId : user100 -> 사용 가능한 아이디 입니다.
		// 1번쨰 userPw : pass100
		// 1번쨰 userName : 유저백
		// --------------------------------------------------
		// 1번쨰 userId : user200 -> 사용 가능한 아이디 입니다.
		// 1번쨰 userPw : pass200
		// 1번쨰 userName : 유이백
		// -- 전체 삽입 성공 / 삽입 실패
		System.out.println("\n 8. 여러 User 등록하기 \n");
		System.out.print("등록 할 User 수 : ");
		int input = sc.nextInt();
		sc.nextLine(); // 버퍼 개행문자 제거
		
		// 입력 받은 회원 정보를 저장할 List 객체 생성
		List<User> userList = new ArrayList<User>();
		
		for(int i = 0 ; i < input ; i++) {
			String userId = null; // 입력된 아이디를 저장할 변수
			while(true) {
				System.out.print((i+1) + "번째 userId : ");
				userId = sc.nextLine();
				
				// 입력 받은 userId가 중복인지 검사하는 서비스(SELECT) 호출 후 결과(int, 중복 == 1, 아니면 == 0) 반환받기
				int count = service.idCheck(userId);
				
				if(count == 0) { // 중복이 아닌 경우
					System.out.println("사용 가능한 아이디 입니다.");
					break;
				}
				System.out.println("이미 사용 중인 아이디 입니다. 다시 입력 해주세요.");
			}
			System.out.print((i+1) + "번째 userPw : ");
			String userPw = sc.nextLine();
			System.out.print((i+1) + "번째 userName : ");
			String userName = sc.nextLine();
			
			User user = new User();
			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setUserName(userName);
			
			// userList에 user 추가하기
			userList.add(user);
		} // for문 종료
		
		// 입력 받은 모든 User를 insert하는 서비스 호출
		int result = service.multiInsertUser(userList);
		
		// 전체 삽입 성공 시
		if(result == userList.size()) {
			System.out.println("전체 삽입 성공");
		} else {
			System.out.println("삽입 실패");
		}
	}
}