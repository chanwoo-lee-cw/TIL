# Flask 전역 공간

## 내용

> 응용 프로그램 컨텍스트 중에 데이터를 저장할 수 있는 네임스페이스 개체
> 

flask.g 변수에는 해당 context 안에서 전역 공간에 값을 저장하고 싶을 때 사용한다.

request를 다른 함수를 호출 할 때마다 매번 전달해주는 것은 굉장히 번거로운데, flask.g에 저장해 줌으로써 해당 요청동안 쉽게 사용할 수 잇다.

## 예시

```python
from flask import Flask, jsonify, g, request
from functools import wraps

app = Flask(__name__)

# @app.before_request은 요청이 만들어지고 뷰에 전달하기 직전에 호출할 함수가 있을 때 사용
@app.before_request
def authenticate():
    g.authorization = request.headers.get("Authorization")

def save_ip_address(f):
    @wraps(f)
    def function(*args, **kwargs):
        g.url = request.url
        return f(*args, **kwargs)
    return function

@app.route('/test')
@save_ip_address
def test():
    return get_answer()

def get_answer():
		# request를 매개변수로 전달하지 않아도 flask.g 에 저장된 변수는 사용 가능
    return {"url" : g.url, "authorization" : g.authorization}

if __name__ == "__main__":
    app.run()
```

get_answer() 안에서는 request가 매개변수로 전달되지 않았지만 간단하게 url이나 authorization을 호출 가능하다.

단, 전역에 저장하는 것이므로 남발하지 말자