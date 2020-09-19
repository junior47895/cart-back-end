package co.edu.usbcali.cart.mapper;

import co.edu.usbcali.cart.domain.Customer;
import co.edu.usbcali.cart.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    public CustomerDTO toCutomerDto(Customer customer);

    public Customer toCustomer(CustomerDTO customerDTO);

    public List<CustomerDTO> toCustomerDtos(List<Customer> customers);

    public List<Customer> toCustomers(List<CustomerDTO> customerDTOs);

}
