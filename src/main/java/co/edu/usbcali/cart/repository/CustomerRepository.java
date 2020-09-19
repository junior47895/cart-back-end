package co.edu.usbcali.cart.repository;

import co.edu.usbcali.cart.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
