package businesstier.transferobject;

public class TypeContent extends Content<TypeContent> {
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String category;
	private int activityNum;
	
	public TypeContent() {
		
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public int compareTo(TypeContent o) {
		return getTitle().compareTo(o.getTitle());
	}
	public int getActivityNum() {
		return activityNum;
	}
	public void setActivityNum(int activityNum) {
		this.activityNum = activityNum;
	}
	
}
