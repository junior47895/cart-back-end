package co.edu.usbcali.cart.services.impl;

import co.edu.usbcali.cart.domain.ProductDTO;
import co.edu.usbcali.cart.entity.Product;
import co.edu.usbcali.cart.mapper.ProductMapper;
import co.edu.usbcali.cart.repository.ProductRepository;
import co.edu.usbcali.cart.services.IProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {

        log.info("Buscando todos los productos existentes en la base de datos ordenados por código");

        List<Product> products = productRepository.findAllOrOrderByProId();

        return productMapper.toProductDtos(products);

    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(final String id) {
        log.info("Buscando producto por código: {}", id);
        return productMapper.toProductDto(productRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public void save(final ProductDTO product) {
        log.info("Guardando producto: {}", product);
        productRepository.save(productMapper.toProduct(product));
    }

    @Override
    @Transactional
    public void delete(final String id) {
        log.info("Eliminando producto con código: {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findByNameContainsIgnoreCase(final String name) {
        log.info("Buscando productos por nombre: {}", name);
        return productMapper.toProductDtos(productRepository.findByNameContainsIgnoreCase(name));
    }
}
