//  Description: The BoxGUI class creates a panel and sets its size.

import javax.swing.*;

public class BoxGUI extends JApplet
{

    public void init()
    {
        // create a WholePanel object and add it to the applet
        WholePanel wholePanel = new WholePanel();
        getContentPane().add(wholePanel);

        //set applet size to 400 X 400
        setSize (400, 400);
    }
}
