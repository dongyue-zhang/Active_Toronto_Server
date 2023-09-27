package intergrationtier.dao;


import java.util.List;

import businesstier.transferobject.Availability;

public interface AvailabilityDao {
	List<Availability> findByType(String type);
	List<Availability> findByFacilityAndType(String facility, String type);
	List<Availability> findByActivity(String activity); //for search
	List<Availability> findByFacility(String facility);
	
}
