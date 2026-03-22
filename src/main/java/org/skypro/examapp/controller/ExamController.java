package org.skypro.examapp.controller;

import org.skypro.examapp.model.question.Question;
import org.skypro.examapp.model.service.ExaminerService;
import org.skypro.examapp.model.service.ExaminerServiceImpl;
import org.skypro.examapp.model.service.JavaQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestion(@PathVariable int amount){
        return examinerService.getQuestions(amount);
    }
}
