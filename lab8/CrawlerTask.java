import java.util.*;

public class CrawlerTask implements Runnable
{
	URLPool myPool;
	public CrawlerTask(URLPool pool)
	{
		myPool = pool;
	}

	public void run(){
		URLDepthPair depthPair = myPool.get();
		int depth = depthPair.getDepth();
		if(depth < Crawler.depth){
			LinkedList<String> urls = new LinkedList<String>();
			LinkedList<URLDepthPair> newUrls = new LinkedList<URLDepthPair>();
			urls = Crawler.getAllUrl(depthPair);
			for(int i = 0; i < urls.size(); i++){
				String newURL = urls.get(i);
				if(!myPool.trackedUrls.contains(newURL)){
					URLDepthPair newDepthPair = new URLDepthPair(newURL, depth + 1);
					newUrls.add(newDepthPair);
					myPool.trackedUrls.add(newURL);
				}
			}
			myPool.put(newUrls);
		}
	}
}