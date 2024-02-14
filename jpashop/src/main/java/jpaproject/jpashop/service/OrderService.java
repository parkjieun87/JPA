package jpaproject.jpashop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpaproject.jpashop.model.Delivery;
import jpaproject.jpashop.model.DeliveryStatus;
import jpaproject.jpashop.model.Member;
import jpaproject.jpashop.model.Order;
import jpaproject.jpashop.model.OrderItem;
import jpaproject.jpashop.model.item.Item;
import jpaproject.jpashop.repo.ItemRepo;
import jpaproject.jpashop.repo.MemberRepo;
import jpaproject.jpashop.repo.OrderRepo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepo orderRepo;
	private final MemberRepo memberRepo;
	private final ItemRepo itemRepo;
	
	
	//주문
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		
		//엔티티 조회
		Member member = memberRepo.findOne(memberId);
		Item item = itemRepo.findOne(itemId);
		
		//배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		delivery.setStatus(DeliveryStatus.READY);
		
		//주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		//주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//주문 저장
		orderRepo.save(order);
		return order.getId();		
		
	}
	
	//주문 취소
	@Transactional
	public void cancleOrder(Long orderId) {
		Order order = orderRepo.findOne(orderId);
		order.cancle();
	}
	
	
	//검색
	
	
}
