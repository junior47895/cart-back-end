package co.edu.usbcali.cart.mapper;

import co.edu.usbcali.cart.dto.CustomerDTO;
import co.edu.usbcali.cart.domain.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toCustomerDTO(Customer customer);

    Customer toCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> toCustomersDTOs(List<Customer> customers);

    List<Customer> toCustomers(List<CustomerDTO> customerDTOs);

}
