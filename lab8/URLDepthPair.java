import java.net.*;

public class URLDepthPair
{
    private String currentURL;
    private int currentDepth;
    
    public URLDepthPair(String URL, int depth)
    {
        currentURL = URL;
        currentDepth = depth;
    }
    
    public String getURL()
    {
        return currentURL; 
    }
    
    public int getDepth()
    {
        return currentDepth;
    }
    
    public String toString()
    {
        String stringDepth = Integer.toString(currentDepth);
        return stringDepth + '\t' + currentURL;
    }
    
    public String getDocPath()
    {
        try{
            URL url = new URL(currentURL);
            return url.getPath();
        }
        catch(MalformedURLException e)
        {
            System.err.println("MalformedURLException: " + e.getMessage());
            return null;
        }
    }
    
    public String getWebHost()
    {
        try{
            URL url = new URL(currentURL);
            return url.getHost();
        }
        catch(MalformedURLException e)
        {
            System.err.println("MalformedURLException: " + e.getMessage());
            return null;
        }
    }
}