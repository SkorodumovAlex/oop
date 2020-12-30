import java.util.*;

public class URLPool
{
	public LinkedList<URLDepthPair> reviewedURLs;
    public LinkedList<URLDepthPair> untrackedURLs;
    public LinkedList<String> trackedUrls;
    public int waitingThread;

    public URLPool(URLDepthPair depthPair)
    {
		reviewedURLs = new LinkedList<URLDepthPair>();
		untrackedURLs = new LinkedList<URLDepthPair>();
		trackedUrls = new LinkedList<String>();
		waitingThread = 0;
    	untrackedURLs.add(depthPair);
    }


    public synchronized int getWaitThreads()
    {
    	return waitingThread;
    }

    public synchronized URLDepthPair get()
    {
    	URLDepthPair depthPair = null;
    	if(untrackedURLs.size() == 0){
    		try{
    			waitingThread++;
    			this.wait();
    		}
    		catch(InterruptedException e)
    		{
    			System.err.println("InterruptedException: " + e.getMessage());
    			return null;
    		}
    	}
    	depthPair = untrackedURLs.removeFirst();
    	reviewedURLs.add(depthPair);
    	trackedUrls.add(depthPair.getURL());
    	return depthPair;
    }
    public synchronized void put(LinkedList<URLDepthPair> newdepthPairs)
    {
    	for(int i = 0; i < newdepthPairs.size(); i++){
    		untrackedURLs.add(newdepthPairs.get(i));
    	}
    	if(waitingThread > 0){
    		waitingThread--;
    		this.notify();
    	}
    }

    public synchronized LinkedList<URLDepthPair> getReviewedList()
    {
    	return reviewedURLs;
    }
}