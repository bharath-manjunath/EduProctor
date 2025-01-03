package com.eduproctor.tut.controller;

import com.eduproctor.tut.entity.Department;
import com.eduproctor.tut.error.DepartmentNotFoundException;
import com.eduproctor.tut.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired // Autowire(Attach) The spring object you have to the below object reference (dependency injection) Their are 2 types 1) Contructor based 2) Property based 3) Setter based
    private DepartmentService departmentService;

//    private final Logger LOGGER =
//            LoggerFactory.getLogger( name: DepartmentController.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){  // The JSON which is been passed has to be converted to Department Object  @RequestBody does this job for us
        // Now we have to call the service layer to pass this data and in service layer we perform the bussiness logic and from their we can call the repository layer to save the data
        //DepartmentService service = new DepartmentServiceImpl();  This is not required by this we are taking the control back instead we can use the object from spring by specifying annotation
//        LOGGER.info("Inside Save Department of Departmentname");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList(){
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}") //Path variable
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted Successfully!!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return departmentService.updateDepartment(departmentId,department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);
    }

}
