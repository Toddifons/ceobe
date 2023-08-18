package com.shiromi.ceobe.item.controller;

import com.shiromi.ceobe.item.dto.ItemDTO;
import com.shiromi.ceobe.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    //상품 저장 화면
    @GetMapping("/item/save")
    public String saveForm() {
        return "itemPages/itemSave";
    }
    //상품 저장처리
    @PostMapping("/item/save")
    public String save(@ModelAttribute ItemDTO itemDTO) throws IOException {
        itemService.save(itemDTO);
        return "redirect:/item/main";
    }
    //상품 수정 화면
    @GetMapping("/item/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        ItemDTO itemDTO = itemService.findById(id);
        model.addAttribute("item",itemDTO);
        return "itemPages/itemUpdate";
    }
    //상품 수정 처리
    @PostMapping("/item/update")
    public String update (@ModelAttribute ItemDTO itemDTO,Model model) throws IOException{
        System.out.println("itemDTO = " + itemDTO + ", model = " + model);
        itemService.update(itemDTO);
        ItemDTO itemDTO1 = itemService.findById(itemDTO.getId());
        model.addAttribute("board",itemDTO1);
        return "redirect:/item/main";
    }
    //상품 삭제
    @GetMapping("/item/delete/{id}")
    public String delete(@PathVariable Long id){
        itemService.delete(id);
        return"redirect:/item/main";
    }

}
