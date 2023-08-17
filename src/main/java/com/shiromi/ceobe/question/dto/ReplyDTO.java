package com.shiromi.ceobe.question.dto;

import com.shiromi.ceobe.question.entity.ReplyEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyDTO {

        private Long questionAnswerId;
        private String replyContents;
        private String replyName;
        private LocalDateTime replyDate;
        private Long questionId;
        private Long memberId;

        public static ReplyDTO toReplyDTO(ReplyEntity replyEntity) {
            ReplyDTO replyDTO = new ReplyDTO();
            replyDTO.setQuestionAnswerId(replyEntity.getQuestionAnswerId());
            replyDTO.setReplyContents(replyEntity.getReplyContents());
            replyDTO.setReplyName(replyEntity.getReplyName());
            replyDTO.setReplyDate(replyEntity.getCreatedTime());
            return replyDTO;
        }
}
