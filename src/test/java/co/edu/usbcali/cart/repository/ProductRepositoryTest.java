package co.edu.usbcali.cart.repository;

import co.edu.usbcali.cart.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(false) //devuelve al estado anterior
@Log4j2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    private final static String PRO_ID = "test0001";

    @Test
    @Transactional
    @Order(1)
    void test(){
        assertNotNull(productRepository, "El productRepository es nulo");
        Product product = Product.builder()
                .proId(PRO_ID)
                .detail("test datail")
                .enable("A")
                .image("test image")
                .name("test name")
                .price(1222L)
                .build();
        productRepository.save(product);
    }

    @Test
    @Transactional
    @Order(2)
    void findById(){
        Optional<Product> productOptional = productRepository.findById(PRO_ID);
        assertTrue(productOptional.isPresent(), "product encontrado");
    }

    @Test
    @Transactional
    @Order(3)
    void update(){
        Optional<Product> productOptional = productRepository.findById(PRO_ID);
        assertTrue(productOptional.isPresent(), "product encontrado");

        Product product = productOptional.get();
        product.setEnable("N");
        productRepository.save(product);
    }

    @Test
    @Transactional
    @Order(4)
    void delete(){

        Optional<Product> productOptional = productRepository.findById(PRO_ID);
        assertTrue(productOptional.isPresent(), "El email del product encontrado");

        Product product = productOptional.get();
        productRepository.delete(product);
    }

    @Test
    @Transactional
    @Order(5)
    void count(){
        Long count = productRepository.count();
        assertNotEquals(count, 0);
        log.info("Total registros: {} ", count);
    }

    @Test
    @Transactional
    @Order(6)
    void findAll(){
        List<Product> productList = productRepository.findAll();
        /*customerRepository.findAll().forEach(customer -> {
            log.info("email: {} ", customer.getEmail());
        });*/

        for (Product product: productList ) {
            log.info("email: {} ", product.getProId());
        }

        /* forma funcional
        customerList.forEach(customer -> {
            log.info("email: {} ", customer.getEmail());
        });*/
    }



}
