package co.edu.usbcali.cart.repository;

import co.edu.usbcali.cart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findByNameContainsIgnoreCase(String name);

    @Query(value = "SELECT pro_id, name, price, detail, image, enable FROM public.product order by pro_id desc"
            , nativeQuery = true)
    List<Product> findAllOrOrderByProId();
}
