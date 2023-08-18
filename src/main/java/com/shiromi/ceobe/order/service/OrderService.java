package com.shiromi.ceobe.order.service;

import com.shiromi.ceobe.item.entity.ItemEntity;
import com.shiromi.ceobe.item.repository.ItemRepository;
import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.ceobe.order.dto.OrderDTO;
import com.shiromi.ceobe.order.entity.OrderEntity;
import com.shiromi.ceobe.order.repository.OrderRepository;
import com.shiromi.ceobe.orderItem.repository.OrderItemRepository;
import com.shiromi.ceobe.member.repository.MemberRepository;
import com.shiromi.ceobe.orderItem.entity.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    public void save(OrderDTO orderDTO) {
        System.out.println("세이브넘어옴orderDTO = " + orderDTO);
        Optional<MemberEntity> memberEntity1 = memberRepository.findById(orderDTO.getMemberId());
        ItemEntity itemEntity1 = itemRepository.findById(orderDTO.getItemId()).get();
        itemEntity1.setItemSellCount(itemEntity1.getItemSellCount() + orderDTO.getOrderCount());
        System.out.println("구매수량"+orderDTO.getOrderCount());
        System.out.println("재고수량"+itemEntity1.getItemCount());
        itemEntity1.setItemCount(itemEntity1.getItemCount() - orderDTO.getOrderCount());
        itemRepository.save(itemEntity1);
        if (memberEntity1.isPresent()) {
            MemberEntity memberEntity2 = memberEntity1.get();
            memberEntity2.setMemberAddress(orderDTO.getMemberAddress());
            memberEntity2.setDetailAddress(orderDTO.getDetailAddress());
            memberEntity2.setExtraAddress(orderDTO.getExtraAddress());
            memberEntity2.setPostcode(orderDTO.getPostcode());
            memberRepository.save(memberEntity2);

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderStatus(orderDTO.getOrderStatus());
            orderEntity.setReview(orderDTO.getReview());
            orderEntity.setMemberEntity(memberEntity2);
            orderEntity.setOrderName(orderDTO.getOrderName());
            orderRepository.save(orderEntity);

            OrderItemEntity itemEntity = new OrderItemEntity();
            itemEntity.setOrderEntity(orderEntity);
            itemEntity.setItemEntity(itemEntity1);
            itemEntity.setOrderCount(orderDTO.getOrderCount());
            itemEntity.setOrderName(orderDTO.getOrderName());
            itemEntity.setOrderPrice(orderDTO.getOrderPrice());
            orderItemRepository.save(itemEntity);
        }
    }
}
