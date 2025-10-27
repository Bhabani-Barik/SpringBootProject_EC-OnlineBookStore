package com.nareshIt.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nareshIt.entity.BooksExcelFileEntity;
import com.nareshIt.repository.BooksExcelFileRepository;
import com.nareshIt.service.BooksExcelUploadService;
import com.nareshIt.utility.Helper;

@Service
public class BooksExcelUploadServiceImpl implements BooksExcelUploadService {

	@Autowired
	BooksExcelFileRepository booksExcelFileRepository;

	@Override
	public void uploadExcelintoDB(MultipartFile file) throws IOException {

		List<BooksExcelFileEntity> excelFilesSaveDatabase = Helper.excelFilesInsertDatabase(file.getInputStream());

		booksExcelFileRepository.saveAll(excelFilesSaveDatabase);

	}

}
