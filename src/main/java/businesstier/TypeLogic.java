package businesstier;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import intergrationtier.dao.TypeDaoImpl;
import businesstier.transferobject.Type;
import businesstier.transferobject.TypeContent;

public class TypeLogic extends GenericLogic< TypeContent,Type, TypeDaoImpl> {
	private static TypeLogic typeLogic = null;

	private TypeLogic() {
		super(new TypeDaoImpl());
	}
	
	public static TypeLogic getInstance() {
		if (typeLogic == null) {
			typeLogic = new TypeLogic();
		} 
		return typeLogic;
	}

	@Override
	public List<Type> getAll() {
		return get(() -> dao().findAll());
		
	}
	
	public List<TypeContent> getAllContents(String lang) {
		return getContents(getAll(), lang);
	}

	@Override
	public Type getWithId(int id) {
		return get(() -> dao().findById(id));
	}
	
	public TypeContent getContentWithId(int id, String lang) {
		return getContent(getWithId(id), lang);
	}
	
	public List<Type> getWithCategory(int categoryId){
		return get(() -> dao().findByCategoryId(categoryId));
	}
	
	public List<TypeContent> getContentWithCategory(int categoryId, String lang) {
		return getContents(getWithCategory(categoryId), lang);
	}
	
	public Type getWithDes(String des) {
		return get(() -> dao().findByDes(des));
	}
	
	public TypeContent getContentWithDes(String des, String lang) {
		return getContent(getWithDes(des), lang);
	}
	
	public List<Type> getWithFacilityId(int facilityId) {
		return get(() -> dao().findByFacilityId(facilityId));
	}
	
	public List<TypeContent> getContentWithFacilityId(int facilityId, String lang) {
		return getContents(getWithFacilityId(facilityId), lang);
	}

	@Override
	public List<Type> search(String search) {
		return get(() -> dao().findByContaining(search));
	}

	public List<TypeContent> getContents(List<Type> collection, String lang) {
		List<TypeContent> contents = new ArrayList<>();
		
		for (Type type: collection) {
			TypeContent typeContent = getContent(type, lang);
			contents.add(typeContent);
		}
		
		return contents;
	}

	public TypeContent getContent(Type type, String lang) {
		TypeContent typeContent = new TypeContent();
		typeContent.setId(type.getId());
		
		type.getTranslation().getLanguageTranslations().forEach(i -> {
			if (i.getLanguage().getId().equals(lang)) {
				typeContent.setTitle(i.getDescription());
			}
		});
		
		type.getCategory().getTranslation().getLanguageTranslations().forEach( i -> {
			if (i.getLanguage().getId().equals(lang)) {
				typeContent.setCategory(i.getDescription());
			}
		});
		typeContent.setActivityNum(type.getActivities().size());
		return typeContent;
	}

	public List<TypeContent> getSearching(String searchBy, String lang) {
		
		return getContents(search(searchBy), lang);
	}
	

	
	

}
