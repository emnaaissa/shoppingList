package com.example.shoppingList.service;

import com.example.shoppingList.model.Item;
import com.example.shoppingList.repositry.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(int id, Item updatedItem) {
        return itemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setName(updatedItem.getName());
                    existingItem.setQuantity(updatedItem.getQuantity());
                    existingItem.setChecked(updatedItem.isChecked());
                    return itemRepository.save(existingItem);
                })
                .orElseThrow(() -> new RuntimeException("Item not found with id " + id));
    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }
}
