package codegym.controller;

import codegym.model.Product;
import codegym.repository.impl.ProductRepoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepoImpl productService = new ProductRepoImpl();

    @GetMapping("/")
    public String index(Model model) {
        List products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/product/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @RequestMapping("/product/save")
    public String save(Product product, RedirectAttributes redirect) {
        productService.save(product);
        redirect.addFlashAttribute("success", "Saved customer successfully!");
        return "redirect:/";
    }

    @RequestMapping("/product/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/edit";
    }

    @PostMapping("/product/update")
    public String update(Product product, RedirectAttributes redirect) {
        productService.update(product.getId(), product);
        redirect.addFlashAttribute("success", "Modified product successfully!");
        return "redirect:/";
    }

    @RequestMapping("/product/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        productService.remove(id);
        redirect.addFlashAttribute("success", "Deleted product");
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchByName(String name, Model model) {
        model.addAttribute("products", productService.search(name));
        return "index";
    }
}
