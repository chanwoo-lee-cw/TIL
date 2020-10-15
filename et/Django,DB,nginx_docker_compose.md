## Django

1. Django App을 생성

   - ```bash
     django-admin.py startproject [프로젝트이름]
     ```

     으로 프로젝트 생성

   - 그 후에 프로젝트 디렉토리에 들어가, 해당 프로젝트의 루트 디렉토리에서

     ```
     python manage.py startapp [앱 이름]
     ```

     으로 앱도 생성. 예를 들면 `python manage.py startapp article` 이라고 생성

   - 그리고 프로젝트 디렉토리 밑에 settings.py에 입력한다.

     ```python
     # settings.py
     INSTALLED_APPS = [
     	...
     	# 이 밑에 App 이름 추가.
         'article',
     ]
     ```

   - 그 이후에 데이터 베이스의 정보를 저장해 두기 위한 'yml'을 읽어오기 위해 pyyaml을 설치한다.

     ```bash
     pip install pyyaml
     ```

   - 그리고 Postgre DB를 읽어오기 위한 psycopg2를 설치한다.

     ```
     pip install psycopg2
     ```

   - nginx와 연동해서 단일 쓰레드로 작동하는 django를 멀티 쓰레드로 사용하기 위한 gunicon 도 설치

     ```
     pip install gunicorn
     ```

   - 그리고 설치된 pip list를 저장한다.

     ```
     pip freeze > requirements.txt
     ```

   - 그 의외의 설정은 [장고 readme](https://github.com/Alphanewbie/Django/blob/master/Readme.md)를 보고 따라 설정해준다.

   - article app안에 models.py안에 간단한 모델 작성

     ```python
     # models.py
     from django.db import models
     
     class Article(models.Model):
         title = models.CharField(max_length=20)
         content = models.TextField()
         created_at = models.DateTimeField(auto_now_add=True)
         updated_at = models.DateTimeField(auto_now=True)
     ```

   - 그리고 이제 Dockerfile을 작성한다.

     ```dockerfile
     # Dockerfile
     FROM python:3.8
     
     ENV PYTHONUNBUFFERED 1
     # 기본적인 리눅스 apt-get 설치와 vim 에디터, 그리고 나머지는 설치 안한다는 명령어
     RUN apt-get update \
         && apt-get install -y vim \
         && apt-get install -y --no-install-recommends
     # docker run 했을때 work 디렉토리를 /code로 한다는 명령어
     RUN mkdir /code
     WORKDIR /code 
     # 지금까지 설치된 pip list가 저장된 requirements.txt을 복사한다는 명령어와 그 pip 들을 전부 성치
     ADD requirements.txt /code/ 
     RUN pip install --upgrade pip \
         && pip install -r requirements.txt 
     ADD . /code/ 
     # 그리고 8000번 포트를 연 다음에 8000번 포트로 django를 run 한다는 명령어이다.
     # 단, 밑에는 nginx와 gunicorn으로 빌드할 때는 작성 하지 않아도 된다.
     EXPOSE 8000
     CMD ["python3", "manage.py", "runserver", "0.0.0.0:8000"]
     ```

## DB

- 일단 간단한 django 앱은 작성 되었으므로 DB부터 작성한다.
  - 다만 DB는 docker을 통해 빌드한다. 그럼 docker hub에서 postgre SQL을 다운 받는다.(Docker가 설치 되어 있어야 한다.)

  ```bash
  docker pull postgres
  ```

- Django 프로젝트 내에 settings.py에 가서 DB설정을 한다.

  ```python
  # settings.py
  import yaml
  
  # 하단의 Database항목에 있던 default DB를 삭제하고 입력한다.
  # Database
  # https://docs.djangoproject.com/en/2.2/ref/settings/#databases
  
  stream = open('settings_data.yml', 'r')
  settings = yaml.load(stream, yaml.SafeLoader)
  
  DATABASES = {
      'default': {
          'ENGINE': settings['DB']['DB_ENGINE'],
          'NAME': settings['DB']['DB_NAME'],
          'USER': settings['DB']['DB_USER'],
          'PASSWORD': settings['DB']['DB_PASSWORD'],
          'HOST': settings['DB']['DB_HOST'],
          'PORT': settings['DB']['DB_PORT'],
      }
  }
  ```
  
- ```yaml
  # settings_data.yml
  DB :
      DB_ENGINE : 'django.db.backends.postgresql'
      DB_NAME : 'db_name'
      DB_USER : 'db_user'
      DB_PASSWORD : 'db_password'
      DB_HOST : 'db'
      DB_PORT : 5432
  ```

## Nginx

- docker-compose를 작성할 폴더에 `./config/nginx/nginx.conf` 파일을 작성한다

  ```config
  upstream web {
    ip_hash;
    server web:8000; # 서버의 컨테이너 명
  }
  
  server {
    ssl                  on;
    ssl_certificate      /etc/pki/tls/certs/springgo.io_20200826SLZJ.crt.chain.pem;
    ssl_certificate_key  /etc/pki/tls/private/springgo.io_20200826SLZJ.key.pem;
    ssl_protocols  TLSv1 TLSv1.1 TLSv1.2;
    location / {
          proxy_pass http://web/;
      }
    location /media {
          autoindex on;
          alias /media/;
    }
    location /static {
          autoindex on;
          alias /static/;
    }
  
    listen 80;
    server_name localhost;
  }
  ```

  

## docker-compose

- 그럼 이제 docker-compose.yml 파일을 작성

  ```yaml
  # docker-compose.yml
  version : '3' # docker-compose version
  services:
      nginx:
          image: nginx:latest # nginx 서비스에서 사용할 도커 이미지
          ports:
              - "80:80" # 앞에 있는 컨테이너의 포트를 뒤에 있는 컴퓨터의 포트와 매칭시킨다.
          volumes:
          	# 앞에 있는 static 폴더의 위치를 뒤에 있는 nginix 내부의 /static위치에 매칭시킨다. 즉, 둘이 같은 폴더가 된다.
              - ./static:/static 
              - /data0/media:/media
              # nginx를 설정을 docker nginx 설정에 매칭 시킄 부분
              - ./config/nginx:/etc/nginx/conf.d
              # 현 디렉토리에 있는 인증서를 ninx내부로 링크 시킨다.
              - ./cert/RootChain:/etc/pki/tls/certs
              - ./cert/private:/etc/pki/tls/private
          depends_on: # 서비스 간의 종속성 표현
              - web
      web: # django App을 Web이라는 이름의 컨테이너로 생성
          build:
              context : . # 빌드할 프로젝트의 위치
              dockerfile : Dockerfile # 빌드할 Docker 파일 위치
          command: gunicorn -w 4 --threads 4 [프로젝트 이름].wsgi:application --bind 0.0.0.0:8000 # gunicon으로 빌드한다.
          volumes:
          	# 업로드 되는 media 파일을 백업 및 위에 nignx에서 접근 할 수 있도록 volmes지정
              - /media:/code/media 
          expose:
              - "8000"
          depends_on:
              - db
      db:
          image: postgres:latest
          # DB가 오류로 종료 되면 다시 시작하도록 설정
          restart: always
          ports:
              - "5432:5432"
          volumes:
          	# DB 컨테이너가 재 시작될 때마다 데이터가 삭제 되는 걸 방지 하기 위해 volumes 지정
              - /db/data:/var/lib/postgresql/data
          environment:
          	# DB 설정
              - POSTGRES_DB=db_name
              - POSTGRES_USER=db_user
              - POSTGRES_PASSWORD=db_password
  ```

  