package businesstier;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import businesstier.transferobject.CategoryContent;
import businesstier.transferobject.PaginatedContent;
import businesstier.transferobject.TypeContent;
import intergrationtier.DomainStore;

public class CategoryFacade extends GenericFacade{
	private CategoryLogic categoryLogic = null;
	private TypeLogic typeLogic = null;
	private static CategoryFacade facade = null;
	private Map<String, List<CategoryContent>> categoryContent = DomainStore.categoryContents;
	private Map<String, List<TypeContent>> typeContents = DomainStore.typeContents;
	
	private CategoryFacade() {
		super();
		categoryLogic = CategoryLogic.getInstance();
		typeLogic = TypeLogic.getInstance();
	}

	public static CategoryFacade getInstance() {
		if (facade == null) {
			facade = new CategoryFacade();
		} 
		return facade;
	}

	public PaginatedContent<CategoryContent> processGetAll(String path,String lang, int page, int size) {
		List<CategoryContent> contents = searchContents(path, categoryContent);
		if (contents == null) {
			 contents = categoryLogic.getAllContents(lang);
		}
		sortByDefault(contents);
		PaginatedContent<CategoryContent> paginatedContent = paginateContents(contents, page,size);
		cacheContents(path, categoryContent, contents);
		return paginatedContent;
	}
	
	public PaginatedContent<TypeContent> processGetTypesWithCategory(String path, String sortBy, String lang, int page, int size) throws IOException{
		List<TypeContent> contents = searchContents(path, typeContents);
		if (contents == null) {
			int categoryId = Integer.parseInt(path.split("/")[1]);
			contents = typeLogic.getContentWithCategory(categoryId, lang);
		}
		
		if (sortBy == null) {
			sortByDefault(contents);
		} else if (sortBy.equals("popular")){
			contents.sort(Comparator.comparingInt(TypeContent::getActivityNum).reversed());
		}
		
		cacheContents(path, typeContents, contents);
		PaginatedContent<TypeContent> paginatedContent = paginateContents(contents, page,size);
		return paginatedContent;
	}


}
