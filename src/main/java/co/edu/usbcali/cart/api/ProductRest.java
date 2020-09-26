package co.edu.usbcali.cart.api;

import co.edu.usbcali.cart.domain.ProductDTO;
import co.edu.usbcali.cart.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/product")
public class ProductRest {

    @Autowired
    private IProductService productService;


    @GetMapping("/findAll")
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }

    @GetMapping("/findById/{proId}")
    public ResponseEntity<?> findById(@PathVariable("proId") String proId) {
        Map<String, Object> response = new HashMap<>();
        try {
            ProductDTO product = productService.findById(proId);

            if (product == null) {
                response.put("mensaje", "El producto con código '".concat(proId.concat("' no existe en la base de datos!")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }


            response.put("mensaje", "El producto con código '".concat(proId.concat("' fue encontrado corretamente!")));
            response.put("producto", product);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/save")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).
                            collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            productService.save(productDTO);
            response.put("mensaje", "El producto fue creado con exito");
            response.put("producto", productDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert a la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping(value = "/update/{id}")//request mapping de accion
    public ResponseEntity<?> edit(@Valid @RequestBody ProductDTO productDTO, BindingResult result, @PathVariable String id) {
        ProductDTO productCurrent = productService.findById(id);

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).
                            collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (productCurrent == null) {
            response.put("mensaje", "No se puede editar el producto con código '".concat(id.toString().concat("' no existe en la base de datos!")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            productCurrent = ProductDTO.builder()
                    .proId(productDTO.getProId())
                    .detail(productDTO.getDetail())
                    .enable(productDTO.getEnable())
                    .image(productDTO.getImage())
                    .name(productDTO.getName())
                    .price(productDTO.getPrice())
                    .build();

            productService.save(productCurrent);

            response.put("mensaje", "El producto fue actualizado con exito");
            response.put("cliente", productCurrent);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ProductDTO productCurrent = productService.findById(id);

            productService.delete(id);

            response.put("mensaje", "El producto fue eliminado con exito");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar de la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
