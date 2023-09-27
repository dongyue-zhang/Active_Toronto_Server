package businesstier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import businesstier.transferobject.AvailabilityContent;
import businesstier.transferobject.PaginatedContent;
import intergrationtier.DomainStore;

public abstract class GenericFacade{
	
	protected GenericFacade () {}
	
	protected <E> PaginatedContent<E> paginateContents(List<E> contents, int page, int size) {
		List<E> paginatedList = new ArrayList<>();
		boolean last = false;
		int totalPage = 0;
		int offset = page * size;
		
        if(contents.size()>offset){
            if(contents.size()>(offset+size)){
                paginatedList = contents.subList(offset, offset+size);
                
            }else{
                paginatedList = contents.subList(offset, contents.size());
                last = true;
            }
            totalPage = (int) Math.ceil(contents.size() / size)+1;
        }
        PaginatedContent<E> paginatedContent = new PaginatedContent<E>(paginatedList);
        paginatedContent.setPage(page);
        paginatedContent.setSize(size);
        paginatedContent.setOffset(offset);
        paginatedContent.setLast(last);
        paginatedContent.setTotalPages(totalPage);
		
		return paginatedContent;
		
	}
	
	protected <E extends Comparable<E>> void sortByDefault(List<E> list) {
		Collections.sort(list);
	}
	
	protected <E> void cacheContents(String path, Map<String, List<E>> contents, List<E> results) {
		path = cleanPath(path);
		DomainStore.saveResults(path,contents, results);
	}
	
	protected <E> List<E> searchContents(String path, Map<String, List<E>> contents) {
		path = cleanPath(path);
		return DomainStore.searchResults(path, contents);
	}
	
	protected String cleanPath(String path) {
		if (path == null) {
			path = "";
		}
		
		if (path.endsWith("/")) {
			path = path.substring(0,path.length() - 1);
		}
		
		return path;
	}
	
	protected List<AvailabilityContent> filterAvailabilityContentByTime(String time, List<AvailabilityContent> contents) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
			contents = contents.stream()
					.filter(availabilityContent -> 
						LocalDateTime.parse(availabilityContent.getStartTime(), formatter).getDayOfYear() == dateTime.getDayOfYear()
					)
					.collect(Collectors.toList());
			
			if (dateTime.getHour() != 0) {
				contents = contents.stream()
						.filter(availabilityContent ->  
						LocalDateTime.parse(availabilityContent.getStartTime(), formatter).getHour() == dateTime.getHour()
						)
						.collect(Collectors.toList());
			}
		
		return contents;
	}
	
	

}
