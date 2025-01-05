package com.eduproctor.tut.service;

import com.eduproctor.tut.entity.Result;
import com.eduproctor.tut.error.CustomException;

import java.util.List;

public interface ResultService {
    Result calculateAndSaveResult(Result result) throws CustomException;

    Result getCurrentResult();

    List<Result> getResultByClient(Long clientId);

    void deleteAllResultsByClient(Long clientId);

    void deleteResult(Long resultId);
}
