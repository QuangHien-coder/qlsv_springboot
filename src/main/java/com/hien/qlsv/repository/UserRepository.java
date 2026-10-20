package com.hien.qlsv.repository;

import com.hien.qlsv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// REPOSITORY = noi noi chuyen voi DB. Ke thua JpaRepository la co san
// save(), findById(), findAll(), deleteById(), count()... khong can viet SQL.
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring doc TEN ham va tu sinh SQL: SELECT * FROM users WHERE username = ?
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
