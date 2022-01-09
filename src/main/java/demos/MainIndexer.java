package demos;

import demos.lucene.constants.Constants;
import demos.lucene.entites.City;
import demos.lucene.factory.AnalyzerFactory;
import demos.lucene.manager.IndexManager;

import java.nio.file.FileSystemNotFoundException;

public class MainIndexer {

    public static void main(String[] args) {
        try {

            Constants.eOperation operation = Constants.eOperation.ADD_DOC;
            AnalyzerFactory.createAnalyzer(Constants.eAnalyzerType.STANDARD);

            switch (operation) {
                case ADD:
                    IndexManager.getInstance().createIndex();
                    break;
                case DELETE_DOC:
                    IndexManager.getInstance().deleteDocument(Constants.ID, "5");
                    break;
                case UPDATE_DOC:
                    IndexManager.getInstance().updateDocument(City.builder()
                            .cityId(5)
                            .country("Jordan")
                            .cityName("Zarqa")
                            .latitude(32.0833).longitude(36.1000)
                            .build());
                    break;
                case ADD_DOC:
                    IndexManager.getInstance().addDocument(City.builder()
                            .cityId(9)
                            .country("Jordan")
                            .cityName("Aqaba")
                            .latitude(29.3577).longitude(34.9627)
                            .build());
                    break;
                default:
                    throw new FileSystemNotFoundException("Wrong input");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}