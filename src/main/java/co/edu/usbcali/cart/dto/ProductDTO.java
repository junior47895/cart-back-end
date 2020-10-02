package co.edu.usbcali.cart.dto;

import lombok.*;

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
public class ProductDTO {

    @NotNull
    private String proId;
    @NotNull
    @NotEmpty
    @Size(min=3,max = 255)
    private String detail;
    @NotNull
    @NotEmpty
    @Size(min=1,max = 1)
    private String enable;
    @NotNull
    @NotEmpty
    @Size(min=3,max = 30)
    private String image;
    @NotNull
    @NotEmpty
    @Size(min=5,max = 30)
    private String name;
    @NotNull
    private Long price;

}
