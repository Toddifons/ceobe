package com.shiromi.ceobe.reply.repository;

import com.shiromi.ceobe.question.entity.QuestionEntity;
import com.shiromi.ceobe.reply.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity,Long> {
    List<ReplyEntity> findByQuestionEntity(QuestionEntity question);

}