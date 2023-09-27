package intergrationtier.dao;

import java.util.List;

import businesstier.transferobject.Address;

public interface AddressDao {
	Address findByFacility(String facility);

}
