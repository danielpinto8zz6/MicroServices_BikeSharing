package estg.mtsd.bikeshare.dockmanagement.service.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="dock")
public class Dock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id ;

	@Column(name="latitude")
	private Double latitude ;

	@Column(name="longitude")
	private Double longitude ;

	@Column(name="location")
	private String location ;

	@Column(name="bike_id")
	private Integer bikeId ;

}