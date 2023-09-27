package intergrationtier.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import businesstier.transferobject.Category;
import businesstier.transferobject.Facility;

public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao{

	public Object findByFacility;

	public CategoryDaoImpl() {
		super(Category.class);
	}

	@Override
	public Category findById(int id) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("id", id);
		return super.findResult("Category_FindById", parameter);
	}

	@Override
	public List<Category> findAll() {
		return super.findResults("Category_FindAll", null);
	}

	@Override
	public Category findByDes(String des) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("des", des);
		return super.findResult("Category_FindByDes", parameter);
	}

	@Override
	public List<Category> findByContaining(String search) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("search", search);
		return super.findResults("Category_FindByContaining", parameter);
	}
	
	public List<Category> findByFacility(int facilityId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("facilityId", facilityId);
		return super.findResults("Category_FindByFacility", parameter);
	}


}
