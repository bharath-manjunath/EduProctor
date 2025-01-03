package com.eduproctor.tut.service;

import com.eduproctor.tut.entity.*;
import com.eduproctor.tut.error.CustomException;
import com.eduproctor.tut.error.ResourceNotFoundException;
import com.eduproctor.tut.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService{
    @Autowired
    ResultRepository resultRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    ClientRepository clientRepository;

//    @Autowired
//    QuestionTypeRepository questionTypeRepository;


    @Override
    public Result calculateAndSaveResult (Result result) throws CustomException {

        Examination examination = result.getExamination();
        if (examination == null || examination.getEid() == null) {
            throw new CustomException("Examination details are missing");
        }

        examination = examinationRepository.findById(examination.getEid())
                        .orElseThrow(()->new ResourceNotFoundException("Examination id found"));

        if(!examination.isActive()){
            throw new CustomException("Examination is not active ");
        }
        result.setExamination(examination);

        result.setNumOfQuestions(examination.getNumOfQuestions());

        Client client = result.getClient();
        if (client == null || client.getId() == 0) {
            throw new CustomException("Client details are missing");
        }

        client = clientRepository.findById(client.getId())
                .orElseThrow(() -> new CustomException("Client not found: "));

        result.setClient(client);

        try{
            int correctAnswers = 0;
            int attempted = 0;

            for(UserQna userQna : result.getUserQnas()){
                Question question = questionRepository.findById(userQna.getQuesId())
                        .orElseThrow(()-> new CustomException("Question with id: "+userQna.getQuesId()+" not found"));

                if(question.getAnswer().equals(userQna.getAnswer())){
                    correctAnswers++;
                }

                if(userQna.getAnswer()!=null && !userQna.getAnswer().isEmpty()){
                    attempted++;
                }

                userQna.setResult(result);

            }

            result.setCorrectAnswers(correctAnswers);
            result.setQuestionsAttempted(attempted);
            result.setMarksScored((double) correctAnswers / result.getNumOfQuestions()*100);
            result.setSubmitDateTime(LocalDateTime.now());

            return resultRepository.save(result);

        }catch (Exception e){
            throw new CustomException("Error Calculating Results"+e.getMessage());
        }
//        return null;
    }

    @Override
    public Result getCurrentResult() {
        return resultRepository.findTopByOrderBySubmitDateTimeDesc()
                .orElseThrow(()-> new ResourceNotFoundException("Error in displaying current result"));
    }

    @Override
    public List<Result> getResultByClient(Long clientId) {
        return resultRepository.findByClientId(clientId);
    }

    @Override
    @Transactional
    public void deleteAllResultsByClient(Long clientId) {
        resultRepository.deleteByClientId(clientId);
    }

    @Override
    @Transactional
    public void deleteResult(Long resultId) {
        resultRepository.deleteById(resultId);
    }
}
