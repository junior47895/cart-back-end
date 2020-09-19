package co.edu.usbcali.cart.rest;

import co.edu.usbcali.cart.domain.Customer;
import co.edu.usbcali.cart.dto.CustomerDTO;
import co.edu.usbcali.cart.mapper.CustomerMapper;
import co.edu.usbcali.cart.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerRest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/findAll")
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toCustomerDtos(customers);
    }

    @GetMapping("/findById/{email}")
    public CustomerDTO findById(@PathVariable("email") String email) {
        Optional<Customer> customerOptional = customerRepository.findById(email);
        if (customerOptional.isPresent() == false) {
            return null;
        }
        Customer customer = customerOptional.get();
        return customerMapper.toCutomerDto(customer);
    }
}
