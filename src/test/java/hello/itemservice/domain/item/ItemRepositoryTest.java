package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ItemRepository 관련 기능 테스트")
class ItemRepositoryTest {
    private ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterLogic() {
        itemRepository.clearStore();
    }

    @Test
    void saveTest() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item foundItem = itemRepository.findById(item.getId());
        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    void findAllTest() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items).hasSize(2);
        assertThat(items).contains(itemA, itemB);
    }

    @Test
    void updateTest() {
        //given
        Item itemA = new Item("itemA", 10000, 10);

        itemRepository.save(itemA);

        //when
        Item updateParam = new Item("itemB", 20000, 20);
        itemRepository.update(itemA.getId(), updateParam);

        //then
        Item foundItem = itemRepository.findById(itemA.getId());

        assertThat(foundItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(foundItem.getQuantity()).isEqualTo(updateParam.getQuantity());
        assertThat(foundItem.getPrice()).isEqualTo(updateParam.getPrice());
    }

}
