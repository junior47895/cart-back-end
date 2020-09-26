package co.edu.usbcali.cart.mapper;

import co.edu.usbcali.cart.domain.ProductDTO;
import co.edu.usbcali.cart.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDto(Product product);

    Product toProduct(ProductDTO productDTO);

    List<ProductDTO> toProductDtos(List<Product> products);

    List<Product> toProducts(List<ProductDTO> productDTOs);
}
