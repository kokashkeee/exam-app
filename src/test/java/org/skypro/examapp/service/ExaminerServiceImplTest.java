package org.skypro.examapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examapp.model.question.Question;
import org.skypro.examapp.model.service.ExaminerServiceImpl;
import org.skypro.examapp.model.service.QuestionService;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private Set<Question> testQuestions;

    @BeforeEach
    void setUp() {
        testQuestions = Set.of(
                new Question("x1", "x"),
                new Question("y1", "y"),
                new Question("z1", "z"),
                new Question("w1", "w"),
                new Question("n1", "n")
        );
    }

    @Test
    void shouldReturnRequestedAmountOfQuestions() {
        when(questionService.getAll()).thenReturn(testQuestions);
        Collection<Question> result = examinerService.getQuestions(3);
        assertEquals(3, result.size());
        verify(questionService, times(1)).getAll();
    }

    @Test
    void shouldReturnAllQuestionsWhenAmountEqualsSize() {
        when(questionService.getAll()).thenReturn(testQuestions);
        Collection<Question> result = examinerService.getQuestions(5);
        assertEquals(5, result.size());
        assertTrue(result.containsAll(testQuestions));
    }

    @Test
    void shouldThrowResponseStatusExceptionWhenRequestingTooManyQuestions() {
        when(questionService.getAll()).thenReturn(testQuestions);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(10));
        assertEquals(org.springframework.http.HttpStatus.BAD_REQUEST,
                exception.getStatusCode());
        assertTrue(exception.getReason().contains("Максимальное кол-во вопросов: 5"));
        verify(questionService, times(1)).getAll();
    }

    @Test
    void shouldThrowResponseStatusExceptionWhenRequestingMoreThanAvailable() {
        when(questionService.getAll()).thenReturn(testQuestions);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(6));
        assertEquals(org.springframework.http.HttpStatus.BAD_REQUEST,
                exception.getStatusCode());
        assertTrue(exception.getReason().contains("5"));
    }

    @Test
    void shouldReturnAllUniqueQuestions() {
        when(questionService.getAll()).thenReturn(testQuestions);
        Collection<Question> result = examinerService.getQuestions(5);
        assertEquals(5, result.size());
        Set<Question> uniqueCheck = new HashSet<>(result);
        assertEquals(result.size(), uniqueCheck.size());
    }

    @Test
    void shouldHandleEmptyQuestionSet() {
        when(questionService.getAll()).thenReturn(Collections.emptySet());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(1));
        assertEquals(org.springframework.http.HttpStatus.BAD_REQUEST,
                exception.getStatusCode());
        assertTrue(exception.getReason().contains("Максимальное кол-во вопросов: 0"));
    }

    @Test
    void shouldReturnOneQuestionWhenAmountIsOne() {
        when(questionService.getAll()).thenReturn(testQuestions);
        Collection<Question> result = examinerService.getQuestions(1);
        assertEquals(1, result.size());
        assertTrue(testQuestions.contains(result.iterator().next()));
    }

    @Test
    void shouldReturnDifferentQuestionsOnMultipleCalls() {
        when(questionService.getAll()).thenReturn(testQuestions);
        Set<Question> firstCall = new HashSet<>(examinerService.getQuestions(3));
        Set<Question> secondCall = new HashSet<>(examinerService.getQuestions(3));
        assertEquals(3, firstCall.size());
        assertEquals(3, secondCall.size());
    }
}