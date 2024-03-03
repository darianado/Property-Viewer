import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. 
 * 
 * 
 * @author Michael Kölling and Josh Murphy and Dariana Dorin
 * @version 1.0
 */
public class PropertyViewer
{    
    private PropertyViewerGUI gui; // the Graphical User Interface
    private StatisticsGUI guiStat;  // the Graphical User Interface for statistics
    private Portfolio portfolio;
    private int currentIndex; 
    private int viewedPropertiesPriceSum;
    
    /**
     * Create a PropertyViewer and display its GUI on screen.
     * The first property in the portfolio (index 0) is displayed
     */
    public PropertyViewer()
    {
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");
        currentIndex = 0;
        display(currentIndex);
    }
    
    /**
     * Display property by index 
     * Show the ID of the property
     * Show whether the property has been marked by the user as one 
     * of their favourites
     * Every display of a property means a new viewing of that property
     *
     */
    public void display(int index)
    {
        gui.showProperty(portfolio.getProperty(index));
        gui.showID(portfolio.getProperty(index));
        gui.showFavourite(portfolio.getProperty(index));
        incrementViews(portfolio.getProperty(index));
    }
    
    /**
     * Increment the number of views of a property by 1
     * Add its price to the total price of all properties viewed
     */
    public void incrementViews(Property property)
    {
        int views= property.getNrViews();
        property.setNrViews(views + 1);
        viewedPropertiesPriceSum += portfolio.getProperty(currentIndex).getPrice();
    }
    
    /**
     * Display the next property in the portofolio if there is one 
     * if we are one the last one, go back to the first one in the portofolio
     */
    public void nextProperty()
    {
        if(currentIndex < portfolio.numberOfProperties()-1)
        {
            currentIndex += 1;
        }
        else currentIndex = 0;
        
        display(currentIndex);
    }

    /**
     * Display the previous property in the portofolio if there is one
     * if we are one the first one, go back to the last one in the portofolio
     */
    public void previousProperty()
    {
        if(currentIndex > 0)
        {
            currentIndex -= 1;
        }
        else currentIndex = portfolio.numberOfProperties()-1;
        
        display(currentIndex);
    }

    /**
     * Updates the favourite status of the current proprety displayed
     * and shows if the property is marked as favourite
     */
    public void toggleFavourite()
    {
        portfolio.getProperty(currentIndex).toggleFavourite();
        gui.showFavourite(portfolio.getProperty(currentIndex));
    }
    
    /**
     *  Display the GUI for the statistics 
     *  and update the same window everytime te method is called
     */
    public void statistics()
    {
        if(guiStat==null) 
            guiStat = new StatisticsGUI(this);
        guiStat.showStatistics();
    }
   
    //----- methods for challenge tasks -----
    
    /**
     * This method opens the system's default internet browser
     * The Google maps page should show the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
       double latitude = portfolio.getProperty(currentIndex).getLatitude();
       double longitude = portfolio.getProperty(currentIndex).getLongitude();
       
       URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
       java.awt.Desktop.getDesktop().browse(uri); 
    }
    
    /**
     * add up how many times each property has been viewd and return the total nr of views
     */
    public int getNumberOfPropertiesViewed()
    {
        int views = 0;
        for(int i=0; i<portfolio.numberOfProperties(); i++)
        {
            views = views + portfolio.getProperty(i).getNrViews();
        }
        return views;
    }
    
    /**
     * Return the avarage property price by dividing the sum of the 
     * prices of the propreties viwed with the number of properties viewed
     */
    public int averagePropertyPrice()
    {
        return viewedPropertiesPriceSum / getNumberOfPropertiesViewed();
    }
   
    
}
