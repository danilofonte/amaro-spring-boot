package br.com.amaro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.amaro.converter.ProductConverter;
import br.com.amaro.dto.ProductDTO;
import br.com.amaro.entity.Product;
import br.com.amaro.repository.ProductRepository;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/add/all")
	public List<ProductDTO> products(@RequestBody List<ProductDTO> productDTOList) {

		List<Product> productList = productConverter.productDTOListToProductEntityList(productDTOList, "tags",
				"tagsVector");

		productRepository.saveAll(productList);

		return productConverter.productListToProductDTOList(productList, "similarity");

	}

	@PostMapping("/add")
	public ProductDTO product(@RequestBody ProductDTO productDTO) {

		Product product = productConverter.productDTOToProductEntity(productDTO, "tags", "tagsVector");

		productRepository.save(product);

		return productConverter.productEntityToProductDTO(product, "similarity");

	}

	@GetMapping("/similarity")
	public List<ProductDTO> product(@RequestParam(name = "product") Long id) {

		Optional<Product> product = productRepository.findById(id);

		if (product.isPresent()) {

			List<Product> products = productRepository.findAll();

			return productConverter.productDTOListWithSimilarity(product.get(), products);

		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProductNotFound", null);

	}

}
