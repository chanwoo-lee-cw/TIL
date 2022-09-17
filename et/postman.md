# Postman

# PostMan

## 목차

## postman 사용법

## **스크립트 예시**

**특징**

- Pre-script, Test 모두 Javascript를 언어로 사용한다.
- request의 위치가 `Root Colletion/Sub Collection/request` 인 경우, 스크립트는 `Root Colletion`, `Sub Collection`, `request`의 순서대로 실행된다.

### **Test**

- 기본 테스트

```jsx
// request가 일단 제대로 전달되었는지 여부
pm.test("Successful POST request", function () {
    pm.expect(pm.response.code).to.be.oneOf([200, 201, 202]);
});

// request가 성공했는지 체크
pm.test("Status code is 200y", function () {
    pm.response.to.be.ok;
});

// request가 body가 있는지 체크
pm.test("response must be with Body", function () {
    pm.response.to.be.withBody;
});

// request가 json 형태인지 체크
pm.test("response must be json", function () {
    pm.response.to.be.json;
});

// reqest가 error인지 체크
pm.test("response should be okay to process", function () {
	  pm.response.to.not.be.error;
});
```

- Json 안에 data 가 있는지 확인

```jsx
pm.test("Body is correct", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('data');
});
```

- data status 값이 있는지 확인

```jsx
pm.test("status is have", function () {
    pm.expect(pm.response.json().data).to.have.property('status');
});
```

- 날짜 저장

```jsx
today = new Date();
today = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
```

- 다음 요청 설정하기

![스크린샷 2022-09-17 오후 11.15.30.png](https://user-images.githubusercontent.com/50443940/190863132-aef290ca-eca7-4de3-9460-787f1b60467c.png)

다음 요청하고 싶은 요청의 이름이 `다음 요청` 이고, ID가 `13774241-b53ea3d5-00bd-46a1-b089-418ef3cab5ef` 인 경우 요청 방법

```jsx
// 요청의 2가지 방법
postman.setNextRequest("다음 요청");    // 요청의 request 이름을 그대로 사용
postman.setNextRequest("b53ea3d5-00bd-46a1-b089-418ef3cab5ef");   // 요청ID에서 가장 앞을 제거하고 나머지 설정
```

만약 이 요청을 마지막으로 종료하고 싶은 경우

```jsx
postman.setNextRequest(null);
```

## 참고

https://learning.postman.com/docs/writing-scripts/script-references/postman-sandbox-api-reference/