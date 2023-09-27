package presentationtier;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;

import businesstier.ActivityFacade;
import businesstier.transferobject.AvailabilityContent;
import businesstier.transferobject.PaginatedContent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActivityController extends GenericController<ActivityFacade>{

	private static final long serialVersionUID = 1L;
	private final String __GetAll = "GetAll";
	private final String __GetWithId = "GetWithId";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		String path = request.getPathInfo();
		
		switch(endpoint) {
			// /activities
			case __GetAll: {
				PaginatedContent<AvailabilityContent> contents = facade.processGetAll(path, lang, page, size);
				responsePaginatedContent(response, contents);
				break;
			}
			// /activities/20001
			case __GetWithId: {
				AvailabilityContent content = facade.processGetWithId(path,lang, page, size);
				responseJSON(response, content);
				break;
			}
			
			case "GetAllAvailabilities": {
				PaginatedContent<AvailabilityContent> contents = facade.processGetAllAvai(path, lang, page, size);
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
		facade = ActivityFacade.getInstance();
	}

}
