package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookrepo;
	
	
	public void save(Book b)
	{
		bookrepo.save(b);
	}
	public List<Book> getAllBook()
	{
		return bookrepo.findAll();
	}
	public Book getBookById(int id)
	{
		return bookrepo.findById(id).get();
	}
	public void deleteBook(int id)
	{
		bookrepo.deleteById(id);
	}
	public Book getBookMaxId()
	{
		return bookrepo.findBookWithMaxId();
	}
	public List<Book> getBookByCateId(int id)
	{
		return bookrepo.getBookByCateId(id);
	}
	public List<Book> getBookBySearch(String txtSearch)
	{
		return bookrepo.getBookBySearch(txtSearch);
	}
	public List<Book> getBookBySellId(int id)
	{
		return bookrepo.getBookBySellId(id);
	}
	
}
