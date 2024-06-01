package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
	
	@Query("SELECT b FROM Book b WHERE b.Id = (SELECT MAX(b2.Id) FROM Book b2)")
    public Book findBookWithMaxId();

	@Query("SELECT b FROM Book b WHERE b.category.id=?1")
	public List<Book> getBookByCateId(int id);
	
	@Query("SELECT b FROM Book b  WHERE b.name LIKE %?1%")
	public List<Book> getBookBySearch(String txtSeaarch);
	
	@Query("SELECT b FROM Book b WHERE b.taiKhoan.id=?1")
	public List<Book> getBookBySellId(int id);
}
