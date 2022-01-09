package demos.lucene.manager;

import demos.lucene.constants.Constants;
import demos.lucene.factory.AnalyzerFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.LatLonPoint;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;
import java.util.Objects;

import static demos.lucene.constants.Constants.LOCATION;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class QueryManager {

    /**
     * This Singleton class is responsible for creating ##IndexReader to read the index based on the analyzer
     * QueryManager exposes the following functionalities:
     * - Execute Term query
     * - Execute Range query
     **/

    private static QueryManager instance = null;
    private IndexSearcher searcher = null;

    private QueryManager() {
    }

    public static QueryManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new QueryManager();
        }
        return instance;
    }

    public void setSearcher(IndexSearcher searcher) {
        this.searcher = searcher;
    }

    public void searchAndPrint(String searchField, String searchTerm) throws IOException, ParseException {
        print(searchIndex(searchField, searchTerm), searcher);
    }

    public void searchByLocation(double lat, double lon, double distance) throws IOException {
        print(doSearch(LatLonPoint.newDistanceQuery(LOCATION, lat, lon, distance * 1000)), searcher);
    }

    public TopDocs searchIndex(String searchField, String searchTerm) throws IOException, ParseException {

        QueryParser queryParser = new QueryParser(searchField, AnalyzerFactory.getAnalyzer());
        Query query = queryParser.parse(searchTerm);

        return doSearch(query);
    }

    private TopDocs doSearch(Query query) throws IOException {
        return searcher.search(query, Constants.MAX_DOC_NUMBER);
    }

    private static void print(TopDocs searchResult, IndexSearcher searcher) throws IOException {

        for (ScoreDoc scoreDoc : searchResult.scoreDocs) {
            Document document = searcher.doc(scoreDoc.doc);

            log.println(document.get(Constants.ID));
            log.println(document.get(Constants.COUNTRY));
            log.println(document.get(Constants.CITY));
            log.println("-------------------------------");
        }
        log.println("===========================================");
    }
}