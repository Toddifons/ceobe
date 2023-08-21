package com.shiromi.ceobe.item.controller;

import com.shiromi.ceobe.item.dto.ItemDTO;
import com.shiromi.ceobe.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    //상품 메인
    @GetMapping("/item/main")
    public String findAll(@PageableDefault(page = 1,size = 5) Pageable pageable, Model model , @RequestParam(required = false , value = "sort", defaultValue = "id") String sort
            , @RequestParam(required = false , value = "search", defaultValue = "") String search,
                          @RequestParam(required = false , value = "category", defaultValue = "") String category){
        Page<ItemDTO> itemDTOList = itemService.findAll(pageable, sort, search , category);
        if(itemDTOList.getTotalElements() == 0){
            model.addAttribute("message","null");
        }
        model.addAttribute("itemList",itemDTOList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < itemDTOList.getTotalPages()) ? startPage + blockLimit - 1 : itemDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        //sort: 정렬 관련 정보
        model.addAttribute("sort", sort);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("search", search);
        model.addAttribute("category", category);
        return "itemPages/itemMain";
    }

    //상품 상세조회
    @GetMapping("/item/")
    public String findById(@RequestParam("itemId") Long itemId, Model model){
        System.out.println( "itemId = " + itemId);
        ItemDTO itemDTO = itemService.findById(itemId);
        model.addAttribute("item",itemDTO);
        return "itemPages/itemDetail";
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

    //주문확인에서 상품 이름으로 상세조회
    @GetMapping("/items")
    public String findById(@RequestParam("orderName") String orderName, Model model){
        System.out.println(" orderName = " + orderName + ", model = " + model);
        ItemDTO itemDTO = itemService.findByOrderName(orderName);
        model.addAttribute("item",itemDTO);
        return "itemPages/itemDetail";
    }

    //리팩토링 1순위
    //카테고리별로 띄우기
    @GetMapping("/item/category1")
    public String findSmall(@PageableDefault(page = 1,size = 5)Pageable pageable, Model model , @RequestParam(required = false , value = "sort", defaultValue = "id") String sort
            , @RequestParam(required = false , value = "search", defaultValue = "") String search,
                            @RequestParam(required = false , value = "category", defaultValue = "") String category){
        Page<ItemDTO> itemDTOList = itemService.findSmall(pageable, sort, search , category);
        if(itemDTOList.getTotalElements() == 0){
            model.addAttribute("message","null");
        }
        model.addAttribute("itemList",itemDTOList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < itemDTOList.getTotalPages()) ? startPage + blockLimit - 1 : itemDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sort", sort);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("search", search);
        model.addAttribute("category", category);
        return "itemPages/itemCtry1";
    }

    @GetMapping("/item/category2")
    public String findMedium(@PageableDefault(page = 1,size = 5)Pageable pageable, Model model , @RequestParam(required = false , value = "sort", defaultValue = "id") String sort
            , @RequestParam(required = false , value = "search", defaultValue = "") String search,
                             @RequestParam(required = false , value = "category", defaultValue = "") String category) {
        System.out.println("pageable = " + pageable + ", model = " + model + ", sort = " + sort + ", search = " + search + ", category = " + category);
        Page<ItemDTO> itemDTOList = itemService.findMedium(pageable, sort, search, category);
        if (itemDTOList.getTotalElements() == 0) {
            model.addAttribute("message", "null");
        }
        model.addAttribute("itemList", itemDTOList);
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < itemDTOList.getTotalPages()) ? startPage + blockLimit - 1 : itemDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sort", sort);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("search", search);
        model.addAttribute("category", category);
        return "itemPages/itemCtry2";
    }

    @GetMapping("/item/category3")
    public String findLarge(@PageableDefault(page = 1,size = 5)Pageable pageable, Model model , @RequestParam(required = false , value = "sort", defaultValue = "id") String sort
            , @RequestParam(required = false , value = "search", defaultValue = "") String search,
                            @RequestParam(required = false , value = "category", defaultValue = "") String category){
        Page<ItemDTO> itemDTOList = itemService.findLarge(pageable, sort, search , category);
        if(itemDTOList.getTotalElements() == 0){
            model.addAttribute("message","null");
        }
        model.addAttribute("itemList",itemDTOList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < itemDTOList.getTotalPages()) ? startPage + blockLimit - 1 : itemDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sort", sort);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("search", search);
        model.addAttribute("category", category);
        return "itemPages/itemCtry3";
    }

}
