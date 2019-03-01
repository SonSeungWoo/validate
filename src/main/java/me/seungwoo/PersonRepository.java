package me.seungwoo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo.
 * User: sonseungwoo
 * Date: 2019-02-26
 * Time: 21:25
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "select * from person where firstName = ?1", nativeQuery = true)
    Person findByFirstName(String firstName);
}
