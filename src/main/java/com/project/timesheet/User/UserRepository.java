package com.project.timesheet.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE upper(u.lastName) LIKE concat('%',upper(?1),'%')")
    List<User> findAllByNameLike(String searchText);


//    @Modifying
//    @Query("UPDATE User u " +
//            "SET u.first_name = :first_name, u.last_name = :last_name, u.rate = :rate " +
//            "WHERE u.Id = :Id")
//    void editUser(@Param("first_name") String first_name,
//                @Param("last_name") String last_name,
//                @Param("rate") int rate);


//    @Query("UPDATE u FROM User u WHERE upper(u.lastName) LIKE concat('%',upper(?1),'%')")
//    @Query("UPDATE usera FROM tabelaUserów WHERE u.id LIKE (?1) wartościami u.firstName=(?2), u.lastName=(?3), u.rate(?4))
//    void editUserFromInterface(int id, String firstName, String lastName, int rate);
}

