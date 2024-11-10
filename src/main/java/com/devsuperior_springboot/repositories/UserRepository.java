package com.devsuperior_springboot.repositories;

import com.devsuperior_springboot.entities.User;
import com.devsuperior_springboot.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT u.email AS username, u.password AS password, r.id AS roleId, r.authority AS authority
            FROM User u
            JOIN u.roles ur
            INNER JOIN Role r ON ur.id = r.id
            WHERE u.email = :email
            """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :username")
    Optional<User> findByEmail(String username);
}
