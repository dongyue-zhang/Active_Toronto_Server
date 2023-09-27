package businesstier.transferobject;

import java.io.Serializable;

public class CategoryContent implements Serializable, Comparable<CategoryContent>{

	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public int compareTo(CategoryContent o) {
		
		return getTitle().compareTo(o.getTitle());
	}
	
	
}
