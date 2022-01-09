package demos.lucene.entites;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {
    private int cityId;

    private String country;
    private String cityName;

    private double latitude;
    private double longitude;
}
