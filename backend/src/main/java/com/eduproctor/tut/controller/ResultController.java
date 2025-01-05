package com.eduproctor.tut.controller;


import com.eduproctor.tut.entity.Result;
import com.eduproctor.tut.error.CustomException;
import com.eduproctor.tut.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/create")
    public ResponseEntity<Result> addResult(@RequestBody Result result) throws CustomException {
        return ResponseEntity.ok(resultService.calculateAndSaveResult(result));
    }

    @GetMapping("/current")
    public ResponseEntity<Result>  getCurrentResult(){
        return ResponseEntity.ok(resultService.getCurrentResult());
    }

    @GetMapping("/all/{clientId}")
    public ResponseEntity<List<Result>> getResultByClient(@PathVariable Long clientId){
        return ResponseEntity.ok(resultService.getResultByClient(clientId));
    }


    @DeleteMapping("/deleteAll/{clientId}")
    public ResponseEntity<String> deleteAllResultsByClient(@PathVariable Long clientId){
        resultService.deleteAllResultsByClient(clientId);
        return ResponseEntity.ok("Results deleted successfully");
    }

    @DeleteMapping("delete/{resultId}")
    public ResponseEntity<String> deleteResult(@PathVariable Long resultId){
        resultService.deleteResult(resultId);
        return ResponseEntity.ok("Result deleted successfully");
    }
}
