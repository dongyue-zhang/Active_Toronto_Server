package intergrationtier;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import businesstier.transferobject.FacilityContent;
import businesstier.transferobject.TypeContent;
import businesstier.transferobject.ActivityContent;
import businesstier.transferobject.CategoryContent;
import businesstier.transferobject.AvailabilityContent;


public class DomainStore {
	public static Map<String, List<TypeContent>> typeContents = new HashMap<>();
	public static Map<String, List<FacilityContent>> facilityContents = new HashMap<>();
	public static Map<String, List<AvailabilityContent>> activityContents = new HashMap<>();
	public static Map<String, List<CategoryContent>> categoryContents = new HashMap<>();
	
	public static <T> List<T> searchResults(String path, Map<String, List<T>> results) {
		if (results.containsKey(path)) {
			return results.get(path);
		}
		return null;
		
	}
	
	public static <T> void saveResults(String path, Map<String, List<T>> contents, List<T> results) {
		if (!contents.containsKey(path)) {
			contents.put(path, results);
		}

	}

}
