package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.TaiKhoan;


@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    @Query("SELECT tk FROM TaiKhoan tk WHERE tk.username = ?1 AND tk.password = ?2")
    public TaiKhoan getTaiKhoan(String username, String password);
    
    @Query("SELECT tk FROM TaiKhoan tk WHERE tk.username = ?1 OR tk.email = ?2")
    public TaiKhoan checkTaiKhoanOrEmailTonTai(String username,String email);
       
    public TaiKhoan findByUsername(String username);
    public TaiKhoan findByEmail(String email);


}
