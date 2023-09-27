package intergrationtier.dao;

import businesstier.transferobject.Category;

public interface CategoryDao {
	Category findByDes(String des);

}
