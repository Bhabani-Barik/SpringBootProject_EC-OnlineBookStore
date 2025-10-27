package com.nareshIt.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nareshIt.entity.BooksEntity;
import com.nareshIt.exceptions.BookIdNotFoundException;
import com.nareshIt.repository.BookRepository;
import com.nareshIt.service.BooksService;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public BooksEntity custmerSaveBooks(BooksEntity booksModule) {

		BooksEntity bookModule = bookRepository.save(booksModule);

		return bookModule;
	}

	@Override
	@Cacheable(value = "getAllBooks")
	public List<BooksEntity> custmergetAllBooks() {

		List<BooksEntity> list = bookRepository.findAll();

		System.out.println("check the database how many Times get the Records");

		return list;
	}

	@Override
	@Cacheable(cacheNames = "booksmodule", key = "#id")
	public BooksEntity getByCustmerBookid(Long id) {

		Optional<BooksEntity> bookId = bookRepository.findById(id);

		if (!bookId.isPresent()) {

			throw new BookIdNotFoundException("Book Id Not Found");
		}
		return bookId.get();
	}

}
