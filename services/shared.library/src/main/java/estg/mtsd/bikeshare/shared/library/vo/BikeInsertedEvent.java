package estg.mtsd.bikeshare.shared.library.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BikeInsertedEvent extends BikeShareEvent {

    public BikeInsertedEvent(Integer bikeId, Integer dockId) {
        this.setBikeId(bikeId);
        this.setDockId(dockId);
        this.setTimestamp(new Date());
    }
}