package com.graphicfilters.design;

import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;

import org.eclipse.swt.graphics.ImageData;

public class Utility {

	public ImageData method(int i ,ImageData sourceImageData){
		
		BufferedImage filteredBufferedImage = null;
		ImageData filteredImageData = null;
		
		if(i == 0) {
			 filteredImageData = sourceImageData;
		}else
		
		if (i == 1) {
			filteredBufferedImage = Design.filter.BlurFilter(Design.converter
					.convertToAWT(sourceImageData));
			filteredImageData = Converter
					.convertToSWT(filteredBufferedImage);
		} else

		if (i == 3 ) {
			filteredBufferedImage = Design.filter.EdgeDetectFilter(Design.converter
					.convertToAWT(sourceImageData));
			filteredImageData = Converter
					.convertToSWT(filteredBufferedImage);
		} else

		if (i == 2) {
			filteredBufferedImage = Design.filter.SharpenFilter(Design.converter
					.convertToAWT(sourceImageData));
			filteredImageData = Converter
					.convertToSWT(filteredBufferedImage);
		} else

		if (i == 4) {
			filteredBufferedImage = Design.filter.EmbossingFilter(Design.converter
					.convertToAWT(sourceImageData));
			filteredImageData = Converter
					.convertToSWT(filteredBufferedImage);
		}

		 

		
		return filteredImageData;
			
	}
	
	
}
