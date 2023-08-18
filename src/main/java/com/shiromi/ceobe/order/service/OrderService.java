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
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    //주문하기
    public void save(OrderDTO orderDTO) {

        log.info("save : {}", orderDTO);

        Optional<MemberEntity> memberEntity1 = memberRepository.findById(orderDTO.getMemberId());
        ItemEntity itemEntity1 = itemRepository.findById(orderDTO.getItemId()).get();
        itemEntity1.setItemSellCount(itemEntity1.getItemSellCount() + orderDTO.getOrderCount());

        log.info("orderCount : {}",orderDTO.getOrderCount());
        log.info("ItmeCount : {}",itemEntity1.getItemCount());

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
    //장바구니에서 주문하기
    @Transactional
    public void save2(JSONArray itemDTOList, String userId) throws JSONException {
        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
        memberEntity.setPostcode(itemDTOList.getJSONObject(0).getString("postcode"));
        memberEntity.setMemberAddress(itemDTOList.getJSONObject(0).getString("memberAddress"));
        memberEntity.setDetailAddress(itemDTOList.getJSONObject(0).getString("detailAddress"));
        memberEntity.setExtraAddress(itemDTOList.getJSONObject(0).getString("extraAddress"));
        memberRepository.save(memberEntity);

        for (int i = 0; i < itemDTOList.length(); i++) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            OrderEntity orderEntity = new OrderEntity();
            ItemEntity itemEntity = itemRepository.findByItemName(itemDTOList.getJSONObject(i).getString("itemName")).get();
            itemEntity.setItemSellCount(itemEntity.getItemSellCount() + itemDTOList.getJSONObject(i).getInt("cartCount"));
            itemEntity.setItemCount(itemEntity.getItemCount() - itemDTOList.getJSONObject(i).getInt("cartCount"));
            orderEntity.setOrderStatus("주문완료");
            orderEntity.setReview("리뷰작성");
            orderEntity.setMemberEntity(memberEntity);
            orderEntity.setOrderName(itemDTOList.getJSONObject(i).getString("itemName"));
            orderItemEntity.setOrderName(itemDTOList.getJSONObject(i).getString("itemName"));
            orderItemEntity.setOrderPrice(itemDTOList.getJSONObject(i).getInt("itemPrice"));
            orderItemEntity.setOrderCount(itemDTOList.getJSONObject(i).getInt("cartCount"));
            orderEntity=orderRepository.save(orderEntity);
            orderItemEntity.setOrderEntity(orderEntity);
            orderItemEntity.setItemEntity(itemEntity);
            orderItemRepository.save(orderItemEntity);
        }

        for (int i = 0; i < itemDTOList.length(); i++) {
            CartItemEntity cartItemEntity = cartItemRepository.findById(itemDTOList.getJSONObject(i).getLong("cartItemId")).get();
            cartItemRepository.delete(cartItemEntity);
        }
        orderReadyRepository.deleteByMemberEntity(memberEntity);

    }

}
