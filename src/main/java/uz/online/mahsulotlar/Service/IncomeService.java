package uz.online.mahsulotlar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.online.mahsulotlar.Entity.Income;
import uz.online.mahsulotlar.Entity.Product;
import uz.online.mahsulotlar.Entity.model.SendingProduct;
import uz.online.mahsulotlar.Repository.IncomeRepository;
import uz.online.mahsulotlar.Repository.ProductRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    ProductRepository productRepository;

    public List<Income> getIncomes(String username) {
        List<Income> all = incomeRepository.findAll();
        List<Income> incomeList = new ArrayList<>();
        for (Income income : all) {
            if (income.getToUser().equals(username)) {
                incomeList.add(income);
            }
        }
        return incomeList;
    }

    public void addIncome(String fromUsername, SendingProduct sendingProduct) {
        Income income=new Income();
        income.setFromUser(fromUsername);
        income.setToUser(sendingProduct.getToUSer());
        income.setAmount(sendingProduct.getProductAmount());
        income.setProduct(productRepository.findById(sendingProduct.getProduct_id()).get());
        income.setDate(new Date());
    }

}
