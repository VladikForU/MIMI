package com.lab4;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

import java.awt.geom.Rectangle2D;

public class FractalExplorer  {
    private int size;
    private JImageDisplay imageDisplay;
    private JButton button;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double range;
    private float H=0.7f;
    public FractalExplorer(int size){
        this.size=size; range=new Rectangle2D.Double(0,0,size,size);
    }
    public void createAndShowGUI(){
        JFrame frame = new JFrame("FractalExplorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        BorderLayout bLayout=new BorderLayout();
        contentPane.setLayout(bLayout);
        imageDisplay=new JImageDisplay(size,size);
        imageDisplay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent){
                float tmpx=-(float)mouseEvent.getX()/size*2,tmpy=-(float)mouseEvent.getY()/size*2;
                if((mouseEvent.getModifiers() & InputEvent.BUTTON1_MASK)!=0){
                    range.x=tmpx; range.y=tmpy;
                }else{range.width=-tmpx*2;range.height=-tmpy*2;}

                drawFractal();
                //fractalGenerator.recenterAndZoomRange(range,mouseEvent.getX(),mouseEvent.getY(),0.5);
            }
            @Override public void mousePressed(MouseEvent mouseEvent){}
            @Override public void mouseReleased(MouseEvent mouseEvent){}
            @Override public void mouseEntered(MouseEvent mouseEvent){}
            @Override public void mouseExited(MouseEvent mouseEvent){}
        });
        contentPane.add(imageDisplay,BorderLayout.CENTER);
        JSlider slider=new JSlider(0,100,(int)(H*100));
        slider.setOrientation(JSlider.VERTICAL);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                H=(float)((JSlider)changeEvent.getSource()).getValue()/100;
            }
        });

        contentPane.add(slider,BorderLayout.LINE_END);
        button=new JButton("Reset Display");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                imageDisplay.clearImage(); imageDisplay.repaint();
            }
        });
        contentPane.add(button, BorderLayout.AFTER_LAST_LINE);
        frame.pack();
        frame.setVisible(true);
        //frame.setResizable(false);
    }

    private void drawFractal(){
        for(int x=0;x<imageDisplay.getWidth();x++){
            for(int y=0;y<imageDisplay.getHeight();y++){
                double xCoord = FractalGenerator.getCoord (range.x, range.x + range.width, size, x);
                double yCoord = FractalGenerator.getCoord (range.y, range.y + range.height, size, y);
                int iter=fractalGenerator.numIterations(xCoord,yCoord);
                if(iter==-1){imageDisplay.drawPixel(x,y,0);}
                else{imageDisplay.drawPixel(x, y, Color.HSBtoRGB(H + (float) iter / 200f, 1f, 1f));}
            }
        }
        imageDisplay.repaint();
    }
    public static void main(String[] args) {
        FractalExplorer app = new FractalExplorer(500);
        app.fractalGenerator=new Mandelbrot();
        app.fractalGenerator.getInitialRange(app.range);
        app.createAndShowGUI();
    }
}
