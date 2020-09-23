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
public class CustomerDTO {

    private String email;
    private String address;
    private String enable;
    private String name;
    private String phone;
    private String token;
}
