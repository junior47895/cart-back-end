package co.edu.usbcali.cart.api;

import java.util.List;
import java.util.Optional;

import co.edu.usbcali.cart.domain.CustomerDTO;
import co.edu.usbcali.cart.entity.Customer;
import co.edu.usbcali.cart.mapper.CustomerMapper;
import co.edu.usbcali.cart.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/customer")
public class CustomerRest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerMapper customerMapper;

    @PostMapping("/save")
    public CustomerDTO save(@RequestBody CustomerDTO customerDTO) throws Exception {

        Customer customer = customerMapper.toCustomer(customerDTO);

        customer = customerService.save(customer);

        customerDTO = customerMapper.toCustomerDto(customer);

        return customerDTO;
    }

    @PutMapping("/update")
    public CustomerDTO update(@RequestBody CustomerDTO customerDTO) throws Exception {

        Customer customer = customerMapper.toCustomer(customerDTO);

        customer = customerService.update(customer);

        customerDTO = customerMapper.toCustomerDto(customer);

        return customerDTO;
    }

    @DeleteMapping("/delete/{email}")
    public void delete(@PathVariable("email") String email) throws Exception {
        customerService.deleteById(email);
    }

    @GetMapping("/findAll")
    public List<CustomerDTO> findAll() {

        List<Customer> customers = customerService.findAll();
        return customerMapper.toCustomerDtos(customers);

    }

    @GetMapping("/findById/{email}")
    public CustomerDTO findById(@PathVariable("email") String email) throws Exception {
        Optional<Customer> customerOptional = customerService.findById(email);

        if (customerOptional.isPresent() == false) {
            return null;
        }
        Customer customer = customerOptional.get();

        return customerMapper.toCustomerDto(customer);
    }

}

