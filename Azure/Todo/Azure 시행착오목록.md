1. python - linux server

   일단 [python-linux 빠른 실행](https://docs.microsoft.com/ko-kr/azure/app-service/containers/quickstart-python?tabs=cmd)의 

   ```cmd
   py -3 -m venv env
   env\scripts\activate
   pip install -r requirements.txt
   SET FLASK_APP=application.py
   flask run
   ```

   를 실행해 보려고 하니 에러가 났다.

   처음에 `py -3 -m venv env`코드를 치니 cmd창이 멈추는 것 같아서 여기서 에러가 뜨는 줄알았다.

   ![](C:\Users\overe\AppData\Roaming\Typora\typora-user-images\image-20200326223421001.png)

   그런데 알고 보니 원래 저기까지 되는게 정상이고, 그 다음에 `env\scripts\activate`를 입력하면 바로 가상 화면을 구축하는 시스템, 즉 정상 작동하는 것

   `py -3 -m venv env
env\scripts\activate`
   
   의 부분은 가상 환경을 구축하는 과정이였다.

   [^가상 환경]: 패키지가 시스템 전체에 설치되는 것이 아니라, 특정 응용 프로그램에 사용되도록 설치될 수 있도록 하는 반 격리 된 파이썬 환경입니다.
   [^ venv]:virtual environment를 만들어주는 패키지다. gradle, maven처럼 **프로젝트의 라이브러리 버전을 매니지먼트**해준다 [참고](https://ssaemo.tistory.com/132)

​	


   하지만 에러가 뜨는게 있었는데 이 에러는 

   `pip install -r requirements.txt
SET FLASK_APP=application.py
   flask run`

   부분에서 에러가 나는 것이였는데 이건 내가 파이썬을 쓰면서 pip install과 flask를 설치하지 않아서 생긴 에러였다.

   pip는 이제 시작해봅시다. [**pip**](http://pip-installer.org/)는 [**Python Package Index**](http://pypi.python.org/) (PyPI) 저장소로부터 파이썬 패키지를 받아 설치하는 패키지 관리 도구였다. 즉, pip를 설치하면 패키지 설치를 쉽게 할 수 있다.

   FLASK는 [Python](https://namu.wiki/w/Python)의 마이크로 [웹 프레임워크](https://namu.wiki/w/웹 프레임워크)이다. 다양한 웹 엔진과 붙여서 쓸 수 있고 또 가볍기도 해서 [Django](https://namu.wiki/w/Django)와 같이 쓰는 경우도 있다. 코드도 비교적 단순하고, 특히 [API](https://namu.wiki/w/API) 서버를 만들기에 매우 편리하다. 관련된 확장 기능들이 많기 때문이다.



2. 그 다음은

   ```cmd
   az webapp up --sku F1 -n <app-name> -l <location-name>"
   ```

   에서 에러가 났는데 

   ```error
   Unable to retrieve details of the existing app 
   ```

   라는 에러는 인터넷

   일단 아무것도 올라가지 않는 에러인데도 이미 앱이 올라가 있다고 뜨는데

   그래서 수정을 하고 다시 업로드를 해봐도 똑같은 에러

   인터넷에 찾아보니 문제는 CLI 버전 문제라고 한당.

3. 그 다음은

   마이크로소프트 Azure를 SQL DB를 생성하면서 에러가 났다

   에러 메세지는

   ```
   허용된 공급자 네임스페이스의 목록은 'Microsoft.Authorization,Microsoft.Features,microsoft.insights,Microsoft.NotificationHubs,Microsoft.Resources,Microsoft.Sql,microsoft.support,microsoft.visualstudio,Microsoft.Web'입니다.
   ```

   인데, 이건 이것 저것 실핸하다 보며 안 것인데.

   이건 그냥 결제 프로필을 정하지 않아서 생겼던 문제.

   카드를 등록하면서 자동으로 해결이 되었다.

   
   
4. DB랑 웹앱이랑 연동이 안됨.

   로그인이 안되서 이것저것 테스트 해 보았다.

   ```php
   <?php
    $con = mysqli_connect(----, ----, -----, ---,----);
    mysqli_set_charset($con, 'utf8');
    
    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM member WHERE userID = ? AND userPassword = ?");
    mysqli_stmt_bind_param($statement, "ss", $userID, $userPassword);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $userPassword);
   
    $response = array();
    $response["success"] = false;
    
    while(mysqli_stmt_fetch($statement)){ 
     $response["success"] = true;
     $response["userID"] = $userID;
     $response["userPassword"] = $userPassword;
    }
    
    echo json_encode($response); 
   ?>?
   ```

   이런 코드인데

   이 코드를 사용하고, 주소창에 입력하는 방식으로 들어가면 코드가 정상 작동을 한다.

   근데 데이터베이스와 연동이 안되... 왜?

   첨엔 ID랑 비밀 번호가 안 가는 줄 알았는데

    $response["temp"] =  $userID;를 반환해 보니 정상적으로 또 반환이 된다.

   즉, ID랑 비밀 번호는 주소+login/php라는 방식으로 전달 되었을때는 정상적으로 전달이 된다는 건데

   그래서 첨엔 sql 문이 문제인줄 알았다.

   $statement를 출력해 보니 NULL이 반환.

   아예 DB랑 연동이 안되는 거였음.

   그래서 $con을 출력해 보니 NULL이 반환...

   이제 즉, DB랑 연동이 안된다는건데, 

   이유는 인증서 문제였다.
   
   ```
   <?php
   	$host = "todomysqldb.mysql.database.azure.com";
   	$username = "takaram@todomysqldb";
   	$password = "semi32834!";
   	$db_name = "tododb";
   
   //Establishes the connection
   	$con = mysqli_init();
   	mysqli_ssl_set($con, NULL, NULL, {ca-cert filename}, NULL, NULL); 
   	mysqli_real_connect($con, $host, $username, $password, $db_name, 3306);
   	if (mysqli_connect_errno($con)) {
   		$response = array();
   		$response["temp"] =  "error";
   		echo json_encode($response); 
   		die('Failed to connect to MySQL: '.mysqli_connect_error());
   		
   	}
   	mysqli_set_charset($con, 'utf8');
    
   	$userID = $_POST["userID"];
   	$userPassword = $_POST["userPassword"];
    
   	$statement = mysqli_prepare($con, "SELECT * FROM member WHERE userID = ? AND userPassword = ?");
   	mysqli_stmt_bind_param($statement, "ss", $userID, $userPassword);
   	mysqli_stmt_execute($statement);
   	mysqli_stmt_store_result($statement);
   	mysqli_stmt_bind_result($statement, $userID, $userPassword);
   
   	$response = array();
   	$response["success"] = false;
   	$response["temp"] =  $con;
    
   	while(mysqli_stmt_fetch($statement)){ 
   		$response["success"] = true;
   		$response["userID"] = $userID;
   		$response["userPassword"] = $userPassword;
   	}
    
   	echo json_encode($response); 
   ?>?
   ```
```
   
이런 코드로 고쳐서 
   
   한줄 한줄 지우면서 테스트 해본 결과
   
   `mysqli_ssl_set($con, NULL, NULL, {ca-cert filename}, NULL, NULL); `
   
   이 줄을 지웠더니
   
   ```error
   Failed to connect to MySQL: SSL connection is required. Please specify SSL options and retry.
```

   이런 에러가 떴다. 

   즉, SSL연결이 필요하다. 즉 인증서 문제였다.

   [인증서 바인딩 방법](https://docs.microsoft.com/ko-kr/azure/mysql/howto-configure-ssl)

   인증서를 바인딩 해주고 연결 부분을 이렇게 고쳤더니

   ```php
   $con = mysqli_init();
   mysqli_ssl_set($conn,NULL,NULL, "/var/www/html/BaltimoreCyberTrustRoot.crt.pem", NULL, NULL) ; 
   	mysqli_real_connect($con, $host, $username, $password, $db_name,3306, MYSQLI_CLIENT_SSL);
   	if (mysqli_connect_errno($con)) {
   		$response = array();
   		$response["temp"] =  "error";
   		echo json_encode($response); 
   		die('Failed to connect to MySQL: '.mysqli_connect_error());
   	}
   ```

   정상 작동하였다.