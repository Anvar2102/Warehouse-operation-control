package uz.online.mahsulotlar.Entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductDto {
    private String username;

    private Integer productId;

    private Integer amount;
}
