package co.edu.usbcali.cart.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @Size(min=3,max = 255)
    private String address;
    @NotNull
    @NotEmpty
    @Size(min=1,max = 1)
    private String enable;
    @NotNull
    @NotEmpty
    @Size(min=4,max = 255)
    private String name;
    @NotNull
    @NotEmpty
    @Size(min=4,max = 255)
    private String phone;
    @NotNull
    @NotEmpty
    @Size(min=4,max = 255)
    private String token;
}
