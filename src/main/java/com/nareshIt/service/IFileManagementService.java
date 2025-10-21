package com.nareshIt.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileManagementService {
	public String storeFile(MultipartFile file) throws IOException;
}
