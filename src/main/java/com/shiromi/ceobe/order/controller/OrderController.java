package com.shiromi.ceobe.order.controller;

import com.shiromi.ceobe.item.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class OrderController {

    //주문상세페이지 이동
    @GetMapping("/order/save")
    public String saveForm(@ModelAttribute ItemDTO itemDTO, Model model) {
        System.out.println("itemDTO = " + itemDTO + ", model = " + model);
        model.addAttribute("item", itemDTO);
        return "orderPages/orderSave";
    }
}
