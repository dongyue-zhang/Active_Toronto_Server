package intergrationtier.dao;

import businesstier.transferobject.Facility;

public interface FacilityDao {
	Facility findByDes(String des); //for search

}
