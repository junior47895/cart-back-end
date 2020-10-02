package co.edu.usbcali.cart.rest;

import co.edu.usbcali.cart.domain.Product;
import co.edu.usbcali.cart.dto.ProductDTO;
import co.edu.usbcali.cart.mapper.ProductMapper;
import co.edu.usbcali.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/product")
public class ProductRest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO)throws Exception {

        Product product = productMapper.toProduct(productDTO);

        product = productService.save(product);

        productDTO = productMapper.toProductDTO(product);

        return ResponseEntity.ok().body(productDTO);
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid @RequestBody ProductDTO productDTO)throws Exception {

        Product product = productMapper.toProduct(productDTO);

        product = productService.update(product);

        productDTO = productMapper.toProductDTO(product);

        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> delete(@PathVariable("email") String email) throws Exception{
        productService.deleteById(email);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {

        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(productMapper.toProductDTOs(products));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        Optional<Product> productOptional = productService.findById(id);

        if (productOptional.isPresent() == false) {
            return null;
        }
        Product product = productOptional.get();

        return ResponseEntity.ok().body(productMapper.toProductDTO(product));
    }
}
