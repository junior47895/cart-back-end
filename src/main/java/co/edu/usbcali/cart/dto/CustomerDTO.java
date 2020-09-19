package co.edu.usbcali.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private String email;
    private String address;
    private String enable;
    private String name;
    private String phone;
    private String token;
}
