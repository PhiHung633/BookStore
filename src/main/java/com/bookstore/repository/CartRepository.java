package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;


@Repository
public interface CartRepository extends CrudRepository<Cart,Integer> {
	
    @Query("SELECT b FROM Cart c JOIN c.book b WHERE c.taiKhoan.Id = ?1")
	public List<Book> getBookByTaiKhoan(int id);
    
    @Query("SELECT c FROM Cart c WHERE c.taiKhoan.Id = ?1")
	public List<Cart> getCartByTaiKhoan(int id);
    
    public void deleteByTaiKhoan_Id(int id);

}
