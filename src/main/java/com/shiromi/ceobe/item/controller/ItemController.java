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


    @GetMapping("/item/save")
    public String saveForm() {
        return "itemPages/itemSave";
    }

    @PostMapping("/item/save")
    public String save(@ModelAttribute ItemDTO itemDTO) throws IOException {
        itemService.save(itemDTO);
        return "redirect:/item/main";
    }
    @GetMapping("/item/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        ItemDTO itemDTO = itemService.findById(id);
        model.addAttribute("item",itemDTO);
        return "itemPages/itemUpdate";
    }
}
