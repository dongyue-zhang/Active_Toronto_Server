package presentationtier;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import businesstier.TypeFacade;
import businesstier.transferobject.FacilityContent;
import businesstier.transferobject.PaginatedContent;
import businesstier.transferobject.TypeContent;
import businesstier.transferobject.AvailabilityContent;


public class TypeController extends GenericController<TypeFacade>{

	private static final long serialVersionUID = 1L;
	private final String __GetAllTypes = "GetAllTypes";
	private final String __GetFacilitiesWithType = "GetFacilitiesWithType";
	private final String __GetActivitiesWithType = "GetActivitiesWithType";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		String path = request.getPathInfo();
		
		switch(endpoint) {
			// /types
			case __GetAllTypes:
				{	
					String searchBy = request.getParameter("q");
					PaginatedContent<TypeContent> contents = null;
					if (searchBy != null) {
						contents = facade.processSearching(searchBy, lang, page, size);
					} else {
						contents = facade.processGetAll(path, request.getParameter("sort"), lang, page, size);
						
					}
					responsePaginatedContent(response, contents);
					break;
				}			
			
			// /types/2001/facilities
			case __GetFacilitiesWithType:
				{
					PaginatedContent<FacilityContent> contents = facade. processGetFacilitiesWithType(path, lang, page, size);
					responsePaginatedContent(response, contents);
					break;
				}

			// /types/2001/activities
			case __GetActivitiesWithType:
				{
					PaginatedContent<AvailabilityContent> contents = facade.processGetActivitesWithType(path, request.getParameter("sort"), request.getParameter("time"), lang, page, size);
					responsePaginatedContent(response, contents);
					break;
				}
			
			default:
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				break;
		}
		
		LogManager.shutdown();
	}
	
	@Override
	public void init() {
		facade = TypeFacade.getInstance();
	}
		
		
}
	


