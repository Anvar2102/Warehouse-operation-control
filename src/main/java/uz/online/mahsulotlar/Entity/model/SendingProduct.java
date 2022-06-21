package uz.online.mahsulotlar.Entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SendingProduct {

    private Integer product_id;
    private String toUSer;//kmga junatilishi
    private Integer productAmount;//qancha hajmdagi product
}
