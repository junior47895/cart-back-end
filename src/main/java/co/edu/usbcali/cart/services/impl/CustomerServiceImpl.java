package co.edu.usbcali.cart.services.impl;

import co.edu.usbcali.cart.domain.CustomerDTO;
import co.edu.usbcali.cart.repository.CustomerRepository;
import co.edu.usbcali.cart.services.ICustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Log4j2
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findAll() {
        try {

        } catch (PersistenceException | DataAccessException e) {

        }
        return null;
    }
}
