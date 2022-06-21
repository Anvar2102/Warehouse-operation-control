package uz.online.mahsulotlar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.online.mahsulotlar.Entity.Product;
import uz.online.mahsulotlar.Entity.model.ApiResponse;
import uz.online.mahsulotlar.Repository.ProductRepository;

import java.util.Date;

@RestController
@RequestMapping
public class AddNewProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/addProduct")
    public ApiResponse add(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setColor(product.getColor());
        newProduct.setPrice(product.getPrice());
        newProduct.setActive(true);
        productRepository.save(newProduct);
        return new ApiResponse("saqlandi", true);
    }
}
