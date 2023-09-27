package intergrationtier.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import businesstier.transferobject.Availability;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

public class AvailabilityDaoImpl extends GenericDaoImpl<Availability> implements AvailabilityDao{

	public AvailabilityDaoImpl() {
		super(Availability.class);
	}

	@Override
	public List<Availability> findByType(String type) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("type", type);
		System.out.println(new Timestamp(new Date().getTime()));
		return super.findResults("Availability_FindByType", parameter);
	}

	@Override
	public List<Availability> findByFacilityAndType(String facility, String type) {
		Collection<Availability> collection = findByType(type);
		return collection.stream()
				.filter(
						availability -> availability.getFacility().getTranslation().getLanguageTranslations().stream().anyMatch(
								r -> r.getDescription().equals(facility)
								)
						)
				.collect(Collectors.toList());
	}

	@Override
	public List<Availability> findByActivity(String activity) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("activity", activity);
		return super.findResults("Availability_FindByActivity", parameter);
	}

	@Override
	public Availability findById(int id) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("id", id);
		return super.findResult("Availability_FindById", parameter);
	}

	@Override
	public List<Availability> findAll() {
		return super.findResults("Availability_FindAll", null);
	}

	@Override
	public List<Availability> findByContaining(String search) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("search", search);
		return super.findResults("Availability_FindByContaining", parameter);
	}

	@Override
	public List<Availability> findByFacility(String facility) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("facility", facility);
		return super.findResults("Availability_FindByFacility", parameter);
	}

}
