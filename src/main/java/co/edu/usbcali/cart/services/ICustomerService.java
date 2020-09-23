package co.edu.usbcali.cart.services;

import co.edu.usbcali.cart.domain.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    List<CustomerDTO> findAll();
}
