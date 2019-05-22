package br.com.amaro.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Product Information")
public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2482379223419841472L;

	@ApiModelProperty(value = "Product ID")
	private Long id;

	@ApiModelProperty(value = "Product name")
	private String name;

	@ApiModelProperty(value = "TAGs information")
	private List<String> tags;

	@ApiModelProperty(value = "TAGs vector information", hidden = true)
	private Integer[] tagsVector;

	@ApiModelProperty(value = "Similarity information", hidden = true)
	private Double similarity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Integer[] getTagsVector() {
		return tagsVector;
	}

	public void setTagsVector(Integer[] tagsVector) {
		this.tagsVector = tagsVector;
	}

	public Double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}

}
