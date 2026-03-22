package org.skypro.examapp.controller;

import org.skypro.examapp.model.question.Question;
import org.skypro.examapp.model.service.ExaminerServiceImpl;
import org.skypro.examapp.model.service.JavaQuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,
                                @RequestParam String answer){
        return javaQuestionService.add(question,answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam Question question){
        return javaQuestionService.remove(question);
    }

    @GetMapping
    public Collection<Question> getAll(){
        return javaQuestionService.getAll();
    }
}
