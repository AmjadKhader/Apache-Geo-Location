package demos.lucene.constants;

public class Constants {

    public enum eAnalyzerType {STANDARD, SIMPLE, WHITE_SPACE}
    public enum eOperation {ADD, ADD_DOC, UPDATE_DOC, DELETE_DOC}

    public static final String INDEX_DIR_STANDARD = "/home/amjad/Desktop/Lucene Geo Location/StandardAnalyzer";
    public static final String INDEX_DIR_SIMPLE = "/home/amjad/Desktop/Lucene Geo Location/SimpleAnalyzer";
    public static final String INDEX_DIR_WHITE = "/home/amjad/Desktop/Lucene Geo Location/WhiteAnalyzer";

    public static final String ID = "id";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String LOCATION = "location";

    public static final int MAX_DOC_NUMBER = 10;
}
