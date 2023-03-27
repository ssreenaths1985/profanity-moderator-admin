package nxt.igot.vega.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uploadImage(MultipartFile file);

}
