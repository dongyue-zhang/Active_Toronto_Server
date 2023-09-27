package businesstier.transferobject;

import java.sql.Timestamp;

public class ActivityContent extends Content<ActivityContent>{


	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String type;
	private String category;
	private String reservationURL;
	private boolean isAvailable = true;
	private String startTime;
	private String endTime;
	private int minAge;
	private int maxAge;
	private String lastUpdated;
	private FacilityContent facility;
	
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getReservationURL() {
		return reservationURL;
	}
	public void setReservationURL(String reservationURL) {
		this.reservationURL = reservationURL;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setSatartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public FacilityContent getFacility() {
		return facility;
	}
	public void setFacility(FacilityContent facility) {
		this.facility = facility;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int compareTo(ActivityContent o) {
		return getTitle().compareTo(o.getTitle());
	}

}
