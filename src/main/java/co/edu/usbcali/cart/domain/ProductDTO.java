package co.edu.usbcali.cart.domain;

import lombok.*;

/**
 * @author Luis Carlos Cabal Rojas
 * @version 1.0
 * @since 2020-09-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {

    private String proId;
    private String detail;
    private String enable;
    private String image;
    private String name;
    private Long price;

}
