package org.skypro.examapp.model.service;

import org.skypro.examapp.model.question.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
