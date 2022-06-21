package uz.online.mahsulotlar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.online.mahsulotlar.Entity.model.AddProductDto;
import uz.online.mahsulotlar.Entity.model.ApiResponse;
import uz.online.mahsulotlar.Entity.model.SendingProduct;
import uz.online.mahsulotlar.Security.JwtProvider;
import uz.online.mahsulotlar.Service.ProductService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping
public class SendProduct {

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ApiResponse addProuct(@RequestBody AddProductDto addProductDto){
        ApiResponse apiResponse = productService.addProductOwn(addProductDto);
        return apiResponse;
    }

    @PostMapping("/send")
    public ResponseEntity transfer(HttpServletRequest request, @RequestBody SendingProduct sendingProduct) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);


        ApiResponse apiResponse = productService.sendProduct(username, sendingProduct);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

    }

}
