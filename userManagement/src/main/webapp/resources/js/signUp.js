// 아이디가 userId, check인 요소를 얻어와 변수에 저장
const userId = document.querySelector("#userId");
const check = document.querySelector("#check");

// #userId에 입력(input)이 되었을 때
userId.addEventListener("input", e => {
  
  // 비동기로 아이디 중복 여부를 얻어오는 ajax코드 작성 예정
  // - ajax : 서버와 비동기 통신을 하기 위한 JS 기술
  // - fetch() API : JS에서 제공하는 ajax를 쉽게 쓰는 코드

  fetch("/signUp/idCheck?userId=" + e.target.value)
  .then(resp => resp.text()) // 응답을 text로 변환
  .then(result => {
    // result : 첫 번쨰 then에서 resp.text()를 통해 변환된 값
    // console.log(result);
    if(result==0) { // 중복 X -> 사용 가능
      check.classList.add("green"); // green 클래스 추가
      check.classList.remove("red"); // red 클래스 제거
      check.innerText = "사용 가능한 아이디 입니다.";
    } else {
      check.classList.add("red"); // green 클래스 추가
      check.classList.remove("green"); // red 클래스 제거
      check.innerText = "이미 사용 중인 아이디 입니다.";
    }
  });

});