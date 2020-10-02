package co.edu.usbcali.cart.service;

import co.edu.usbcali.cart.domain.Customer;
import co.edu.usbcali.cart.domain.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    //Llave del Customer
    private final static String PRO_ID = "PRUEBA01";
    private final static Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

    @Test
    @Order(5)
    void count() {
        Long count = productService.count();
        assertNotEquals(count, 0);
        log.info("Count:" + count);
    }

    @Test
    @Order(6)
    void findAll() {
        productService.findAll().forEach(product -> log.info(product.getProId()));
    }

    @Test
    @Order(1)
    void save() throws Exception {
        assertNotNull(productService, "El ProductService es nulo");

        Product product = Product.builder()
                .proId(PRO_ID)
                .detail("test datail")
                .enable("A")
                .image("test image")
                .name("test name")
                .price(1222L)
                .build();

        productService.save(product);
    }

    @Test
    @Order(2)
    void findById() {
        Optional<Product> productOptional = productService.findById(PRO_ID);
        assertTrue(productOptional.isPresent(), "El producto con id:" + PRO_ID + " no existe");
    }

    @Test
    @Order(3)
    void update() throws Exception {
        Optional<Product> productOptional = productService.findById(PRO_ID);
        assertTrue(productOptional.isPresent(), "El producto con id:" + PRO_ID + " no existe");

        Product product = productOptional.get();
        product.setEnable("N");
        productService.update(product);
    }

    @Test
    @Order(4)
    void delete() throws Exception {
        Optional<Product> productOptional = productService.findById(PRO_ID);
        assertTrue(productOptional.isPresent(), "El customer con id:" + PRO_ID + " no existe");

        Product product = productOptional.get();

        productService.delete(product);
    }
}
