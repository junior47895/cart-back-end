package co.edu.usbcali.cart.repository;

import co.edu.usbcali.cart.entity.Customer;
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
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    private final static String email = "test@test.com";

    @Test
    @Transactional
    @Order(1)
    void test(){
        assertNotNull(customerRepository, "El customerRepository es nulo");
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setAddress("www.prueba.com");
        customer.setEnable("Y");
        customer.setName("Prueba");
        customer.setPhone("318 845 1930");
        customer.setToken("sdafgasdgerqtewrtwertergseg");
        customerRepository.save(customer);
    }

    @Test
    @Transactional
    @Order(2)
    void findById(){
        Optional<Customer> customerOptional = customerRepository.findById(email);
        assertTrue(customerOptional.isPresent(), "customer encontrado");
    }

    @Test
    @Transactional
    @Order(3)
    void update(){
        Optional<Customer> customerOptional = customerRepository.findById(email);
        assertTrue(customerOptional.isPresent(), "customer encontrado");

        Customer customer = customerOptional.get();
        customer.setEnable("N");
        customerRepository.save(customer);
    }

    @Test
    @Transactional
    @Order(4)
    void delete(){
        Optional<Customer> customerOptional = customerRepository.findById(email);
        assertTrue(customerOptional.isPresent(), "El email del customer encontrado");

        Customer customer = customerOptional.get();
        customerRepository.delete(customer);
    }

    @Test
    @Transactional
    @Order(5)
    void count(){
        Long count = customerRepository.count();
        assertNotEquals(count, 0);
        //log.info("Total registros: {} ", count);
    }

    @Test
    @Transactional
    @Order(6)
    void findAl(){
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        /*customerRepository.findAll().forEach(customer -> {
            log.info("email: {} ", customer.getEmail());
        });*/

        for (Customer customer: customerList ) {
            //log.info("email: {} ", customer.getEmail());
        }

        /* forma funcional
        customerList.forEach(customer -> {
            log.info("email: {} ", customer.getEmail());
        });*/
    }
}

