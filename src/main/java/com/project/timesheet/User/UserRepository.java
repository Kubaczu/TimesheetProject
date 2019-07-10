package com.project.timesheet.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE upper(u.lastName) LIKE concat('%',upper(?1),'%')")
    List<User> findAllByNameLike(String searchText);
}
