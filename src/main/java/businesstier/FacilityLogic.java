package businesstier;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import businesstier.transferobject.Category;
import businesstier.transferobject.Type;
import businesstier.transferobject.CategoryContent;
import businesstier.transferobject.Facility;
import businesstier.transferobject.FacilityContent;
import businesstier.transferobject.TypeContent;
import intergrationtier.dao.FacilityDaoImpl;

public class FacilityLogic extends GenericLogic<FacilityContent, Facility, FacilityDaoImpl>{
	private static FacilityLogic facilityLogic = null;

	private FacilityLogic() {
		super(new FacilityDaoImpl());
	}
	
	public static FacilityLogic getInstance() {
		if (facilityLogic == null) {
			facilityLogic = new FacilityLogic();
		} 
		return facilityLogic;
	}

	@Override
	public List<Facility> getAll() {
		return get(() -> dao().findAll());
	}

	@Override
	public Facility getWithId(int id) {
		return get(() -> dao().findById(id));
	}

	@Override
	public List<Facility> search(String search) {
		return get(() -> dao().findByContaining(search));
	}


	public List<FacilityContent> getAllContents(String lang) {
		return getContents(getAll(), lang);
	}


	public FacilityContent getContentWithId(int id, String lang) {
		return getContent(getWithId(id), lang);
	}
	
	public List<Facility> getWithTypeId(int typeId) {
		return get(() -> dao().findByTypeId(typeId));
	}
	
	public List<FacilityContent> getContentsWithTypeId(int typeId, String lang) {
		return getContents(getWithTypeId(typeId), lang);
	}
	
	public List<Category> getCategoriesWithFacility(int facilityId) {
		return get(() -> dao().findCategoriesByFacility(facilityId));
	}
	
	public List<Type> getTypesWithCategoryAndFacility(int facililityId, int categoryId) {
		return get(() -> dao().findTypesByCategoryAndFacility(facililityId, categoryId));
	}
	
	public List<TypeContent> getTypeContentsWithCategoryAndFacility(int facililityId, int categoryId, String lang) {
		return TypeLogic.getInstance().getContents(getTypesWithCategoryAndFacility(facililityId, categoryId), lang);
	}
	
	public List<CategoryContent> getCategoryContentsWithFacility(int facilityId, String lang) {
		return CategoryLogic.getInstance().getContents(getCategoriesWithFacility(facilityId), lang);
	}

	public List<FacilityContent> getContents(List<Facility> collection, String lang) {
		List<FacilityContent> contents = new ArrayList<>();
		for (Facility facility : collection) {
			contents.add(getContent(facility,lang));
		}
		
		return contents;
	}


	public FacilityContent getContent(Facility full, String lang) {
		FacilityContent facilityContent = new FacilityContent();
		facilityContent.setId(full.getId());
		facilityContent.setLatitude(full.getAddress().getLatitude());
		facilityContent.setLongitude(full.getAddress().getLongitude()); 
		facilityContent.setDistance(ClientSettings.getClientLongitude(), ClientSettings.getClientLatitude() );
		facilityContent.setEmail("test@test.com");
		if (full.getPhone() == null ) {
			facilityContent.setPhone("0000000000000");
		} else {
			facilityContent.setPhone(full.getPhone().replace("-", ""));
		}
		
		full.getTranslation().getLanguageTranslations().forEach(i -> {
			if (i.getLanguage().getId().equals(lang)) {
				facilityContent.setTitle(i.getDescription());
			}
		});
		facilityContent.setUrl(full.getUrl());
		facilityContent.setAddress(AddressLogic.getInstance().getContent(full.getAddress(), lang));
		return facilityContent;
	}

	public List<FacilityContent> getSearching(String searchBy, String lang) {
		return getContents(search(searchBy), lang);
	}

	


}
