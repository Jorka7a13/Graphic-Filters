package com.graphicfilters.design;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Filters {

	public BufferedImage BlurFilter(BufferedImage image) {
		float[] blurMatrix = { 1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
				1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
				1.0f / 9.0f, 1.0f / 9.0f };

		BufferedImageOp blurFilter = new ConvolveOp(
				new Kernel(3, 3, blurMatrix), ConvolveOp.EDGE_NO_OP, null);

		return blurFilter.filter(image, null);
	}

	public BufferedImage SharpenFilter(BufferedImage image) {
		float[] sharpenMatrix = {0, -1, 0, -1, 5, -1, 0, -1, 0};

		BufferedImageOp sharpenFilter = new ConvolveOp(new Kernel(3, 3,
				sharpenMatrix), ConvolveOp.EDGE_NO_OP, null);

		return sharpenFilter.filter(image, null);
	}
	
	public BufferedImage EmbossingFilter(BufferedImage image) {
		float[] embossingMatrix = {-2, -1, 0, -1, 1, 1, 0, 1, 2};

		BufferedImageOp embossingFilter = new ConvolveOp(new Kernel(3, 3,
				embossingMatrix), ConvolveOp.EDGE_NO_OP, null);

		return embossingFilter.filter(image, null);
	}
	
	public BufferedImage EdgeDetectFilter(BufferedImage image) {
		float[] edgeDetectMatrix = {-1, -1, -1, -1, 8, -1, -1, -1, -1};

		BufferedImageOp edgeDetectFilter = new ConvolveOp(new Kernel(3, 3,
				edgeDetectMatrix), ConvolveOp.EDGE_NO_OP, null);

		return edgeDetectFilter.filter(image, null);
	}
	


}
