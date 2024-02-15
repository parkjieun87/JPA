package jpaproject.jpashop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jpaproject.jpashop.exception.NotEnoughStockException;
import jpaproject.jpashop.model.Address;
import jpaproject.jpashop.model.Member;
import jpaproject.jpashop.model.Order;
import jpaproject.jpashop.model.OrderStatus;
import jpaproject.jpashop.model.item.Book;
import jpaproject.jpashop.model.item.Item;
import jpaproject.jpashop.repo.OrderRepo;
import jpaproject.jpashop.service.OrderService;

@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired EntityManager em;
	@Autowired OrderService orderService;
	@Autowired OrderRepo orderRepo;
	
	
	@Test
	public void 상품주문()throws Exception {
		//Given
		 Member member = createMember();
		 Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
		 int orderCount = 2;
		
		
		//when
		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
		
		//then
		Order getOrder = orderRepo.findOne(orderId);

		assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("주문한 상품 종류 수가 정확해야 한다.",1, getOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2, getOrder.getTotalPrice());
		assertEquals("주문 수량만큼 재고가 줄어야 한다.",8, item.getStockQuantity());
				 
	}

	@Test
	public void 주문취소()throws Exception {
		//Given
		 Member member = createMember();
		 Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
		 int orderCount = 2;
		 
		 Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
		 //When
		 orderService.cancleOrder(orderId);
		 //Then
		 Order getOrder = orderRepo.findOne(orderId);
		 assertEquals("주문 취소시 상태는 CANCEL 이다.",OrderStatus.CANCEL, 
		getOrder.getStatus());
		 assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, 
		item.getStockQuantity());
		}

	
	@Test
	public void 재고수량초과()throws Exception {
		//Given
	    Member member = createMember();
	    Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
	    int orderCount = 11; //재고보다 많은 수량
	    
	    //When & Then
	    assertThrows(NotEnoughStockException.class, () -> {
	        orderService.order(member.getId(), item.getId(), orderCount);
	    });
	}
	
	
	private Member createMember() {
		 Member member = new Member();
		 member.setName("회원1");
		 member.setAddress(new Address("서울", "강가", "123-123"));
		 em.persist(member);
		 return member;
		 }
 	private Book createBook(String name, int price, int stockQuantity) {
		 Book book = new Book();
		 book.setName(name);
		 book.setStockQuantity(stockQuantity);
		 book.setPrice(price);
		 em.persist(book);
		 return book;
		 }
	
}
