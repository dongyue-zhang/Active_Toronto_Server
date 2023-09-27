package businesstier;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import businesstier.transferobject.ActivityContent;
import businesstier.transferobject.AvailabilityContent;
import businesstier.transferobject.PaginatedContent;
import businesstier.transferobject.TypeContent;
import intergrationtier.DomainStore;

public class ActivityFacade extends GenericFacade{
	private ActivityLogic activityLogic = null;
	private AvailabilityLogic availabilityLogic = null;
	private static ActivityFacade facade = null;
	private Map<String, List<AvailabilityContent>> activityContents = DomainStore.activityContents;
	
	private ActivityFacade() {
		super();
		activityLogic = ActivityLogic.getInstance();
		availabilityLogic = AvailabilityLogic.getInstance();
	}
	
	public static ActivityFacade getInstance() {
		if (facade == null) {
			facade = new ActivityFacade();
		} 
		return facade;
	}
	
	public AvailabilityContent processGetWithId(String path, String lang, int page, int size) {
	
		int activityId = Integer.parseInt(path.split("/")[1]);
		AvailabilityContent content = availabilityLogic.getContentWithId(activityId,lang);
		

		return content;
	}
	
	public PaginatedContent<AvailabilityContent> processGetAll(String path,String lang, int page, int size) throws IOException {
		List<AvailabilityContent> contents = searchContents(path, activityContents);
		if ( contents == null) {
			contents = activityLogic.getAllContents(lang);
		}
		sortByDefault(contents);
		PaginatedContent<AvailabilityContent> paginatedContents = paginateContents(contents, page, size);
		cacheContents(path, activityContents, contents);
		return paginatedContents;
	}

	public PaginatedContent<AvailabilityContent> processGetAllAvai(String path, String lang, int page, int size) {
		List<AvailabilityContent> contents = activityLogic.getAllAvailabilitiesContents(lang);
		PaginatedContent<AvailabilityContent> paginatedContents = paginateContents(contents, page, size);
		return paginatedContents;
	}


}
