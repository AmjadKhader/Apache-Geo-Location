package demos;

import demos.lucene.constants.Constants;
import demos.lucene.factory.AnalyzerFactory;
import demos.lucene.manager.QueryManager;
import demos.lucene.manager.SearcherManager;
import org.apache.lucene.search.IndexSearcher;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class MainSearcher {

    public static void main(String[] args) throws Exception {

        //set analyzer type
        AnalyzerFactory.createAnalyzer(Constants.eAnalyzerType.STANDARD);
        IndexSearcher searcher = SearcherManager.createSearcher();

        QueryManager.getInstance().setSearcher(searcher);

        //Search by ID
        log.println("Search by Id Results : ");
        QueryManager.getInstance().searchAndPrint(Constants.ID, "1");

        //Search by Title
        log.println("Search by Title Results : ");
        QueryManager.getInstance().searchAndPrint(Constants.COUNTRY, "jordan");

        //Search by Location
        log.println("Search by Location Results : ");
        QueryManager.getInstance().searchByLocation(32.0287, 35.8056, 350); // Get cities that are 350 KM away.
    }
}