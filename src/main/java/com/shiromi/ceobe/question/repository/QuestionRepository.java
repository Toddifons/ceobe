package com.shiromi.ceobe.question.repository;
import com.shiromi.ceobe.question.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {

    Page<QuestionEntity> findByQuestionTitleContaining(String search, PageRequest of);

    Page<QuestionEntity> findByQuestionContentsContaining(String search, PageRequest of);

    Page<QuestionEntity> findByQuestionNameContaining(String search, PageRequest of);
}