package com.team.app.backend.service;

import com.team.app.backend.dto.*;
import com.team.app.backend.exception.QuizNotFoundException;
import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.Quiz;

import java.util.List;

public interface QuizService {
    Long addQuestion(QuestionDto questionDto);

    Long addDefQuestion(QuestionDefAddDto questionDefAddDto);

    Long addOptQuestion(QuestionOptAddDto questionOptAddDto);

    Long addSeqOptQuestion(QuestionSeqAddDto questionSeqAddDto);

    void deleteQuiz(Long id);

    void deleteQuestion(Long id);

    List<Quiz> getUserQuizes(Long id);

    List<Quiz> getApprovedQuizes();

    List<Quiz> getApprovedUserQuizes(Long user_id);

    List<Quiz> getUserFavoritesQuizes(Long user_id);

    List<Quiz> getSuggestion(Long user_id);

    List<Quiz> getCompletedQuizes(Long user_id);

    List<Quiz> getCategoryQuizes(String category);

    List<Quiz> searchQuizes(String[] categories, String searchstring, String dateFrom, String dateTo, String user);
	
	List<Quiz> searchQuizes(String searchstring);

    List<Question> getQuizQuestion(Long id);

    Question getQuestion(Long id);

    Long addQuiz(QuizAddDto quizAddDto);

    List<Quiz> getAllQuizes();

    Quiz getQuiz(Long id);

    void aproveQuiz(Quiz quiz);

    List<Quiz> getCreated();

    List<SessionStatsDto>getTopStats(Long quizId);

    void updateQuiz(QuizUpdateDto dto) throws QuizNotFoundException;

}
