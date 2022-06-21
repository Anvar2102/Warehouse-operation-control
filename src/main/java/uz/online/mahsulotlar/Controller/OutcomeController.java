package uz.online.mahsulotlar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.online.mahsulotlar.Entity.Outcome;
import uz.online.mahsulotlar.Security.JwtProvider;
import uz.online.mahsulotlar.Service.OutcomeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/outcome")
public class OutcomeController {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    OutcomeService outcomeService;

    @GetMapping
    public ResponseEntity getOutcomes(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        List<Outcome> outcomeList = outcomeService.getOutcomes(username);
        return ResponseEntity.ok(outcomeList);
    }
}
