package com.example.shoppingList.repositry;

import com.example.shoppingList.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
