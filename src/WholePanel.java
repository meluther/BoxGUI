// Description: The WholePanel class creates the GUI of the applet, as well as 
// the methods to handle the actions of buttons/check boxes and rectangle sizing
// adjustments. 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  //to use listener interfaces

public class WholePanel extends JPanel
{
    private Color currentColor;
    private int currentWidth, currentHeight;
    private CanvasPanel canvas;
    private JPanel menuPanel;
    private JCheckBox fillCheck;
    private int x1, y1;
    private JRadioButton white, red, blue, green, orange; 
    private boolean shouldFill = false; 
    private boolean isWhite = false; 
    private boolean isRed = false; 
    private boolean isBlue = false; 
    private boolean isGreen = false; 
    private boolean isOrange = false; 
    private boolean rBorderPressed = false; 
    private boolean lBorderPressed = false; 
    private boolean tBorderPressed = false; 
    private boolean bBorderPressed = false;
   
    //components all aranged here
    public WholePanel()
    {
        //white is the default color
        currentColor = Color.WHITE;

        //default x-y cooridnate, width, and height of a rectangle
        currentWidth = currentHeight = 100;
        x1 = 100; y1 = 100;

        //initialize fillBox
        fillCheck = new JCheckBox("Filled");

        //create panel
        menuPanel = new JPanel();
        menuPanel.add(fillCheck);

        //initialize buttons
        white = new JRadioButton("white", true); 
        red = new JRadioButton("red"); 
        blue = new JRadioButton("blue"); 
        green = new JRadioButton("green"); 
        orange = new JRadioButton("orange"); 
        
        //add buttons to GUI
        ButtonGroup group = new ButtonGroup(); 
        group.add(white); 
        group.add(red);
        group.add(blue);
        group.add(green);
        group.add(orange);
        menuPanel.setLayout(new GridLayout(1,6)); 
        menuPanel.add(white); 
        menuPanel.add(red); 
        menuPanel.add(blue); 
        menuPanel.add(green); 
        menuPanel.add(orange); 
       
        canvas = new CanvasPanel();
        JSplitPane sPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, menuPanel, 
                canvas);

