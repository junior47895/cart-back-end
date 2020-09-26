package co.edu.usbcali.cart.services;

import co.edu.usbcali.cart.domain.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findAll();

    ProductDTO findById(String id);

    void save(ProductDTO product);

    void delete(String id);

    List<ProductDTO> findByNameContainsIgnoreCase(String name);

}
