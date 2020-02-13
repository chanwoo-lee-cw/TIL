var source, drop;
function initiate(){
  source=document.getElementById('image');
  source.addEventListener('dragstart', dragged, false);

  drop=document.getElementById('dropbox');
  drop.addEventListener('dragover', function(e){console.log("dragover"); e.preventDefault(); }, false);
  //
  drop.addEventListener('drop', dropped, false);
}
function dragged(e){
	//매개변수 이벤트 객체
	console.log("dragstart");
  var code='<img src="'+source.getAttribute('src')+'">';
  e.dataTransfer.setData('Text', code);
}
function dropped(e){
	console.log("drop");
  e.preventDefault();
  // 기본 이벤트 핸틀러 해지
  drop.innerHTML+=e.dataTransfer.getData('Text');
  // 문자열에 text에 대한 데이터를 추가한다. 즉, <img id="image" src="monster1.gif" >를 추가한다.
  // 자바 스크립트 파일이 외부 파일일 경우에는 html 파일을 아무리 새로 고침해도, 이미 불러온 js파일을 새로 불러오지 않는다. 
  //즉, 외부 파일일 경우는 캐시 비우기를 통해 비워줘야지 외부 파일을 불러온다.
}
window.addEventListener('load', initiate);

// 드래그 스타트 이벤트는 드래그 시작 할때 호출
// 드래그 오버는 원하는 드롭 박스 안에 있는 동안 계쏙해서 호출된다.
// 드래그 엔드는 드롭 박스 내려 놓아야 호출 된다.