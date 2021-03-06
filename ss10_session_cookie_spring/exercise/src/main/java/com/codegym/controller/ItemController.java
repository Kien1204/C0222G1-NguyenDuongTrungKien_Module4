package com.codegym.controller;

import com.codegym.model.Item;
import com.codegym.service.impl.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Controller
@SessionAttributes("cart")
public class ItemController {
    @Autowired
    ItemService itemService;

    @ModelAttribute("cart")
    public Map<Item, Integer> setUpCart() {
        return new LinkedHashMap<>();
    }

    @GetMapping("/")
    public String showHomePage(Model model){
        model.addAttribute("items", itemService.findAll());
        return "index";
    }
    @GetMapping("/addCart")
    public String addCart(@ModelAttribute("cart") Map<Item, Integer> cart, @RequestParam int id, @RequestParam int quantity){
        Item item = itemService.findById(id);
        if (cart.containsKey(item)){
            cart.replace(item, cart.get(item), cart.get(item) + quantity);
        } else {
            cart.put(item, quantity);
        }
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String showCart(@ModelAttribute("cart") Map<Item, Integer> cart, Model model, @RequestParam(defaultValue = "0") int id){
        int amount = 0;
        Set<Item> keySet = cart.keySet();
        for (Item key : keySet){
            amount += key.getPrice()*cart.get(key);//tính tổng số tiền
        }
        model.addAttribute("id", id);
        model.addAttribute("amount", amount);
        return "cart";
    }

    @GetMapping(value = "/updateCart")
    public String updateCart(@ModelAttribute("cart") Map<Item, Integer> cart, @RequestParam int id, @RequestParam int quantity){
        Item item = itemService.findById(id);
        cart.replace(item, cart.get(item), quantity);//replay trả về chuỗi mới
        return "redirect:/cart";
    }

    @GetMapping("/delete")
    public String deleteCart(@ModelAttribute("cart") Map<Item, Integer> cart, @RequestParam(defaultValue = "0") int id){
        if (id == 0){
            cart.clear();
        } else {
            Item item = itemService.findById(id);
            if (item != null){
                cart.remove(item);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/view")
    public String viewItem(@RequestParam int id, Model model){
        model.addAttribute("item", itemService.findById(id));
        return "view";
    }
}
