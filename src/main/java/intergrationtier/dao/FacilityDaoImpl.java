package intergrationtier.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import businesstier.transferobject.Category;
import businesstier.transferobject.CategoryContent;
import businesstier.transferobject.Facility;
import businesstier.transferobject.Type;

public class FacilityDaoImpl extends GenericDaoImpl<Facility> implements FacilityDao {

	public FacilityDaoImpl() {
		super(Facility.class);
	}

	@Override
	public Facility findByDes(String des) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("facility", des);
		return super.findResult("Facility_FindByDes", parameter);
	}

	@Override
	public Facility findById(int id) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("id", id);
		return super.findResult("Facility_FindById", parameter);
	}

	@Override
	public List<Facility> findAll() {
		return super.findResults("Facility_FindAll", null);
	}
	
	public List<Facility>  findByTypeId(int typeId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("typeId", typeId);
		return super.findResults("Facility_FindByTypeId", parameter);
		
	}

	@Override
	public List<Facility> findByContaining(String search) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("search", search);
		return super.findResults("Facility_FindByContaining", parameter);
	}
	
	public List<Category> findCategoriesByFacility(int facilityId) {
		Facility facility = findById(facilityId);
		Set<Category> categorySet = new HashSet<>();
		facility.getFacilityActivities().forEach(i -> {
			categorySet.add(i.getActivity().getType().getCategory());
		});
		return new ArrayList<Category>(categorySet);
	}
	
	public List<Type> findTypesByCategoryAndFacility(int facilityId, int categoryId) {
		Facility facility = findById(facilityId);
		Set<Type> typeSet = new HashSet<>();
		facility.getFacilityActivities().forEach(i -> {
			Type type = i.getActivity().getType();
			if (type.getCategory().getId() == categoryId) {
				typeSet.add(type);
			}
		});
		return new ArrayList<Type>(typeSet);
	}

}
