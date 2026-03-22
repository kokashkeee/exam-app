package org.skypro.examapp.model.service;

import org.skypro.examapp.model.question.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService questionService;
    private final Random random;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
        this.random = new Random();
    }

    @Override
    public Collection<Question> getQuestions(int amount){
        Collection<Question> allQuestions = questionService.getAll();
        if(amount>allQuestions.size()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST , "Максимальное кол-во вопросов: "
                            + allQuestions.size());
        }
        Set<Question> result = new HashSet<>();
        List<Question> questionList = new ArrayList<>(allQuestions);
        while (result.size() < amount) {
            int randomIndex = random.nextInt(questionList.size());
            result.add(questionList.get(randomIndex));
        }

        return result;
    }
}
