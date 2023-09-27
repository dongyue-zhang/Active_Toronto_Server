package businesstier;

import java.util.List;
import java.util.ArrayList;

import businesstier.transferobject.Category;
import businesstier.transferobject.CategoryContent;
import intergrationtier.dao.CategoryDaoImpl;

public class CategoryLogic extends GenericLogic<CategoryContent, Category, CategoryDaoImpl>{
	
	private static CategoryLogic categoryLogic = null;

	private CategoryLogic() {
		super(new CategoryDaoImpl());
	}
	
	public static CategoryLogic getInstance() {
		if (categoryLogic == null) {
			categoryLogic = new CategoryLogic();
		} 
		return categoryLogic;
	}

	@Override
	public List<Category> getAll() {
		return get(() -> dao().findAll());
	}
	
	public List<CategoryContent> getAllContents(String lang) {
		return getContents(getAll(), lang);
	}

	@Override
	public Category getWithId(int id) {
		return get(() -> dao().findById(id));
	}
	
	public Category getWithDes(String category) {
		return get(() -> dao().findByDes(category));
	}

	@Override
	public List<Category> search(String search) {
		return get(() -> dao().findByContaining(search));
	}
	
	public CategoryContent getContent(Category full, String lang) {
		CategoryContent content = new CategoryContent();
		content.setId(full.getId());
		full.getTranslation().getLanguageTranslations().forEach(i -> {
			if (i.getLanguage().getId().equals(lang)) {
				content.setTitle(i.getDescription());
			}
		});
		return content;
		
	}
	
	public List<CategoryContent> getContents(List<Category> collection, String lang) {
		List<CategoryContent> contents = new ArrayList<>();
		for (Category category : collection) {
			contents.add(getContent(category, lang));
		}
		return contents;
	}
	

	


}
