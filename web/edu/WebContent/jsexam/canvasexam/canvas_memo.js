var device;
var drawing = false;
var canvas;
var context;
var rect;
var drawColor="black";
function setColor(color) {
	drawColor = color;	
}
function initialize() {
	// 캔버스 크기를 포스트잇 화면으로 만든다.
	// 즉 꾸미기
    context.clearRect(0,0,580,450);
    context.beginPath();
    context.rect(0,0,580,450);
    context.strokeStyle = "silver";
    context.fillStyle = "LightGoldenrodYellow";
    context.fill();
    context.lineWidth = 0.5;
    for(i=1;i<=8;i++) {
        context.moveTo(5,i*50);
        context.lineTo(575, i*50);
    }
    context.stroke();
}
function startDrawing() {
	// 이벤트 객체 정보를 이용하여 마우스가 움직이는 것을 처리한다.
	// 클릭 시간에 대핸 굵기 조정도 있다.
    drawing = true;
    context.beginPath();
    context.strokeStyle = drawColor;
    context.lineWidth = 1;
    context.arc(event.clientX - rect.left, event.clientY - rect.top, 3, 0, 2*Math.PI)
    context.stroke();
    context.fillStyle = drawColor;
    context.fill();
    context.closePath();
    context.beginPath();
    context.moveTo(event.clientX - rect.left, event.clientY - rect.top);
    context.lineCap = "round";
    // 그려진 선분의 끝에 라운드 캡을 씌워준다.
    context.lineWidth = 6;
}
function keepDrawing() {
	console.log("MOVE");
	// 움직이는 동안 move 로그를 띄운다.
    if (drawing) {
    	//드로잉이 트루인 동안 저장
        var x,y;
        if (device == "mobileDevice") {
            x = event.targetTouches[0].pageX;
            y = event.targetTouches[0].pageY;
        }
        else {
            x = event.clientX;
            y = event.clientY;
        }
        context.lineTo(x - rect.left, y - rect.top);
        context.stroke();
    }
}
function stopDrawing() {
    if (drawing) {
        context.stroke();
        drawing = false;
    }
}
function save() {	
    alert(canvas.toDataURL());
    // 캔버스에 저장된 모든 내용을 URL 형식으로 변환해서 띄워준다.
    localStorage.setItem("canvas", canvas.toDataURL());    
}
function restore() {
	var img = new Image();
	// 비어있는 이미지 형식으로 만든다.
    img.src = localStorage.getItem("canvas");
    // 비어있는 이미지
    img.onload = function() {
    	context.drawImage(img, 0, 0);        
    }
    // 외부에서 이미지 파일을 읽어 오는 것이니, 온 로드를 사용하지 않으면, 이미지 파일이 다 불려오기 전에 그려 버릴 수 있으니 그것을 방지하기 위해서
    // 디코딩까지 다 끝난 상태에서 드로우 이벤트를 하겠다.
    // 만약 다 로딩 되지 않는다면 제대로 그려지지 않는 경우가 있다.
    
}
function upload() {
	var data = new FormData();
	var myblob = new Blob([canvas.toDataURL()], {type : 'text/plain'});
	data.append('file', myblob, "test.png");
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/edu/upload", true);
	xhr.send(data);  
	xhr.onload=function() { alert("업로드완료");};
}
function downimage() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "/edu/download", true);
	xhr.send();  
	xhr.onload=function(e) { 
        var img = new Image();
        img.src = this.responseText;
        context.drawImage(img, 0, 0);
	}   
}
function getDeviceType() {
    var str = navigator.userAgent;
    if (str.match(/(ipad)|(iphone)|(ipod)|(android)|(webos)/i))
        device = "mobileDevice";
    else
        device = "desktopPC";
}
function startMemo() {
	getDeviceType();
	// 디바이스에 대한 정보를 얻어온다.
	canvas = document.getElementById("myCanvas");
	if (device == "mobileDevice") {
		//모바일 디바이스면 터치 이벤트에 대한 이벤트 처리
		canvas.ontouchstart = startDrawing;
		canvas.ontouchmove = keepDrawing;
		canvas.ontouchend = stopDrawing;
	} else {
		// 피시면 마우스 이벤트에 대한 처리를 한다.
		canvas.onmousedown = startDrawing;
		canvas.onmousemove = keepDrawing;
		canvas.onmouseup = stopDrawing;
	}    
    context = canvas.getContext("2d"); 
    rect = canvas.getBoundingClientRect();
    initialize();
}
document.body.onload = startMemo;