package uz.online.mahsulotlar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.online.mahsulotlar.Entity.Income;
import uz.online.mahsulotlar.Security.JwtProvider;
import uz.online.mahsulotlar.Service.IncomeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    IncomeService incomeService;

    @GetMapping
    public ResponseEntity<List<Income>> getIncomes(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        List<Income> incomeList = incomeService.getIncomes(username);
        return ResponseEntity.ok(incomeList);
    }
}
