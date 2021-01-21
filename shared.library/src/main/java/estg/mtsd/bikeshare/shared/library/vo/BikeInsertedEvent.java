package estg.mtsd.bikeshare.shared.library.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BikeInsertedEvent extends BikeShareEvent{
    public BikeInsertedEvent(Integer bikeId, Integer dockId) {
        this.setBikeId(bikeId);
        this.setDockId(dockId);
        this.setTimestamp(new Date());
    }
}
