package com.eduproctor.tut.service;

import com.eduproctor.tut.entity.Department;
import com.eduproctor.tut.error.DepartmentNotFoundException;
import com.eduproctor.tut.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements   DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);  // We haven't created table but spring data JPA will create table for us
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
         Optional<Department> department=
                 departmentRepository.findById(departmentId);

         if(!department.isPresent()){
             throw new DepartmentNotFoundException("Department Not Available");
         }
         return department.get() ;
//        return departmentRepository.findById(departmentId).get(); // USed without throwing execption or error handling
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        //To get the data from the database
        Department depDB = departmentRepository.findById(departmentId).get();

        //The data got from the request and checking whether that specific param is not null and not not blank and updating that in the database object retrived previously;
        if(Objects.nonNull(department.getDepartmentName()) &&
        !"".equalsIgnoreCase(department.getDepartmentName())){
            depDB.setDepartmentName(department.getDepartmentName());  // IF name is not null and not blank set the department name
        }

        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDB.setDepartmentCode(department.getDepartmentCode());  // IF name is not null and not blank set the department name
        }

        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDB.setDepartmentAddress(department.getDepartmentAddress());  // IF name is not null and not blank set the department name
        }

        //Saving the depDB object which was retirived from repo
        return departmentRepository.save(depDB);
    }


    //Defining own method instead of using JPA methods
    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }


}
