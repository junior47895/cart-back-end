package co.edu.usbcali.cart.mapper;

import co.edu.usbcali.cart.domain.Product;
import co.edu.usbcali.cart.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);

    Product toProduct(ProductDTO productDTO);

    List<ProductDTO> toProductDTOs(List<Product> products);

    List<Product> toProduct(List<ProductDTO> productDTOs);
}
