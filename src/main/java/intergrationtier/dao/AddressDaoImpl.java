package intergrationtier.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import businesstier.transferobject.Address;

public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao{

	public AddressDaoImpl() {
		super(Address.class);
	}

	@Override
	public Address findByFacility(String facility) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("facility", facility);
		return super.findResult("Address_FindByFacility", parameter);
	}

	@Override
	public Address findById(int id) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("id", id);
		return super.findResult("Address_FindById", parameter);
	}

	@Override
	public List<Address> findAll() {
		return super.findResults("Address_FindAll", null);
	}

	@Override
	public List<Address> findByContaining(String search) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("search", search);
		return super.findResults("Address_FindByContaining", parameter);
	}
	
}
