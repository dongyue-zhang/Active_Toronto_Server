package intergrationtier.datatransferobject;

import java.util.List;

import businesstier.transferobject.Availability;
import businesstier.transferobject.Facility;
import businesstier.transferobject.Type;

public interface AvailabilityDao {
	List<Availability> getAllAvailability();
	List<Availability> getAvailabilityByType(Type type);
	List<Availability> getAvailabilityByFacilityAndType(Facility facility, Type type);
	

}
