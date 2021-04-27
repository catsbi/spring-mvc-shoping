package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "/basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(Model model, @PathVariable Long itemId) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "/basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /**
     * RequestParam을 이용한 매핑 방식 (Legacy)
     *
     * @param item
     * @param model
     * @return
     */
    /*@PostMapping("/add")
    public String saveLegacy(@RequestParam String itemName,
                             @RequestParam int price,
                             @RequestParam Integer quantity,
                             Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        Item save = itemRepository.save(item);
        model.addAttribute("item", save);
        return "basic/item";
    }*/

    /*@PostMapping("/add")
    public String saveV2(@ModelAttribute("item") Item item, Model model) {
        Item save = itemRepository.save(item);
        //model.addAttribute("item", save); //생략 가능
        return "basic/item";
    }*/

    /*@PostMapping("/add")
    public String saveV3(@ModelAttribute Item item, Model model) {
        itemRepository.save(item);
        //model.addAttribute("item", save);
        return "basic/item";
    }*/

    /*@PostMapping("/add")
    public String saveV4(Item item) {
        itemRepository.save(item);
        //model.addAttribute("item", save);
        return "basic/item";
    }*/

    @PostMapping("/add")
    public String saveV5(Item item) {
        itemRepository.save(item);
        //model.addAttribute("item", save);
        return "redirect:/basic/items/" + item.getId();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect: /basic/items/{itemId}";
    }


    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
