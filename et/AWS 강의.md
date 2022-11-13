# AWS 강의

작성일시: 2022년 11월 13일 오후 6:17
최종 편집일시: 2022년 11월 13일 오후 7:27

# 목차

# IPv4 Address

![img](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F8ff05c38-bce8-48c3-bac8-f5263a4954cf%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2022-11-13_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_6.31.11.png?table=block&id=0c0fc6d3-8551-42aa-b3b7-9fb360e31cf7&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)

- IP 주소란 네트워크 통신을 하기 위해 각 장비에 부여하는 고유한 주소
- 라우터에 라우팅을 하기 위해서는 IP 주소가 필수적으로 필요
- IP 주소 체계는 IPv4와 IPv6가 있다.
- IPv4 주소는 32비트 길이의 식별자로 이루어지며, 12 자리의 10진수 번호로 이루어짐
- 0.0.0.0 에서 255.255.255.255 까지의 숫자 조합으로 이루어지며 각 부분은 0~255 까지의 수로 표현
- IP 주소는 0또는 1이 나열된 32비트로 구성
- IP 주소는 8 자리 이진수 묶음이 4개가 모여 32 비트로 구성

## IPv4 Class

- IP 주소의 효율적 사용을 위해 5개의 클래스로 나눔
- 인터넷 주소 관리 기관에서 부여한 네트워크 주소와 개별 호스트 주소를 식별하기 위해 네트워크 관리자가 부여 가능한 호스트 주소로 구성
- IP 주소 체계에선 호스트 주소끼리는 Network Address가 없어도 서로 통신 가능
- A Class
  
    ![img](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0316a4be-4abb-4464-8504-d87972a710c3%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2022-11-13_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_6.43.32.png?table=block&id=0eba74d9-5893-4ed6-ba58-38e94523e007&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)
    
- B Class
  
    ![img](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fbca96ce6-d154-43fa-8d3e-fc7dd5846ba5%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2022-11-13_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_6.46.12.png?table=block&id=fac97dff-8c47-48e4-a545-85ecdf0d5b98&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)
    
- C Class
  
    ![img](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fbaff8828-239d-4a6c-8bf8-638b48f741d9%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2022-11-13_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_6.48.17.png?table=block&id=8941f7c7-377c-4fca-96c5-6fcc50da008f&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)
    
- D Class
  
    ![img](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F1540d2e4-2ecf-4bd1-b12a-264a5c805251%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2022-11-13_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_6.49.53.png?table=block&id=b2b47d65-0b3c-44eb-b0dc-fd883a7434b8&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)
    
- E Class
  
    ![img](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F3fddb799-42e8-45b3-a168-a2aa42c6a50f%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2022-11-13_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_6.50.27.png?table=block&id=4ddf0e16-477e-425e-bbf5-27d973b65e55&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)
    

## 네트워크 호스트

- 같은 네트워크의 호스트끼리만 통신이 가능
- 같은 네트워크가 아닌 경우에는 라우팅을 통해 디폴트 게이트 웨이로 IP 패킷 전송
    - 예를 들면 Class의 A 호스트(192.168.1.11)가 B 호스트(192.168.1.10) 안에 있는 네트워크에 데이터를 전송한다.

## Subnetting - IP Subnetting

- 클래스 단위의 IP 주소 할당의 주소 공간 낭비를 막기 위해 IP 주소를 보다 작은 네트워크로 분할하는 작업
- IP 주소를 서브네팅 할 댸 CIDR(Class Inter-Domain Routing) 이란 표현 방식 사용
- 기본적인 클래스 A, B, C의 경우 /8, /16, 24 비트의 네트워크를 사용하면 CIDR를 사용하면 보다 세분화 해서 네트워크와 호스트 범위 지정 가능

### 예시

- Class A의 127.0.0.0 IP 대역 대 사용
- CIDR 표현 방식 : 127.0.0.0/8
- IP 주소 범위 127.0.0.1 ~ 127.255.255.254
    - 즉, 사이더에 걸리는 범위(앞의 8자리)만 Network Address로 사용
- Subnet ID : 10.10.0.0
- Broadcast Address : 10.10.255.255

## Private IP Address vs. Public IP Address

- Public IP는 인터넷에서 사용하는 IP 주소로 중복되지 않고 고유하게 할당됨.
- Private IP는 기업, 개인의 내부망에서 사용하는 IP 주소로 인터넷으로 라우팅이 안 됨.