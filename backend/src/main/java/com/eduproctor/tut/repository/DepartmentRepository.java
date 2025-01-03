package com.eduproctor.tut.repository;

import com.eduproctor.tut.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//We don't create implementation class instead we use JPA repository itself which provides variety of features as it has all the methods already implemented
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // We can use native query as well using @Query annotation where we write queries to retrive the data

    public Department findByDepartmentName(String departmentName);
}


