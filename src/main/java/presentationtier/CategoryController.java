package presentationtier;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import businesstier.CategoryFacade;
import businesstier.transferobject.CategoryContent;
import businesstier.transferobject.PaginatedContent;
import businesstier.transferobject.TypeContent;
import intergrationtier.DomainStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryController extends GenericController<CategoryFacade>{

	private static final long serialVersionUID = 1L;
	private final String __GetAll = "GetAll";
	private final String __GetTypeByCategory = "GetTypeByCategory";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		String path = request.getPathInfo();
		String sortBy = request.getParameter("sort");
		
		switch (endpoint) {
			// /categories
			case __GetAll : {
				PaginatedContent<CategoryContent> contents = facade.processGetAll(path, lang, page, size);
				responsePaginatedContent(response, contents);
				break;
			}
			
			// /categories/201/types
			case __GetTypeByCategory: {
				PaginatedContent<TypeContent> contents = facade.processGetTypesWithCategory(path, sortBy, lang, page, size);
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
		facade = CategoryFacade.getInstance();
	}
}
