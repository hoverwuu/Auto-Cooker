package autocooker;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Search {

    private String searchQuery;
    private WebClient client;

    public Search(String searchQuery){
        this.searchQuery = searchQuery;

        // Instantiate the client
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
    }


    public void performSearch() {
    
        try {
            // Set up the URL and send the request
            String searchUrl = "https://www.allrecipes.com/search?q=";
            HtmlPage page = client.getPage(searchUrl);

            
        } catch (Exception e) {
        }
    

    }

}
