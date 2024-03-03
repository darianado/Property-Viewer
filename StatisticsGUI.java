import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * StatisticsGUI provides the GUI for the statistics function of the project. 
 * It displays the statistics and strings with the data

 * @author Michael Kölling, David J Barnes, and Josh Murphy, Dariana Dorin
 * @version 1.0
 */
public class StatisticsGUI
{
    // fields:
    private JFrame frame;
    private JPanel statisticsPanel;
    
    private JTextField nrViewed;
    private JTextField averagePrice;
    
    private PropertyViewer viewer;
        
    /**
     * Take the PropertyViewer data and display its statistics GUI on screen.
     */
    public StatisticsGUI(PropertyViewer viewer)
    {
        this.viewer = viewer;
        makeFrame();
    }


    // ---- public view functions ----
    /**
     * Display the statistics
     */
    public void showStatistics()
    {
        nrViewed.setText(convertToString(viewer.getNumberOfPropertiesViewed()));
        averagePrice.setText(convertToString(viewer.averagePropertyPrice()));
    }
    /**
     *  Convert an int to String format
     */
    public String convertToString(int x)
    {
        return ""+x;
    }
    
    // ---- swing stuff to build the frame and all its components ----
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame("Statistics Viewer Application");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        // Create the statistics pane in the center
        statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new GridLayout(2,2));
        
        statisticsPanel.add(new JLabel("Number of propreties viewed: "));
        nrViewed = new JTextField("default");
        nrViewed.setEditable(false);
        statisticsPanel.add(nrViewed);
        
        statisticsPanel.add(new JLabel("Average price: "));
        averagePrice = new JTextField("default");
        averagePrice.setEditable(false);
        statisticsPanel.add(averagePrice);

        statisticsPanel.setBorder(new EtchedBorder());
        contentPane.add(statisticsPanel, BorderLayout.CENTER);
        
        // building is done - arrange the components     
        frame.pack();
        
        // place the frame centered a little to the left and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/4 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }    
}
