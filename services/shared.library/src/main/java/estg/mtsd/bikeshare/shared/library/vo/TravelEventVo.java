package estg.mtsd.bikeshare.shared.library.vo;

import java.util.Date;

import lombok.Data;

@Data
public class TravelEventVo {

    private int rentalId;

    private double latitude;

    private double longitude;

    private Date timestamp;
    
}
