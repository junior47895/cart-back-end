package co.edu.usbcali.cart.services;

import co.edu.usbcali.cart.entity.Customer;

import java.util.List;

public interface CustomerService extends  GenericService<Customer, String> {
    List<Customer> findAll();
}
