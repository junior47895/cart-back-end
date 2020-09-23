package co.edu.usbcali.cart.mapper;

import co.edu.usbcali.cart.domain.CustomerDTO;
import co.edu.usbcali.cart.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toCutomerDto(Customer customer);

    Customer toCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> toCustomerDtos(List<Customer> customers);

    List<Customer> toCustomers(List<CustomerDTO> customerDTOs);

}
