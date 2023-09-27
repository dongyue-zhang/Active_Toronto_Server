package intergrationtier.dao;

import java.util.List;

import businesstier.transferobject.Type;

public interface TypeDao {
	List<Type> findByCategoryId(int category);
	Type findByDes(String des);
	Type findByTranslationId(int translationId);
	List<Type> findByFacilityId(int facilityId);

}
