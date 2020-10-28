# 장고 동적 업로드

## 장고 업로드 경로 동적 설정

장고의 model.py의 모델에 선언되는 파일의 업로드 경로는 보통 이렇게 선언된다.

```python
media = models.FileField(upload_to='media')
```

/media/media 안에 업로드된 파일이 올라가게 된다.

그럼 파일 업로드 경로에 Date형식이나, UserID 같은 것도 올려주고 싶다면 어떻게 해야하는가에 대함 것이다.



### 날짜를 포함 시키는 방법

```python
import datetime

def upload_path(instance, filename):
    nows = datetime.datetime.now()
    nowDate_year = nows.strftime('%Y')
    nowDate_month = nows.strftime('%m')
    nowDate_day = nows.strftime('%d')
    return f'{nowDate_year}-{nowDate_month}-{nowDate_day}/{filename}'

class UploadFileModel(models.Model):
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASECADE)
    media = models.FileField(upload_to=upload_path)
```

이렇게 선언하면 된다.

이렇게 선언하고 `python manage.py makemigrations`하고 확인해 본다면,

```python
operations = [
        migrations.CreateModel(
            name='UploadFileModel',
            fields=[
                ('files', models.FileField(upload_to=example.models.upload_path)),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASECADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
    ]
```

같이 선언되어 있는 것을 알 수 있다.

즉, example 앱 안의 model.py의 upload_path 함수를 불러와 파일 경로를 읽겠다는 의미이다. 즉, 함수 안의 값을 바꿀때마다 매번 migrate하지 않아도 상관없다. 읽어오게 되는 것이니까.



## 유저를 읽어오는 방법

```python
def upload_path(instance, filename):
    return f'{instance.user}/{filename}'

class UploadFileModel(models.Model):
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASECADE)
    media = models.FileField(upload_to=upload_path)
```

이렇게 선언하면 user디렉토리 안에 파일이 저장된다.

instance의 값을 (`print(instance.__dict__)`로 출력)보면 모델의 값이 할당 되어 있는 것을 알 수 있다.

이런 식으로 하면 해당 게시판 이름 안에 값이 저장되는 것을 볼 수 있다.

```
{'_state': <django.db.models.base.ModelState object at 0x03934E68>, 
'id': None, 
'boardname': 'happy', 
'media': <FieldFile: dummy.txt>, 
'user_id': 1}
```

이런 식으로 

```python
def upload_path(instance, filename):
    return f'{instance.boardname}/{instance.user}/{filename}'

class UploadFileModel(models.Model):
    boardname = models.CharField(max_length=50)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASECADE)
    media = models.FileField(upload_to=upload_path)
```



## 기타

위에 언급했다 싶이 함수를 읽어오는 것이다 보니, 다양한 방법으로 사용이 가능하다.

```python
def upload_path(instance, filename):
    uploaded = UploadFileModel.objects.filter(boardname__exact=instance.boardname).count()
    return f'{instance.boardname}/{instance.uploaded}/{instance.user}/{filename}'

class UploadFileModel(models.Model):
    boardname = models.CharField(max_length=50)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASECADE)
    media = models.FileField(upload_to=upload_path)
```

이런 식으로 해당 게시판의 몇번째 이미지인지를 알려주는 방식도 가능하다.