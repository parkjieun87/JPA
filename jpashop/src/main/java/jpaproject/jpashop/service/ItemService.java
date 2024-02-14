package jpaproject.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpaproject.jpashop.model.item.Item;
import jpaproject.jpashop.repo.ItemRepo;
import lombok.RequiredArgsConstructor;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepo itemRepo;
	
	@Transactional
	public void save(Item item) {
		itemRepo.save(item);
	}
	
	public List<Item> findAll(){
		return itemRepo.findAll();
	}
	
	public Item findOne(Long id) {
		return itemRepo.findOne(id);
	}
	
	
}
