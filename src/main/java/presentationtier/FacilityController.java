package presentationtier;

import businesstier.FacilityFacade;
import businesstier.transferobject.AvailabilityContent;
import businesstier.transferobject.CategoryContent;
import businesstier.transferobject.FacilityContent;
import businesstier.transferobject.TypeContent;
import businesstier.transferobject.PaginatedContent;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacilityController extends GenericController<FacilityFacade>{

	private static final long serialVersionUID = 1L;
	private final String __GetTypesByFacilityAndCategory = "GetTypesByFacilityAndCategory";
	private final String __GetAll = "GetAll";
	private final String __GetWithId = "GetWithId";
	private final String __GetWithType = "GetWithType";
	private final String __GetAllTypesWithId = "GetAllTypesWithId";
	private final String __GetActivitiesWithFacilityAndType = "GetActivitiesWithFacilityAndType";
	private final String __GetCategoriesByFacility = "GetCategoriesByFacility";
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		String path = request.getPathInfo();
		
		switch(endpoint) {
			// /facilities
			case __GetAll: {
				
				String searchBy = request.getParameter("q");
				PaginatedContent<FacilityContent> contents = null;
				if (searchBy != "") {
					contents = facade.processSearching(searchBy, lang, page, size);
				} else {
					contents = facade.processGetAll(path, request.getParameter("sort"), lang, page, size);
					
				}
				responsePaginatedContent(response, contents);
				break;
//				PaginatedContent<FacilityContent> contents = facade.processGetAll(path, lang, page, size);
//				responsePaginatedContent(response, contents);
//				break;
			}
			
			// /facilities/2001
			case __GetWithId: {
				FacilityContent content = facade.processGetWithId(path, lang, page, size);
				responseJSON(response, content);
				break;
			}
			
			// /facilities/2001/types/2001 NOT USED!!
			case __GetWithType: {
				PaginatedContent<FacilityContent> contents = facade.processGetWithType(path, lang, page, size);
				responsePaginatedContent(response, contents);
				break;
			}
			
			// /facilities/2001/types/
			case __GetAllTypesWithId : {
				PaginatedContent<TypeContent> contents = facade.processGetAllTypesWithId(path, lang, page, size);
				responsePaginatedContent(response, contents);
				break;
			}
			
			
			// /facilities/2001/types/20001/activities
			case __GetActivitiesWithFacilityAndType: {
				PaginatedContent<AvailabilityContent> contents = facade.processGetActivitiesWithFacilityAndType(path,request.getParameter("time"), lang, page, size);
				responsePaginatedContent(response, contents);
				break;
			}
			
			// /facilities/2001/categories
			case __GetCategoriesByFacility: {
				PaginatedContent<CategoryContent> contents = facade.processGetCategoriesByFacility(path, lang, page, size);
				responsePaginatedContent(response, contents);
				break;
			}
			
			// /facilities/2004/categories/203/types
			case __GetTypesByFacilityAndCategory: {
				PaginatedContent<TypeContent> contents = facade.processGetTypesByFacilityAndCategory(path, lang, page, size);
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
		facade = FacilityFacade.getInstance();
	}


}
