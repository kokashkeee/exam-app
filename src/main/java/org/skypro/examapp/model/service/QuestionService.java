package org.skypro.examapp.model.service;

import org.skypro.examapp.model.question.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String questionContent, String answer);
    Question remove(Question question);
    Collection<Question> getAll();
    Question getRandomQuestion();
}
