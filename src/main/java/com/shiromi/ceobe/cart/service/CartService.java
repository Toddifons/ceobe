package com.shiromi.ceobe.cart.service;

import com.shiromi.ceobe.cart.dto.CartDTO;
import com.shiromi.ceobe.cart.entity.CartEntity;
import com.shiromi.ceobe.cart.repository.CartRepository;
import com.shiromi.ceobe.cartItem.dto.CartItemDTO;
import com.shiromi.ceobe.cartItem.entity.CartItemEntity;
import com.shiromi.ceobe.cartItem.repository.CartItemRepository;
import com.shiromi.ceobe.item.dto.ItemDTO;
import com.shiromi.ceobe.item.entity.ItemEntity;
import com.shiromi.ceobe.item.repository.ItemRepository;
import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.ceobe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final MemberRepository memberRepository;

    private final CartItemRepository cartItemRepository;

    private final ItemRepository itemRepository;
    @Transactional
    public String save(ItemDTO itemDTO, MemberEntity memberEntity) {

        Optional<CartEntity>cartEntity = cartRepository.findByMemberEntity(memberEntity);

        if (cartEntity.isPresent()) {

            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setCartName(itemDTO.getItemName());
            cartItemEntity.setCartCount(itemDTO.getCartCount());
            ItemEntity itemEntity =itemRepository.findById(itemDTO.getId()).get();
            cartItemEntity.setItemEntity(itemEntity);
            cartItemRepository.save(cartItemEntity);
            return memberEntity.getUserId();

        }else {
            CartEntity cartEntity1 = new CartEntity();
            cartEntity1.setMemberEntity(memberEntity);
            cartRepository.save(cartEntity1);

            CartItemEntity cartItemEntity = createNewEntity(itemDTO);

            ItemEntity itemEntity =itemRepository.findById(itemDTO.getId()).get();
            cartItemEntity.setItemEntity(itemEntity);

            cartItemRepository.save(cartItemEntity);
        }
        return null;
    }

    private CartItemEntity createNewEntity(ItemDTO itemDTO){
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCartName(itemDTO.getItemName());
        cartItemEntity.setCartCount(itemDTO.getItemCount());
        cartItemEntity.setCartCount(itemDTO.getCartCount());
        return cartItemEntity;
    }

    @Transactional
    public List<CartItemDTO> findAll(String userId) {
        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
        Optional<CartEntity>cartEntity = cartRepository.findByMemberEntity(memberEntity);
        if (cartEntity.isPresent()) {
            CartEntity cartEntity1 = cartEntity.get();
            List<CartItemEntity>cartItemEntityList = cartItemRepository.findByCartEntity(cartEntity1);
            List<CartItemDTO> cartItemDTOList = new ArrayList<>();
            for (CartItemEntity cartItemEntity : cartItemEntityList) {
                CartItemDTO cartItemDTO = new CartItemDTO();
                cartItemDTO.setId(cartItemEntity.getId());
                cartItemDTO.setCartCount(cartItemEntity.getCartCount());
                cartItemDTO.setItemName(cartItemEntity.getItemEntity().getItemName());
                cartItemDTO.setItemPrice(cartItemEntity.getItemEntity().getItemPrice());
                cartItemDTO.setItemImage(cartItemEntity.getItemEntity().getItemFileEntityList().get(0).getStoredFileNameItem());
                cartItemDTOList.add(cartItemDTO);
            }
            return cartItemDTOList;

        }else {
            return null;
        }
    }
    @Transactional
    public CartItemDTO findById(Long id) {
        Optional<CartItemEntity> cartEntityOptional = cartItemRepository.findById(id);
        if(cartEntityOptional.isPresent()){
            CartItemEntity cartEntity = cartEntityOptional.get();
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartEntity.getId());
            cartItemDTO.setCartCount(cartEntity.getCartCount());
            cartItemDTO.setItemName(cartEntity.getItemEntity().getItemName());
            cartItemDTO.setItemPrice(cartEntity.getItemEntity().getItemPrice());
            cartItemDTO.setItemImage(cartEntity.getItemEntity().getItemFileEntityList().get(0).getStoredFileNameItem());
            return cartItemDTO;
        }else{
            return null;
        }
    }
    @Transactional
    public List<CartItemDTO> delete(Long id) {
        cartItemRepository.deleteById(id);
        return null;
    }
    @Transactional
    public void update(CartDTO cartDTO) {
        CartItemEntity cartItemEntity = cartItemRepository.findById(cartDTO.getId()).get();
        cartItemEntity.setCartCount(cartDTO.getCartCount());
        cartItemRepository.save(cartItemEntity);
    }
}