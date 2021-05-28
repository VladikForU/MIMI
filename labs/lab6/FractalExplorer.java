package com.lab6;

import com.lab4.FractalGenerator;
import com.lab4.JImageDisplay;
import com.lab4.Mandelbrot;
import com.lab5.BurningShip;
import com.lab5.Tricorn;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.File;


public class FractalExplorer {
    private class FractalWorker extends SwingWorker<Object, Object> {
        int x;
        int[] arr;
        FractalWorker(int x){this.x=x;}
        @Override
        protected Object doInBackground() throws Exception {
            double xCoord = FractalGenerator.getCoord (range.x, range.x + range.width, size, x);
            arr=new int[imageDisplay.getHeight()];
            for(int y=0;y<imageDisplay.getHeight();y++){
                double yCoord = FractalGenerator.getCoord (range.y, range.y + range.height, size, y);
                int iter=fractalGenerator.numIterations(xCoord,yCoord);
                if(iter==-1){arr[y]=0;}
                else{arr[y]=Color.HSBtoRGB(H + (float) iter / 200f, 1f, 1f);}
            }
            return null;
        }

        @Override
        protected void done() {
            super.done();
            for(int y=0;y<imageDisplay.getHeight();y++){imageDisplay.drawPixel(x,y,arr[y]);}
            imageDisplay.repaint(x,0,1,imageDisplay.getHeight());
            if(x==imageDisplay.getHeight()-1){enableUI(true);}
        }
    }
    private int size;
    private boolean UIenable=true;
    private JImageDisplay imageDisplay;
    private JButton button,bsave;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double range;
    JComboBox<String> comboBox;
    private String[] fractals=new String[]{"Mandelbrot","Tricorn","Burning Ship"};
    private float H=0.7f;
    JFrame frame;
    public FractalExplorer(int size){
        this.size=size; range=new Rectangle2D.Double(0,0,size,size);
    }

    public void createAndShowGUI(){
        frame = new JFrame("FractalExplorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        //contentPane.setLayout();
        JPanel panel=new JPanel(new BorderLayout());
        comboBox=new JComboBox<>(fractals);
        comboBox.setEditable(false);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                switch(fractals[((JComboBox<String>)actionEvent.getSource()).getSelectedIndex()]){
                    case "Mandelbrot":
                        fractalGenerator=new Mandelbrot();
                        break;
                    case "Tricorn":
                        fractalGenerator=new Tricorn();
                        break;
                    case "Burning Ship":
                        fractalGenerator=new BurningShip();
                        break;
                }
                fractalGenerator.getInitialRange(range);
                drawFractal();
            }
        });
        panel.add(comboBox,BorderLayout.NORTH);
        imageDisplay=new JImageDisplay(size,size);
        imageDisplay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent){
                if(UIenable){
                    float tmpx=-(float)mouseEvent.getX()/size*2,tmpy=-(float)mouseEvent.getY()/size*2;
                    if((mouseEvent.getModifiers() & InputEvent.BUTTON1_MASK)!=0){
                        range.x=tmpx; range.y=tmpy;
                    }else{range.width=-tmpx*2;range.height=-tmpy*2;}
                    drawFractal();
                }

            }
            @Override public void mousePressed(MouseEvent mouseEvent){}
            @Override public void mouseReleased(MouseEvent mouseEvent){}
            @Override public void mouseEntered(MouseEvent mouseEvent){}
            @Override public void mouseExited(MouseEvent mouseEvent){}
        });
        panel.add(imageDisplay,BorderLayout.CENTER);
        JSlider slider=new JSlider(0,100,(int)(H*100));
        slider.setOrientation(JSlider.VERTICAL);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                H=(float)((JSlider)changeEvent.getSource()).getValue()/100;
            }
        });

        panel.add(slider,BorderLayout.LINE_END);
        button=new JButton("Reset Display");
        bsave=new JButton("Save Image");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                imageDisplay.clearImage(); imageDisplay.repaint();
            }
        });
        bsave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser=new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if(chooser.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
                    File f=chooser.getSelectedFile();
                    try{
                        ImageIO.write(imageDisplay.getBufImage(),"png",f);}catch(Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame,ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    if(!f.getName().toLowerCase().endsWith(".png")){f.renameTo(new File(f.getAbsolutePath()+".png"));}
                }

            }
        });
        JPanel panel2=new JPanel(new FlowLayout());
        panel2.add(bsave);
        panel2.add(button);
        panel.add(panel2,BorderLayout.SOUTH);
        contentPane.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal(){enableUI(false); for(int x=0;x<imageDisplay.getWidth();x++){new FractalWorker(x).execute();} }

    private void enableUI(boolean val){
        UIenable=val;
        comboBox.setEnabled(val);
        button.setEnabled(val);
        bsave.setEnabled(val);
    }
    public static void main(String[] args) {
        FractalExplorer app = new FractalExplorer(500);
        app.fractalGenerator=new Mandelbrot();
        app.fractalGenerator.getInitialRange(app.range);
        app.createAndShowGUI();
    }
}


