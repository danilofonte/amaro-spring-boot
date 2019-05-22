package br.com.amaro.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.amaro.enums.TagEnum;

@Service
public class TagConverter {

	public Integer[] convertTagStringListToTagReferenceList(List<String> tagStringList) {

		Integer[] tags = new Integer[20];
		Arrays.fill(tags, new Integer(0));

		tagStringList.forEach(tag -> {

			tags[TagEnum.valueOf(tag).posicao] = 1;

		});

		return tags;

	}

	public List<String> convertTagVectorToTagStringList(Integer [] tagVector) {

		List<String> tags = new ArrayList<>();
		
		for (int i=0; i<tagVector.length; i++) {

			if (tagVector[i] == 1) {
				
				tags.add(TagEnum.retrieveByPosicao(tagVector[i]).name());
				
			}

		};

		return tags;

	}

}
