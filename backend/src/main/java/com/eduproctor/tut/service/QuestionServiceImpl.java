package com.eduproctor.tut.service;

import com.eduproctor.tut.dto.QuestionDTO;
import com.eduproctor.tut.entity.*;
import com.eduproctor.tut.error.ResourceNotFoundException;
import com.eduproctor.tut.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private FillInTheBlanksQuestionRepository fillInTheBlanksQuestionRepository;

    @Autowired
    private ShortAnswerQuestionRepository shortAnswerQuestionRepository;

    @Autowired
    private EssayQuestionRepository essayQuestionRepository;

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;


    @Transactional
    public Question createQuestion(QuestionDTO questionDTO) {

        Examination exam = examinationRepository.findById(questionDTO.getExaminationId())
                .orElseThrow(()-> new ResourceNotFoundException("Examination with id: "+questionDTO.getExaminationId()+" not found"));

        QuestionType questionType = questionTypeRepository.findById(questionDTO.getTypeId())
                .orElseThrow(()-> new ResourceNotFoundException("Question Type with id: "+questionDTO.getTypeId()+" not found"));

//        Question baseQuestion = Question.builder()
//                .content(questionDTO.getContent())
//                .examination(exam)
//                .type(questionType)
//                .build();

        Question savedQuestion ;

        switch (questionDTO.getTypeId().intValue()){
            case  1: //QuizQuestion
                savedQuestion = QuizQuestion.builder()
                        .content(questionDTO.getContent())
                        .examination(exam)
                        .type(questionType)
                        .option1(questionDTO.getOption1())
                        .option2(questionDTO.getOption2())
                        .option3(questionDTO.getOption3())
                        .option4(questionDTO.getOption4())
                        .answer(questionDTO.getQuizAnswer())
                        .build();
                savedQuestion = quizQuestionRepository.save((QuizQuestion) savedQuestion);
                break;

            case 2: // Fill in the Blanks
                savedQuestion = FillInTheBlanksQuestion.builder()
                        .content(questionDTO.getContent())
                        .examination(exam)
                        .type(questionType)
                        .answer(questionDTO.getFillAnswer())
                        .build();
                savedQuestion = fillInTheBlanksQuestionRepository.save((FillInTheBlanksQuestion) savedQuestion);
                break;

            case 3: // Short Answer
                savedQuestion = ShortAnswerQuestion.builder()
                        .content(questionDTO.getContent())
                        .examination(exam)
                        .type(questionType)
                        .answer(questionDTO.getShortAnswer())
                        .build();
                savedQuestion = shortAnswerQuestionRepository.save((ShortAnswerQuestion) savedQuestion);
                break;

            case 4: // Essay Question
                savedQuestion = EssayQuestion.builder()
                        .content(questionDTO.getContent())
                        .examination(exam)
                        .type(questionType)
                        .answer(questionDTO.getEssayAnswer())
                        .wordLimit(questionDTO.getWordLimit())
                        .build();
                savedQuestion = essayQuestionRepository.save((EssayQuestion) savedQuestion);
                break;


//            case  2: //Fill in  the blanks
//                FillInTheBlanksQuestion fillInTheBlanksQuestion = new FillInTheBlanksQuestion();
//                fillInTheBlanksQuestion.setId(savedQuestion.getId());
//                fillInTheBlanksQuestion.setContent(questionDTO.getContent());
//                fillInTheBlanksQuestion.setAnswer(questionDTO.getFillAnswer());
//                fillInTheBlanksQuestionRepository.save(fillInTheBlanksQuestion);
//                break;
//
//
//            case 3: // Short Answer
//                ShortAnswerQuestion shortAnswerQuestion = new ShortAnswerQuestion();
//                shortAnswerQuestion.setId(savedBaseQuestion.getId());
//                shortAnswerQuestion.setContent(questionDTO.getContent());
//                shortAnswerQuestion.setAnswer(questionDTO.getShortAnswer());
//                shortAnswerQuestionRepository.save(shortAnswerQuestion);
//                break;
//
//
//            case 4: //
//                EssayQuestion essayQuestion = new EssayQuestion();
//                essayQuestion.setId(savedBaseQuestion.getId());
//                essayQuestion.setContent(questionDTO.getContent());
//                essayQuestion.setAnswer(questionDTO.getEssayAnswer());
//                essayQuestion.setWordLimit(questionDTO.getWordLimit());
//                essayQuestionRepository.save(essayQuestion);
//                break;

            default:
                throw new IllegalArgumentException("Invalid Question Type");

        }

        return savedQuestion;
    }

    @Override
    @Transactional
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Question with Question no: "+id+" not found"));
    }

    @Override
    public List<Question> getQuestionByExamination(Long eid) {
        return questionRepository.findByExaminationEid(eid);
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question with id: "+id+" not present"));
        questionRepository.delete(question);
    }
}
