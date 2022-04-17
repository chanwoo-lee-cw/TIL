# for... else 문

보통 `else`는 `if`문과 사용되기 마련이지만, 파이썬에는 `for`과 함께 `else`를 쓸 수 있다.



파이썬에서 쓰이는 `for... else` 문은 `for` 문이 중간에 if 문으로 끊기지 않았을 때 작동되는 방식이다.



## 예시

```python
if __name__ == "__main__":
    for i in range(11):
        if i >= 10:
            print(i)
            break
    else:
        print("10 이상으로 설정해주세요.")
        
 # 출력
10
```

```python
if __name__ == "__main__":
    for i in range(5):
        if i >= 10:
            print(i)
            break
    else:
        print("10 이상으로 설정해주세요.")
        
# 출력
10 이상으로 설정해주세요.
```



단, `continue` 는 정상적으로 실행되었다고 간주한다.

```python
if __name__ == "__main__":
    for i in range(11):
        if i >= 10:
            print(i)
            continue
    else:
        print("10 이상으로 설정해주세요.")
        
# 출력
10
10 이상으로 설정해주세요.
```

