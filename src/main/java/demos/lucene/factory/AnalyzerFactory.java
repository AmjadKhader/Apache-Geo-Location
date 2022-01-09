package demos.lucene.factory;

import demos.lucene.constants.Constants;
import demos.lucene.manager.IndexManager;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.IOException;
import java.util.Objects;

public class AnalyzerFactory {

    /**
     * This Factory class is responsible for creating and retrieving Analyzer
     * AnalyzerFactory exposes the following functionalities:
     * - Creates an analyzer based on the type ( typical factory class)
     * - Retrieve analyzer type.
     **/

    private static Analyzer analyzer = null;
    private static Constants.eAnalyzerType analyzerType = Constants.eAnalyzerType.STANDARD;

    private AnalyzerFactory() {
    }

    public static Analyzer getAnalyzer() throws IOException {
        if (Objects.isNull(analyzer)) {
            analyzer = createAnalyzer(Constants.eAnalyzerType.STANDARD); // the default analyzer.
            analyzerType = Constants.eAnalyzerType.STANDARD;
            IndexManager.getInstance().setAnalyzerType(Constants.eAnalyzerType.STANDARD);
        }

        return analyzer;
    }

    public static Constants.eAnalyzerType getAnalyzerType() {
        return analyzerType;
    }

    public static Analyzer createAnalyzer(Constants.eAnalyzerType newAnalyzerType) throws IOException {
        analyzerType = newAnalyzerType;

        switch (analyzerType) {
            case SIMPLE:
                analyzer = new SimpleAnalyzer();
                IndexManager.getInstance().setAnalyzerType(Constants.eAnalyzerType.SIMPLE);
                break;

            case STANDARD:
                analyzer = new StandardAnalyzer();
                IndexManager.getInstance().setAnalyzerType(Constants.eAnalyzerType.STANDARD);
                break;

            case WHITE_SPACE:
                analyzer = new WhitespaceAnalyzer();
                IndexManager.getInstance().setAnalyzerType(Constants.eAnalyzerType.WHITE_SPACE);
                break;
        }
        return analyzer;
    }

}
