package uz.online.mahsulotlar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;
import uz.online.mahsulotlar.Entity.Product;
import uz.online.mahsulotlar.Entity.ProductAmountUser;
import uz.online.mahsulotlar.Entity.Users;
import uz.online.mahsulotlar.Entity.model.AddProductDto;
import uz.online.mahsulotlar.Entity.model.ApiResponse;
import uz.online.mahsulotlar.Entity.model.SendingProduct;
import uz.online.mahsulotlar.Repository.ProductAmountRepo;
import uz.online.mahsulotlar.Repository.ProductRepository;
import uz.online.mahsulotlar.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IncomeService incomeService;
    @Autowired
    OutcomeService outcomeService;
    @Autowired
    ProductAmountRepo productAmountRepo;

    public List<Product> getCardsInformation() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public ApiResponse addProductOwn(AddProductDto addProductDto) {
        Optional<Product> byId = productRepository.findById(addProductDto.getProductId());
        Product product = byId.get();
        ProductAmountUser productAmountUser = new ProductAmountUser();
        productAmountUser.setProduct(product);
        productAmountUser.setAmount(addProductDto.getAmount());
        productAmountUser.setUsername(addProductDto.getUsername());
        productAmountRepo.save(productAmountUser);
        return new ApiResponse("Product saqlandi", true);
    }

    public ApiResponse sendProduct(String username, SendingProduct sendingProduct) {
        Optional<Users> optionalUser = userRepository.findByEmail(username);
        Users user = optionalUser.get();

        Optional<Users> optionalUserTo = userRepository.findByEmail(sendingProduct.getToUSer());
        if (!optionalUserTo.isPresent()) {
            return new ApiResponse("Mahsulot jo`natilayotgan user topilmadi", false);
        }


        Optional<ProductAmountUser> byUsername = productAmountRepo.findByUsernameAndProduct(username, productRepository.findById(sendingProduct.getProduct_id()).get());
        if (!byUsername.isPresent()) {
            return new ApiResponse("Sizda mahsulot yo`q", false);
        }
        ProductAmountUser productAmountUser = byUsername.get();

        byUsername = productAmountRepo.findByUsernameAndProduct(sendingProduct.getToUSer(), productRepository.findById(sendingProduct.getProduct_id()).get());
        if (productAmountUser.getAmount() - sendingProduct.getProductAmount() < 0) {
            return new ApiResponse("Sizda mahsulot yetarli emas", false);
        }
        productAmountUser.setAmount(productAmountUser.getAmount() - sendingProduct.getProductAmount());

        productAmountRepo.save(productAmountUser);

        ProductAmountUser newProductAmount = new ProductAmountUser();
        newProductAmount.setProduct(productRepository.findById(sendingProduct.getProduct_id()).get());
        newProductAmount.setAmount(sendingProduct.getProductAmount());
        newProductAmount.setUsername(sendingProduct.getToUSer());
        productAmountRepo.save(newProductAmount);


        incomeService.addIncome(username, sendingProduct);
        outcomeService.addOutcome(username, sendingProduct);

        return new ApiResponse();
    }


}