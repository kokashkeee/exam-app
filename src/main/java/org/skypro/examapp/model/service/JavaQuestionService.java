package org.skypro.examapp.model.service;

import org.skypro.examapp.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{
    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        throw new IllegalArgumentException("Вопрос не найден");
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if(questions.isEmpty())throw new NullPointerException("Вопрос нет, базару жок");
        int randomIndex = random.nextInt(questions.size());
        List<Question> questionList = new ArrayList<>(questions);
        return questionList.get(randomIndex);
    }
}
