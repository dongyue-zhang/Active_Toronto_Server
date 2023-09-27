package businesstier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import businesstier.transferobject.ActivityContent;
import businesstier.transferobject.AvailabilityContent;
import businesstier.transferobject.CategoryContent;
import businesstier.transferobject.FacilityContent;
import businesstier.transferobject.PaginatedContent;
import businesstier.transferobject.TypeContent;
import intergrationtier.DomainStore;

public class FacilityFacade extends GenericFacade{
	
	private FacilityLogic facilityLogic = null;
	private TypeLogic typeLogic = null;
	private ActivityLogic activityLogic = null;
	private CategoryLogic categoryLogic = null;
	private static FacilityFacade facade = null;
	private Map<String, List<FacilityContent>> facilityContents = DomainStore.facilityContents;
	private Map<String, List<TypeContent>> typeContents = DomainStore.typeContents;
	private Map<String, List<AvailabilityContent>> activityContents = DomainStore.activityContents;
	private Map<String, List<CategoryContent>> categoryContents = DomainStore.categoryContents;
			
	private FacilityFacade() {
		super();
		this.facilityLogic = FacilityLogic.getInstance();
		this.typeLogic = TypeLogic.getInstance();
		this.activityLogic = ActivityLogic.getInstance();
		this.categoryLogic = CategoryLogic.getInstance();
	}
	
	public static FacilityFacade getInstance() {
		if (facade == null) {
			facade = new FacilityFacade();
		} 
		return facade;
	}
	
	
	public PaginatedContent<FacilityContent> processGetWithType(String path, String lang, int page, int size) throws IOException {
		List<FacilityContent> contents = searchContents(path, facilityContents);
		if ( contents == null) {
			int typeId = Integer.parseInt(path.split("/")[3]);
			contents =  facilityLogic.getContentsWithTypeId(typeId, lang);
		}
		sortByDefault(contents);
		PaginatedContent<FacilityContent> paginatedContents = paginateContents(contents, page, size);
		cacheContents(path, facilityContents, contents);
		return paginatedContents;
	
	}
	
	public FacilityContent processGetWithId(String path, String lang, int page, int size) throws IOException {
		int facilityId = Integer.parseInt(path.split("/")[1]);
		FacilityContent content = facilityLogic.getContentWithId(facilityId, lang);
		return content;
	}
	
	public PaginatedContent<TypeContent> processGetAllTypesWithId(String path, String lang, int page, int size) throws IOException {
		List<TypeContent> contents = searchContents(path, typeContents);
		if ( contents == null) {
			int facilityId = Integer.parseInt(path.split("/")[1]);
			contents =  typeLogic.getContentWithFacilityId(facilityId, lang);
		}
		sortByDefault(contents);
		PaginatedContent<TypeContent> paginatedContents = paginateContents(contents, page, size);
		cacheContents(path, typeContents, contents);
		return paginatedContents;
	}
	
	public PaginatedContent<FacilityContent> processGetAll(String path, String sortBy, String lang, int page, int size) throws IOException {
		List<FacilityContent> contents = searchContents(path, facilityContents);
		if ( contents == null) {
			contents = facilityLogic.getAllContents(lang);
			cacheContents(path, facilityContents, contents);
		}
		
		if (sortBy.equals("distance")) {
			contents.sort(Comparator.comparingDouble(FacilityContent::getDistance));
		} else if (sortBy.equals("title")) {
			sortByDefault(contents);
		}
		
		PaginatedContent<FacilityContent> paginatedContents = paginateContents(contents, page, size);
		
		
		return paginatedContents;
		
	}
	
	public PaginatedContent<AvailabilityContent> processGetActivitiesWithFacilityAndType(String path, String time, String lang, int page, int size) throws IOException {
		List<AvailabilityContent> contents = searchContents(path, activityContents);
		if (contents == null) {
			int facilityId = Integer.parseInt(path.split("/")[1]);
			int typeId = Integer.parseInt(path.split("/")[3]);
			contents = activityLogic.getContentsWithFacilityIdAndTypeId(facilityId, typeId, lang);
			cacheContents(path, activityContents, contents);
		}
		sortByDefault(contents);
		if (!time.isEmpty()) {
			contents = filterAvailabilityContentByTime(time, contents);
		}
		PaginatedContent<AvailabilityContent> paginatedContents = paginateContents(contents, page, size);
		return paginatedContents;
		
	}
	
	public PaginatedContent<CategoryContent> processGetCategoriesByFacility(String path, String lang, int page, int size) throws IOException {
		int facilityId = Integer.parseInt(path.split("/")[1]);
		List<CategoryContent> contents = facilityLogic.getCategoryContentsWithFacility(facilityId, lang);
		sortByDefault(contents);
		PaginatedContent<CategoryContent> paginatedContents = paginateContents(contents, page, size);
		cacheContents(path, categoryContents, contents);
		return paginatedContents;
	}

	public PaginatedContent<TypeContent> processGetTypesByFacilityAndCategory(String path, String lang, int page,
			int size) {
		List<TypeContent> contents = searchContents(path, typeContents);
		if (contents == null) {
			int facilityId = Integer.parseInt(path.split("/")[1]);
			int categoryId = Integer.parseInt(path.split("/")[3]);
			contents = facilityLogic.getTypeContentsWithCategoryAndFacility(facilityId, categoryId, lang);
		}
		sortByDefault(contents);
		PaginatedContent<TypeContent> paginatedContents = paginateContents(contents, page, size);
		cacheContents(path, typeContents, contents);
		return paginatedContents;
	}

	public PaginatedContent<FacilityContent> processSearching(String searchBy, String lang, int page, int size) {
		List<FacilityContent> contents = facilityLogic.getSearching(searchBy, lang);
		PaginatedContent<FacilityContent> paginatedContents = paginateContents(contents, page, size);
		return paginatedContents;
	}




}
