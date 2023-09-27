package intergrationtier.datatransferobject;

import java.util.List;

import businesstier.transferobject.Category;
import businesstier.transferobject.Type;

public interface TypeDao {
	List<Type> getAllType();
	List<Type> getTypeByCategory(Category category);

}
