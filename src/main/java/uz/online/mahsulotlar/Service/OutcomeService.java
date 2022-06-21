package uz.online.mahsulotlar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.online.mahsulotlar.Entity.Income;
import uz.online.mahsulotlar.Entity.Outcome;
import uz.online.mahsulotlar.Entity.model.SendingProduct;
import uz.online.mahsulotlar.Repository.IncomeRepository;
import uz.online.mahsulotlar.Repository.OutcomeRepository;
import uz.online.mahsulotlar.Repository.ProductRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;
    @Autowired
    ProductRepository productRepository;

    public List<Outcome> getOutcomes(String fromUsername) {
        List<Outcome> all = outcomeRepository.findAll();
        List<Outcome> outcomeList = new ArrayList<>();
        for (Outcome outcome : all) {
            if (outcome.getFromUser().equals(fromUsername)) {
                outcomeList.add(outcome);
            }
        }
        return outcomeList;
    }

    public void addOutcome(String fromUsername, SendingProduct sendingProduct) {
        Outcome outcome=new Outcome();
        outcome.setFromUser(fromUsername);
        outcome.setToUser(sendingProduct.getToUSer());
        outcome.setAmount(sendingProduct.getProductAmount());
        outcome.setProduct(productRepository.findById(sendingProduct.getProduct_id()).get());
        outcome.setDate(new Date());
    }

}
