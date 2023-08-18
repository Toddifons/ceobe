package com.shiromi.ceobe.order.controller;

import com.shiromi.ceobe.item.dto.ItemDTO;
import com.shiromi.ceobe.order.dto.OrderDTO;
import com.shiromi.ceobe.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //주문상세페이지 이동
    @GetMapping("/order/save")
    public String saveForm(@ModelAttribute ItemDTO itemDTO, Model model) {
        log.info("itemDTO = {}, model = {}",itemDTO, model);
        model.addAttribute("item", itemDTO);
        return "orderPages/orderSave";
    }
    //주문하기
    @PostMapping("/order/save")
    public String save(@ModelAttribute OrderDTO orderDTO, Model model) {
        System.out.println("시작orderDTO = " + orderDTO + ", model = " + model);
        orderService.save(orderDTO);
        model.addAttribute("order", orderDTO);
        System.out.println("저장후orderDTO = " + orderDTO + ", model = " + model);
        return "redirect:/";
    }
}
