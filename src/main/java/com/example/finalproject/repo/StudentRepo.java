package com.example.finalproject.repo;

import com.example.finalproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

   @Query("select s from Student s where lower(s.firstName) like lower(concat('%', :name, '%')) " +
           "or lower(s.lastName) like lower(concat('%', :name, '%'))")
   List<Student> searchByName(@Param("name") String name);

   boolean existsByEmail(String email);
   boolean existsByPhone(long phone);
}
