package co.edu.usbcali.cart.domain;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
@Table(name = "product", schema = "public")
public class Product implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pro_id", unique = true, nullable = false)
    private String proId;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "enable", nullable = false)
    private String enable;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ShoppingProduct> shoppingProducts = new ArrayList<ShoppingProduct>(0);

}