        setLayout(new BorderLayout());
        add(sPane, BorderLayout.CENTER);
    }
        //nested class of WholePanel; listens for check box
        private class FillListener implements ItemListener
        {
            //stores whether or not check box is filled 
            public void itemStateChanged(ItemEvent event)
            {
                if (fillCheck.isSelected())
                {
                    shouldFill = true;   
                }
                if(!fillCheck.isSelected())
                {
                    shouldFill = false; 
                }
                canvas.repaint(); 
            }
        }
    
        //nested class of WholePanel; lists for radio buttons
        private class ColorListener implements ActionListener
        {
            //color chosen by user is assigned to currentColor
            public void actionPerformed(ActionEvent event)
            {
                if (white.isSelected())
                {
                    currentColor = Color.WHITE; 
                }
                else if (red.isSelected())
                { 
                    currentColor = Color.RED; 
                }
                else if (blue.isSelected())
                {
                    currentColor = Color.BLUE; 
                }
                else if (green.isSelected())
                {
                    currentColor = Color.GREEN;
                }
                else if (orange.isSelected())
                {
                    currentColor = Color.ORANGE; 
                }
                canvas.repaint(); 
            }
        }

    //CanvasPanel is the panel where a rectangle will be drawn
    private class CanvasPanel extends JPanel
    {
        //Constructor to initialize the canvas panel
        public CanvasPanel()
        {
            // ensures that the panel listens to user actions
            setLayout(new FlowLayout()); 
            PointListener lis = new PointListener();
            FillListener fLis = new FillListener(); 
            ColorListener cLis = new ColorListener(); 
            fillCheck.addItemListener(fLis);
            white.addActionListener(cLis);
            red.addActionListener(cLis);
            blue.addActionListener(cLis);
            green.addActionListener(cLis);
            orange.addActionListener(cLis);
            addMouseListener(lis);
            addMouseMotionListener(lis);
            setBackground(Color.BLACK);
        }

            //this method draws the rectangle with a selected color
            public void paintComponent(Graphics page)
            {
                super.paintComponent(page);

                page.setColor(currentColor);

                if (shouldFill == true) //if user checks the fill box
                {
                    page.setColor(currentColor);
                    page.fillRect(x1, y1, currentWidth, currentHeight);
                }
                else if(shouldFill == false) //if check box is unchecked
                {
                    page.drawRect(x1, y1, currentWidth, currentHeight);
                }

                // the following if statements set the color to the appropriate 
                // radio button choice
                if(isWhite == true) 
                {
                    page.setColor(Color.WHITE);
                    currentColor = Color.WHITE; 
                    isWhite = false; 
                }
                else if(isRed == true)
                {
                    page.setColor(Color.RED);
                    currentColor = Color.RED; 
                    isRed = false; 
                }
                else if(isBlue == true)
                {
                    page.setColor(Color.BLUE);
                    currentColor = Color.BLUE; 
                    isBlue = false; 
                }
                else if(isGreen == true)
                {
                    page.setColor(Color.GREEN);
                    currentColor = Color.GREEN;
                    isGreen = false; 
                }  
            }

        // listener class that listens to the mouse
        public class PointListener implements MouseListener, MouseMotionListener
        {
            //in case that a user presses using a mouse,
            //record the point where it was pressed.
            public void mousePressed (MouseEvent event) 
            {
                int x = event.getX(); 
                int y = event.getY(); 

                if (x > x1 + currentWidth - 5 && x < x1 + currentWidth + 5) //right border
                {
                    rBorderPressed = true; 
                }
                else if (x > x1 - 5 && x < x1 + 5) //left border 
                {
                    lBorderPressed = true; 
                }
                else if (y > y1 - 5 && y < y1 + 5) //top border
                {
                    tBorderPressed = true; 
                }
                else if (y > y1 + currentHeight - 5 && y < y1 + currentHeight + 5) //bottom border    
                {
                    bBorderPressed = true; 
                }
            }
            public void mouseClicked (MouseEvent event) {}
            //ensures other mouse activity is not altered 
            public void mouseReleased (MouseEvent event) 
            {
                rBorderPressed = false; 
                lBorderPressed = false; 
                tBorderPressed = false;
                bBorderPressed = false; 
            }
            public void mouseEntered (MouseEvent event) {}
            public void mouseExited (MouseEvent event) {}
            public void mouseMoved(MouseEvent event) {}
            //updates dimensions of rectangle
            public void mouseDragged(MouseEvent event)
            {
                int x = event.getX(); 
                int y = event.getY(); 
                
                if(rBorderPressed == true) // right border is pressed
                {
                    if (x < x1) // dragged to left 
                    {
                        if (currentWidth + (x1 - x) >= 10) //ensures adequate pixel dimensions
                        {
                            currentWidth = currentWidth + (x1 - x); //updates width
                            x1 = x; //resets starting point
                        }  
                    }
                    if (x > x1) // dragged to right
                    {
                        if (x - x1 >= 10)
                        {
                            currentWidth = x - x1;
                        } 
                    } 
                }
                else if(lBorderPressed == true) //left border 
                {
                    if (x < x1) // dragged to left 
                    {
                        if (currentWidth + (x1 - x) >= 10)
                        {
                            currentWidth = currentWidth + (x1 - x);
                            x1 = x; 
                        }
                    }
                    if (x > x1) // dragged to right
                    {
                        if (currentWidth - (x - x1) >= 10)
                        {
                            currentWidth = currentWidth - (x - x1);
                            x1 = x; 
                        } 
                    } 
                }
                if (tBorderPressed == true)
                {
                    if (y < y1) // dragged up
                    {
                        if (currentHeight + (y1 - y) >= 10)
                        {
                            currentHeight = currentHeight + (y1 - y); 
                            y1 = y; 
                        }
                    }
                    if (y > y1) // dragged down
                    {
                        if (currentHeight - (y - y1) >= 10)
                        {
                            currentHeight = (currentHeight + y1) - y; 
                            y1 = y; 
                        }
                    }
                }
                else if (bBorderPressed == true)
                {
                    if (y < y1) // dragged up
                    {
                        if (y - y1 >= 10)
                        {
                            currentHeight = y - y1;
                            y1 = y; 
                        }  
                    }
                    if (y > y1) // dragged down
                    {
                        if (y - y1 >= 10)
                        {
                            currentHeight = y - y1;
                        }  
                    }   
                }
                repaint(); 
            } // end of MouseDragged
        } // end of PointListener
    } // end of Canvas Panel Class
} // end of Whole Panel Class
