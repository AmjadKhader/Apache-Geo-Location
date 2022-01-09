package demos.lucene.manager;

import demos.lucene.constants.Constants;
import demos.lucene.entites.City;
import demos.lucene.factory.AnalyzerFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LatLonPoint;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static demos.lucene.constants.Constants.*;

public class IndexManager {

    /**
     * This Singleton class manages the index.
     * IndexManager is responsible for:
     * - Creating new index.
     * - Add document to index.
     * - Delete document from index.
     * - Update document in index by field.
     **/

    private static IndexManager instance = null;
    private IndexWriter indexWriter = null;

    private IndexManager() {
    }

    public static IndexManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new IndexManager();
        }
        return instance;
    }

    public void setAnalyzerType(Constants.eAnalyzerType analyzerType) throws IOException {
        FSDirectory directory = DirectoryManager.getDirectory(analyzerType);
        try {
            indexWriter = new IndexWriter(directory, new IndexWriterConfig(AnalyzerFactory.getAnalyzer()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void createIndex() throws IOException {
        List<Document> documents = new ArrayList<>();

        Document document1 = createDocument(City.builder().cityId(1).country("Jordan").cityName("Jarash").latitude(32.2723).longitude(35.8914).build());
        Document document2 = createDocument(City.builder().cityId(2).country("Jordan").cityName("Ma'an").latitude(30.194958).longitude(35.734241).build());
        Document document3 = createDocument(City.builder().cityId(3).country("Jordan").cityName("Amman").latitude(31.963158).longitude(35.930359).build());
        Document document4 = createDocument(City.builder().cityId(4).country("Jordan").cityName("Irbid").latitude(32.551445).longitude(35.851479).build());
        Document document5 = createDocument(City.builder().cityId(5).country("Jordan").cityName("Az Zarqa").latitude(32.0833).longitude(36.1000).build());

        documents.add(document1);
        documents.add(document2);
        documents.add(document3);
        documents.add(document4);
        documents.add(document5);

        //Let's clean everything first
        indexWriter.deleteAll();

        indexWriter.addDocuments(documents);

        indexWriter.prepareCommit();
        indexWriter.commit();
    }

    public void deleteDocument(String term, String field) throws IOException {
        indexWriter.deleteDocuments(new Term(term, field));
        indexWriter.commit();
    }

    public void updateDocument(City newCity) throws IOException {
        Document document = createDocument(newCity);

        indexWriter.updateDocument(new Term(ID, document.get(ID)), document);
        indexWriter.commit();
    }

    private Document createDocument(City city) {
        Document document = new Document();

        document.add(new TextField(ID, String.valueOf(city.getCityId()), Field.Store.YES));
        document.add(new TextField(COUNTRY, city.getCountry(), Field.Store.YES));
        document.add(new TextField(CITY, city.getCityName(), Field.Store.YES));
        document.add(new LatLonPoint(LOCATION, city.getLatitude(), city.getLatitude()));

        return document;
    }

    public void addDocument(City city) throws IOException {
        Document document = createDocument(city);

        indexWriter.addDocument(document);
        indexWriter.commit();
    }
}