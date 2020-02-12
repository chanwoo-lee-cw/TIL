package vo;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	private MultipartFile uploadFile;
	// MultipartFile 파일만 있으면 무조건 멀티 파트로 해야한다.
	// 빈 객체로 multipart로 선언 되어 있음.
	// 단일 파일만 됨
	// 하지만, 여러개 파일을 받고 싶으면 배열로 하자
	// private MultipartFile[] uploadFile;
	
	// 만약 일반 쿼리도 받고 싶으면
	
	// private String name
	// 으로 일반 쿼리는 name에 넣고 파일은 uploadFile 에 세팅하면 된다.

	public MultipartFile getUploadFile() {		
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
