package com.shiromi.ceobe.order.service;

import com.shiromi.ceobe.cart.entity.CartEntity;
import com.shiromi.ceobe.cartItem.dto.CartItemDTO;
import com.shiromi.ceobe.cartItem.entity.CartItemEntity;
import com.shiromi.ceobe.item.entity.ItemEntity;
import com.shiromi.ceobe.item.repository.ItemRepository;
import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.ceobe.order.dto.OrderDTO;
import com.shiromi.ceobe.order.entity.OrderEntity;
import com.shiromi.ceobe.order.repository.OrderRepository;
import com.shiromi.ceobe.orderItem.repository.OrderItemRepository;
import com.shiromi.ceobe.member.repository.MemberRepository;
import com.shiromi.ceobe.orderItem.entity.OrderItemEntity;
import com.shiromi.ceobe.orderReady.entity.OrderReadyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderReadyRepository orderReadyRepository;
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

    @Transactional
    public List<OrderDTO> findAll(String userId) {
        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
        List<OrderEntity> orderEntityList = orderRepository.findByMemberEntity(memberEntity, Sort.by(Sort.Direction.DESC, "id"));
        log.info("orderEntityList = {} ",orderEntityList);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(orderEntity.getId());
            orderDTO.setReview(orderEntity.getReview());
            orderDTO.setOrderStatus(orderEntity.getOrderStatus());
            orderDTO.setOrderName(orderEntity.getOrderName());
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }
    //
    @Transactional
    public Page<OrderDTO> findListAll(Pageable pageable, String sort) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = pageable.getPageSize();
        Page<OrderEntity> orderEntityList = orderRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, sort)));
        Page<OrderDTO> orderDTOList = orderEntityList.map(OrderDTO::toOrderDTO);
        return orderDTOList;
    }
    //유저 아이디로 카트 찾기
    @Transactional
    public List<CartItemDTO> findCartById(String userId) {
        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
        CartEntity cartEntity = cartRepository.findByMemberEntity(memberEntity).get();
        List<CartItemEntity> cartEntityList = cartItemRepository.findByCartEntity(cartEntity);
        log.info("cartEntityList = {} ", cartEntityList);
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartEntityList) {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartItemEntity.getId());
            cartItemDTO.setItemName(cartItemEntity.getItemEntity().getItemName());
            cartItemDTO.setItemPrice(cartItemEntity.getItemEntity().getItemPrice());
            cartItemDTO.setItemCount(cartItemEntity.getItemEntity().getItemCount());
            cartItemDTO.setCartCount(cartItemEntity.getCartCount());
            cartItemDTO.setItemImage(cartItemEntity.getItemEntity().getItemFileEntityList().get(0).getStoredFileNameItem());
            cartItemDTOList.add(cartItemDTO);
        }
        return cartItemDTOList;
    }
    //업데이트
    public void update(Long id, String status) {
        OrderEntity orderEntity=orderRepository.findById(id).get();
        orderEntity.setOrderStatus(status);
        orderRepository.save(orderEntity);
    }

    public String checkOrder(String userId, JSONArray itemDTOList) throws JSONException {
        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
        if (orderReadyRepository.findByMemberEntity(memberEntity).isEmpty()) {
            for (int i = 0; i < itemDTOList.length(); i++) {
                OrderReadyEntity orderReadyEntity = new OrderReadyEntity();
                orderReadyEntity.setMemberEntity(memberEntity);
                orderReadyEntity.setOrderName(itemDTOList.getJSONObject(i).getString("itemName"));
                orderReadyEntity.setOrderPrice(itemDTOList.getJSONObject(i).getInt("itemPrice"));
                orderReadyEntity.setCartCount(itemDTOList.getJSONObject(i).getInt("cartCount"));
                orderReadyEntity.setItemPriceTotal(itemDTOList.getJSONObject(i).getInt("itemPriceTotal"));
                orderReadyEntity.setItemImage(itemDTOList.getJSONObject(i).getString("itemImage"));
                orderReadyEntity.setCartItemId(itemDTOList.getJSONObject(i).getLong("cartItemId"));
                orderReadyRepository.save(orderReadyEntity);
            }
        } else {
            return "no";
        }

        return "ok";
    }



}
