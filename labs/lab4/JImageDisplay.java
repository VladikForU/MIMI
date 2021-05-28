package com.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    private BufferedImage bufImage;
    public JImageDisplay(int width,int height){
        bufImage=new BufferedImage(width,height,java.awt.image.BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(width,height));
    }
    public int getWidth(){return bufImage.getWidth();}
    public int getHeight(){return bufImage.getHeight();}
    public BufferedImage getBufImage(){return bufImage;}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bufImage, 0, 0, bufImage.getWidth(), bufImage.getHeight(), null);
    }
    public void drawPixel(int x,int y,int rgb){bufImage.setRGB(x,y,rgb);}
    public void clearImage(){for(int x=0;x<bufImage.getWidth();x++){for(int y=0;y<bufImage.getHeight();y++){drawPixel(x,y,0);}}}
}
