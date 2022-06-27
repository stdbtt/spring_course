package stdbtt.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdbtt.springcourse.models.Item;
import stdbtt.springcourse.models.Person;
import stdbtt.springcourse.repositories.ItemsRepository;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findByName(String name){
        return itemsRepository.findByName(name);
    }

    public List<Item> findByOwner(Person person){
        return itemsRepository.findByOwner(person);
    }


}
