package co.edu.usbcali.cart.entity;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Entity
@ToString
@Table(name = "customer", schema = "public")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "email", unique = true, nullable = false)
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 1)
    @Column(name = "enable", nullable = false)
    private String enable;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 255)
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 255)
    @Column(name = "token", nullable = false)
    private String token;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>(0);
    
}
