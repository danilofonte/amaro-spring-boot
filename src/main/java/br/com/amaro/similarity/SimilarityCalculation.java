package br.com.amaro.similarity;

import org.springframework.stereotype.Service;

@Service
public class SimilarityCalculation {

	public Double similarityBetweenTAGs(Integer[] tags1, Integer[] tags2) {
		double sum = 0.0;
		for (int i = 0; i < tags1.length; i++) {
			sum = sum + Math.pow((tags1[i] - tags2[i]), 2.0);
		}
		return 1 / (1 + Math.sqrt(sum));
	}

}
