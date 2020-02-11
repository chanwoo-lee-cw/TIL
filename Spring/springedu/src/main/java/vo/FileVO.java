package vo;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	private MultipartFile uploadFile;

	public MultipartFile getUploadFile() {		
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
