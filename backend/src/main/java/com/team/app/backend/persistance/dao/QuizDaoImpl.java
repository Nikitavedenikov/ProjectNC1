package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.dao.mappers.QuizRowMapper;

import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.persistance.model.QuizStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class QuizDaoImpl implements QuizDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private QuizRowMapper userRowMapper=new QuizRowMapper();

    public QuizDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Quiz get(Long id) {
        return jdbcTemplate.query(
                "select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QC.name as cat_name from quiz Q INNER JOIN quiz_category QC ON QC.id = Q.status_id where id = ?",
                new Object[]{id},
                resultSet -> {
                    if (resultSet.next()) {
                        return new Quiz(
                                (long) resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getDate("date"),
                                resultSet.getString("description"),
                                resultSet.getBytes("image"),
                                new QuizStatus((long)resultSet.getInt("role_id"),resultSet.getString("cat_name")),
                                (long) resultSet.getInt("user_id")
                        );
                    } else {
                        return null;
                    }
                }
        );
    }

    @Override
    public List<Quiz> getByUserId(Long id) {
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QC.name as cat_name from quiz Q INNER JOIN quiz_category QC ON QC.id = Q.status_id  where user_id = ? "
                ,new Object[] { id },new QuizRowMapper());
    }

    @Override
    public List<Quiz> getAll() {
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QC.name as cat_name from quiz Q INNER JOIN quiz_category QC ON QC.id = Q.status_id"
                ,new QuizRowMapper());
    }


    @Override
    public void save(Quiz quiz) {
        jdbcTemplate.update(
                "INSERT INTO quiz (title,date,desription,image,status_id,user_id VALUES (?, ?, ?, ?, ?, ?)",
                quiz.getTitle(),
                quiz.getDate(),
                quiz.getDiscription(),
                quiz.getImage(),
                quiz.getStatus().getId(),
                quiz.getUser()
            );
    }

    @Override
    public void update(Quiz quiz) {
        jdbcTemplate.update(
                "UPDATE quiz set title = ?, date = ?, desription = ?, image = ?, status_id = ?, user_id = ?  where id = ?",
                quiz.getTitle(),
                quiz.getDate(),
                quiz.getDiscription(),
                quiz.getImage(),
                quiz.getStatus().getId(),
                quiz.getUser(),
                quiz.getId()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE from quiz where id = ?",
                id
        );
    }
}
