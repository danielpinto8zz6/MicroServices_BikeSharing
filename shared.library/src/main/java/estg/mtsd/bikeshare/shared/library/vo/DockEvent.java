package estg.mtsd.bikeshare.shared.library.vo;

import lombok.Data;

import java.util.Date;

@Data
abstract class BikeShareEvent {
    Integer bikeId;

    Integer dockId;

    Date timestamp;
}
