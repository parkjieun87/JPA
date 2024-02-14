package jpaproject.jpashop.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {
	
	@Id @GeneratedValue
	@Column(name="order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="delivery_id")
	private Delivery delivery;
	
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	private LocalDate orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;	
	
	//연관관계 메서드
	public void setMemeber(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
	
	//생성 메서드
		public static Order createOrder(Member member, Delivery delivery, OrderItem...orderItems) {
			Order order = new Order();
			order.setMemeber(member);
			order.setDelivery(delivery);
			for(OrderItem orderItem : orderItems) {
				order.addOrderItem(orderItem);
			}
			order.setStatus(OrderStatus.ORDER);
			order.setOrderDate(LocalDateTime.now().toLocalDate());
			return order;
		}
	
	//주문 취소
		public void cancle() {
			if(delivery.getStatus()== DeliveryStatus.COMP) {
				throw new IllegalStateException("이미 배송완료되서 취소가 불가능합니다.");
			}
			this.setStatus(OrderStatus.CANCEL);
			for(OrderItem orderItem : orderItems) {
				orderItem.cancle();
			}
		}
		
		//조회 로직
		public int getTotalPrice() {
			int totalPirce = 0;
			for(OrderItem orderItem : orderItems) {
				totalPirce += orderItem.getTotalPrice();
			}
			return totalPirce;
		}
}
