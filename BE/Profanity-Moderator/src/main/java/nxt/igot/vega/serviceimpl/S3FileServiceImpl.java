package nxt.igot.vega.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nxt.igot.vega.service.FileService;
@Service
public class S3FileServiceImpl implements FileService{

	@Override
	public String uploadImage(MultipartFile file) {
		//implement s3 push code
		return "https://upload.wikimedia.org/wikipedia/en/9/95/Test_image.jpg";
	}

}
