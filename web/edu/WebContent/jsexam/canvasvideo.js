var canvas, video;
function initiate() {
	var elem = document.getElementById('canvas');
	canvas = elem.getContext('2d');
	video = document.getElementById('media');

	video.addEventListener('click', push, false);
	// 클릭 했을때 시작하거나 중지하거나 한다.
}
function push() {
	if (!video.paused && !video.ended) {
		video.pause();
		window.clearInterval(loop);
	} else {
		video.play();
		loop = setInterval(processFrames, 33);
		// 재생하고 0.033초 마다 processFrames객체를 실행한다.
	}
}
function processFrames() {
	canvas.drawImage(video, 0, 0);
	// 오른 쪽에 출력한다.
	var info = canvas.getImageData(0, 0, 483, 272);
	// 이 범위 안에 나오는 이미지를 그대로 읽어온 다음에 이미지를 수정하기 위한 사전 작업
	var pos;
	var gray;
	for (x = 0; x <= 483; x++) {
		for (y = 0; y <= 272; y++) {
			pos = (info.width * 4 * y) + (x * 4);
			gray = parseInt(info.data[pos] * 0.2989 + info.data[pos + 1]
					* 0.5870 + info.data[pos + 2] * 0.1140);
			info.data[pos] = gray;
			info.data[pos + 1] = gray;
			info.data[pos + 2] = gray;
			// 그레이 톤으로 바꿔준다. 이중  for 문 주석 처리 하면 그대로 컬러로 나온다.
		}
	}
	canvas.putImageData(info, 0, 0);
	// 그리고 출력한다.
}
window.addEventListener("load", initiate, false);
// 즉, 사이트가 로딩되면 load 가 발생하면 initiate를 실행

