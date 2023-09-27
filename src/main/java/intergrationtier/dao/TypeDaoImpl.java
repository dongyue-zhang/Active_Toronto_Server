package intergrationtier.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import businesstier.transferobject.Type;

public class TypeDaoImpl extends GenericDaoImpl<Type> implements TypeDao{

	public TypeDaoImpl() {
		super(Type.class);
	}

	@Override
	public List<Type> findByCategoryId(int categoryId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("categoryId", categoryId);
		return super.findResults("Type_FindByCategoryId", parameter);
	}

	@Override
	public Type findByDes(String des) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("des", des);
		return super.findResult("Type_FindByDes", parameter);
	}


	@Override
	public Type findByTranslationId(int translationId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("id", translationId);
		return super.findResult("Type_FindByTranslationId", parameter);
	}

	@Override
	public Type findById(int id) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("id", id);
		return super.findResult("Type_FindById", parameter);
	}

	@Override
	public List<Type> findAll() {
		return super.findResults("Type_FindAll", null);
	}

	@Override
	public List<Type> findByContaining(String search) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("search", search);
		return super.findResults("Type_FindByContaining", parameter);
	}

	@Override
	public List<Type> findByFacilityId(int facilityId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("facilityId", facilityId);
		return super.findResults("Type_FindByFacilityId", parameter);
	}

}
