package com.shiromi.ceobe.order.controller;

import com.shiromi.ceobe.item.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    //주문상세페이지 이동
    @GetMapping("/order/save")
    public String saveForm(@ModelAttribute ItemDTO itemDTO, Model model) {
        log.info("itemDTO = {}, model = {}",itemDTO, model);
        model.addAttribute("item", itemDTO);
        return "orderPages/orderSave";
    }
}
