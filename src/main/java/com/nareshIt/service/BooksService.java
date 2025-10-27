package com.nareshIt.service;

import java.util.List;

import com.nareshIt.entity.BooksEntity;

public interface BooksService {
	
	BooksEntity custmerSaveBooks(BooksEntity booksEntity);

	List<BooksEntity> custmergetAllBooks();

	BooksEntity getByCustmerBookid(Long id);

}
