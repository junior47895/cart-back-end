package co.edu.usbcali.cart.service;

import co.edu.usbcali.cart.domain.Product;
import co.edu.usbcali.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Scope("singleton")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Validator validator;


    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return productRepository.count();
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Product save(Product product) throws Exception {
        if(product==null) {
            throw new Exception("El producto no puede nulo");
        }

        validate(product);

        if(productRepository.existsById(product.getProId())==true) {
            throw new Exception("El producto con id:"+product.getProId()+" ya existe");
        }

        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Product update(Product product) throws Exception {
        if(product==null) {
            throw new Exception("El producto no puede nulo");
        }

        validate(product);

        if(productRepository.existsById(product.getProId())==false) {
            throw new Exception("El producto con id:"+product.getProId()+" no existe");
        }

        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void delete(Product product) throws Exception {
        if(product==null) {
            throw new Exception("El producto no puede nulo");
        }

        if(product.getProId()==null || product.getProId().isBlank()==true) {
            throw new Exception("El id es obligatorio ");
        }

        if(productRepository.existsById(product.getProId())==false) {
            throw new Exception("El producto con id:"+product.getProId()+" no existe");
        }

        productRepository.findById(product.getProId()).ifPresent(entity->{
            if(entity.getShoppingProducts()!=null && entity.getShoppingProducts().isEmpty()==false) {
                throw new RuntimeException("El customer tiene ShoppingProducts asociados");
            }
        });

        productRepository.deleteById(product.getProId());

    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteById(String id) throws Exception {
        if(id==null) {
            throw new Exception("El id no puede ser nulo");
        }
        if(productRepository.existsById(id)==false) {
            throw new Exception("El producto con id:"+id+" no existe");
        }
        delete(productRepository.findById(id).get());
    }

    @Override
    public void validate(Product product) throws ConstraintViolationException {

        Set<ConstraintViolation<Product>> constraintViolations=validator.validate(product);
        if(constraintViolations.isEmpty()==false) {
            throw new ConstraintViolationException(constraintViolations);
        }

    }


}