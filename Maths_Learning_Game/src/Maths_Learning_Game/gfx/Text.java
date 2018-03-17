package Maths_Learning_Game.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text {

	public static void drawString(Graphics g, String text ,int xPos, int yPos, boolean center, Color c, Font font) {
		g.setColor(c);
		g.setFont(font);
		if(center) {
			FontMetrics fn = g.getFontMetrics(font);
			xPos = xPos - fn.stringWidth(text) / 2;
			yPos = (yPos - fn.getHeight() / 2) + fn.getAscent();
		}
		g.drawString(text, xPos, yPos);
	}
}
