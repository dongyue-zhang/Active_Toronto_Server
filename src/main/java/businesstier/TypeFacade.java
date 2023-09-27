package businesstier;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import businesstier.transferobject.ActivityContent;
import businesstier.transferobject.AvailabilityContent;
import businesstier.transferobject.FacilityContent;
import businesstier.transferobject.PaginatedContent;
import businesstier.transferobject.TypeContent;
import intergrationtier.DomainStore;

public class TypeFacade extends GenericFacade{
	private TypeLogic typeLogic = null;
	private FacilityLogic facilityLogic = null;
	private ActivityLogic activityLogic = null;
	private static TypeFacade facade = null;
	private Map<String, List<TypeContent>> typeContents = DomainStore.typeContents;
	private Map<String, List<FacilityContent>> facilityContents = DomainStore.facilityContents;
	private Map<String, List<AvailabilityContent>> activityContents = DomainStore.activityContents;
	
	private TypeFacade() {
		super();
		typeLogic = TypeLogic.getInstance();
		facilityLogic = FacilityLogic.getInstance();
		activityLogic = ActivityLogic.getInstance();
	}
	
	public static TypeFacade getInstance() {
		if (facade == null) {
			facade = new TypeFacade();
		} 
		return facade;
	}
	
	public PaginatedContent<TypeContent> processGetAll(String path, String sortBy, String lang, int page, int size) throws IOException {
		List<TypeContent> contents = searchContents(path, typeContents);
		if ( contents == null) {
			contents = typeLogic.getAllContents(lang);
		}

		if (sortBy == null) {
			sortByDefault(contents);
		} else if (sortBy.equals("popular")) {
			contents.sort(Comparator.comparingInt(TypeContent::getActivityNum).reversed());
		}
		cacheContents(path, typeContents, contents);
		PaginatedContent<TypeContent> paginatedContent = paginateContents(contents, page,size);
		return paginatedContent;
	}
	
	
	public PaginatedContent<FacilityContent> processGetFacilitiesWithType(String path, String lang, int page, int size) throws IOException {
		List<FacilityContent> contents = searchContents(path, facilityContents);
		if (contents == null) {
			int typeId = Integer.parseInt(path.split("/")[1]);
			contents = facilityLogic.getContentsWithTypeId(typeId, lang);
		}
		
		sortByDefault(contents);
		PaginatedContent<FacilityContent> paginatedContent = paginateContents(contents, page,size);
		cacheContents(path, facilityContents, contents);
		return paginatedContent;
	}
	
	public PaginatedContent<AvailabilityContent> processGetActivitesWithType(String path, String sortBy, String time, String lang, int page, int size) throws IOException {
		List<AvailabilityContent> contents = searchContents(path, activityContents);
		if (contents == null) {
			int typeId = Integer.parseInt(path.split("/")[1]);
			contents = activityLogic.getContentsWithTypeId(typeId, lang);
			cacheContents(path, activityContents, contents);
		}
		
		if (sortBy == null) {
			sortByDefault(contents);
		} else if (sortBy.equals("time")) {
			contents.sort(Comparator.comparing(AvailabilityContent::getStartTime));
		} else if (sortBy.equals("distance")) {
			Comparator<AvailabilityContent> comparator = Comparator.comparingDouble((AvailabilityContent content) -> content.getFacility().getDistance()).thenComparing(AvailabilityContent::getTitle);
			contents.sort(comparator);
		}
		
//		if (time != null) {
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//			LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
//			contents = contents.stream()
//					.filter(availabilityContent -> 
//						LocalDateTime.parse(availabilityContent.getStartTime(), formatter).getDayOfYear() == dateTime.getDayOfYear()
//					)
//					.collect(Collectors.toList());
//			
//			if (dateTime.getHour() != 0) {
//				contents = contents.stream()
//						.filter(availabilityContent ->  
//						LocalDateTime.parse(availabilityContent.getStartTime(), formatter).getHour() == dateTime.getHour()
//						)
//						.collect(Collectors.toList());
//			}
//		}
		
		if (!time.isEmpty()) {
			contents = filterAvailabilityContentByTime(time, contents);
		}
		PaginatedContent<AvailabilityContent> paginatedContent = paginateContents(contents, page,size);
		return paginatedContent;
	}

	public PaginatedContent<TypeContent> processSearching(String searchBy, String lang, int page, int size) {
		List<TypeContent> contents = typeLogic.getSearching(searchBy, lang);
		PaginatedContent<TypeContent> paginatedContent = paginateContents(contents, page,size);
		return paginatedContent;
	}


		

}
