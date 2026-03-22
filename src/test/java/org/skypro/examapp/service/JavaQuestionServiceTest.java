package org.skypro.examapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skypro.examapp.model.question.Question;
import org.skypro.examapp.model.service.JavaQuestionService;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void shouldAddQuestion() {
        Question question = javaQuestionService.add("Вопрос", "Ответ");
        assertEquals("Вопрос", question.getQuestion());
        assertEquals("Ответ", question.getAnswer());
        assertEquals(1, javaQuestionService.getAll().size());
    }

    @Test
    void shouldNotAddDuplicate() {
        javaQuestionService.add("Вопрос", "Ответ");
        javaQuestionService.add("Вопрос", "Ответ");
        assertEquals(1, javaQuestionService.getAll().size());
    }

    @Test
    void shouldRemove() {
        Question question = javaQuestionService.add("x", "y");
        javaQuestionService.remove(question);
        assertEquals(0, javaQuestionService.getAll().size());
    }

    @Test
    void shouldTrowWhenNonExistentQuestion() {
        Question question = new Question("x", "y");
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.remove(question));
    }

    @Test
    void shouldGetRandomQuestion() {
        Question question = javaQuestionService.add("x", "y");
        Question question1 = javaQuestionService.add("z", "w");
        Question random = javaQuestionService.getRandomQuestion();
        assertNotNull(random);
    }

    @Test
    void shouldThrowWhenNoQuestionsForRandom() {
        assertThrows(NullPointerException.class, () -> javaQuestionService.getRandomQuestion());

    }
}