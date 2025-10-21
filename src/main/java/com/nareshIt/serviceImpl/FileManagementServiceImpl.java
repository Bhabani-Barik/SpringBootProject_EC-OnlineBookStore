package com.nareshIt.serviceImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nareshIt.entity.FilesEntity;
import com.nareshIt.repository.FileRepo;
import com.nareshIt.service.IFileManagementService;


@Service
public class FileManagementServiceImpl implements IFileManagementService{

	
	@Autowired private FileRepo fileRepo;
	
	@Override
	public String storeFile(MultipartFile file) throws IOException {
		
		FilesEntity fss = new FilesEntity();
		fss.setFileName(file.getOriginalFilename());
		fss.setFileType(file.getContentType());
		fss.setData(file.getBytes());
		fileRepo.save(fss);
		
		return "File Inserted Successfully....!" + file.getOriginalFilename();
	}

}
