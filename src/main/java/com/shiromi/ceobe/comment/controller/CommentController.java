package com.shiromi.ceobe.comment.controller;

import com.shiromi.ceobe.comment.service.CommentService;
import com.shiromi.ceobe.comment.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //리뷰 작성(모달창)
    @GetMapping("/comment/save2")
    public @ResponseBody String commentSave(@ModelAttribute CommentDTO commentDTO){
        Long id = commentService.save(commentDTO);
        return "성공";
    }

    //리뷰 수정 (팝업창)
    @GetMapping("/comment/update")
    public String commentUpdateForm(@RequestParam("commentId")Long id, Model model){
        CommentDTO commentDTO = commentService.update(id);
        model.addAttribute("commentDTO", commentDTO);
        return "popup/popup";
    }

    @PostMapping("/comment/update")
    public String commentUpdate(@ModelAttribute CommentDTO commentDTO,Model model){
        System.out.println("코멘트 컨트롤러 넘오옴");
        model.addAttribute("commentDTO", commentDTO);
        commentService.update2(commentDTO);
        return "/popup/popupClose";
    }


    @GetMapping("/comment/delete")
    public String commentDelete(@RequestParam("commentId")Long id,@RequestParam("itemId")Long itemId){
        commentService.delete(id);
        return "redirect:/item/?itemId="+itemId;
    }


}
