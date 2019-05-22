package br.com.amaro.converter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.amaro.dto.ProductDTO;
import br.com.amaro.entity.Product;
import br.com.amaro.similarity.SimilarityCalculation;

@Service
public class ProductConverter {

	@Autowired
	private TagConverter tagConverter;

	@Autowired
	private SimilarityCalculation similarityCalculation;

	public List<Product> productDTOListToProductEntityList(List<ProductDTO> productDTOList, String... attributes) {

		List<Product> productEntityList = new ArrayList<>();

		productDTOList.forEach(productDTO -> {

			productEntityList.add(productDTOToProductEntity(productDTO, attributes));

		});

		return productEntityList;

	}

	public Product productDTOToProductEntity(ProductDTO productDTO, String... attributes) {

		Product productEntity = new Product();

		BeanUtils.copyProperties(productDTO, productEntity, attributes);

		productEntity.setTagsVector(tagConverter.convertTagStringListToTagReferenceList(productDTO.getTags()));

		return productEntity;

	}

	public ProductDTO productEntityToProductDTO(Product product, String... attributes) {

		ProductDTO productDTO = new ProductDTO();

		BeanUtils.copyProperties(product, productDTO, attributes);

		productDTO.setTags(tagConverter.convertTagVectorToTagStringList(product.getTagsVector()));

		return productDTO;

	}

	public List<ProductDTO> productListToProductDTOList(List<Product> productList, String... attributes) {

		List<ProductDTO> productDTOList = new ArrayList<>();

		productList.forEach(product -> {

			productDTOList.add(productEntityToProductDTO(product, attributes));

		});

		return productDTOList;

	}

	public List<ProductDTO> productListToProductDTOListWithSimilarity(Product productReference,
			List<Product> productList, String... attributes) {

		List<ProductDTO> productDTOList = new ArrayList<>();

		productList.forEach(product -> {

			if (!product.getId().equals(productReference.getId())) {

				ProductDTO productDTO = productEntityToProductDTO(product, attributes);

				productDTO.setSimilarity(similarityCalculation.similarityBetweenTAGs(productReference.getTagsVector(),
						product.getTagsVector()));

				productDTOList.add(productDTO);
			}

		});

		return productDTOList;

	}

	public List<ProductDTO> productDTOListWithSimilarity(Product product, List<Product> productList) {

		List<ProductDTO> productDTOList = productListToProductDTOListWithSimilarity(product, productList, "tags",
				"tagsVector");

		productDTOList.sort(Comparator.comparingDouble(ProductDTO::getSimilarity));

		return productDTOList.stream().limit(3).collect(Collectors.toList());

	}

}
