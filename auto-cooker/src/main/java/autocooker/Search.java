package autocooker;

import java.io.IOException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Search {

    private final String searchQuery;
    private final WebClient client;

    public Search(String searchQuery){
        this.searchQuery = searchQuery;

        // Instantiate the client
        client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
    }


    public void performSearch() {
    
        try {
            // Set up the URL and send the request
            String searchUrl = "https://www.allrecipes.com/search?q="  + java.net.URLEncoder.encode(searchQuery, "UTF-8");
            HtmlPage page = client.getPage(searchUrl );

            // Uses XPath to find all elements
            List<HtmlAnchor> links = page.getByXPath("//a[@href]");

            for (HtmlAnchor link : links) {
                // Get the URL from the href attribute
                String href = link.getHrefAttribute().trim();
                // Get the text content of the <a> tag, which is the title
                String title = link.getTextContent().trim();
                
                if (title.toLowerCase().contains(searchQuery.toLowerCase()) || href.toLowerCase().contains(searchQuery.toLowerCase())) {
                System.out.println("Title: " + title);
                System.out.println("URL: " + href);
                System.out.println();
                }
            }

            
        } catch (FailingHttpStatusCodeException | IOException e) {
            e.printStackTrace(System.out);
        } finally {
            client.close();
        }
    }

    public static void main(String[] args) {
        Search search = new Search("apple pie");
        search.performSearch();
    }

}
