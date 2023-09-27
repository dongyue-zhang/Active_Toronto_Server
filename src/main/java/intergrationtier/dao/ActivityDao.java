package intergrationtier.dao;

import java.util.List;

import businesstier.transferobject.Activity;

public interface ActivityDao {
	List<Activity> findByFacility(String facitliy);
	List<Activity> findByType(String type);
	List<Activity> findByTypeId(int id);
	List<Activity> findByFacilityAndType(String facility, String type);
	List<Activity> findByFacilityIdAndTypeId(int facilityId, int typeId);

}
