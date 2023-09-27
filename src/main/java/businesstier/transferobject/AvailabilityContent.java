package businesstier.transferobject;

public class AvailabilityContent extends Content<AvailabilityContent>{
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
	public boolean getIsAvailable() {
		return isAvailable;
	}

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String satartTime) {
		this.startTime = satartTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
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
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
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
	public FacilityContent getFacility() {
		return facility;
	}
	public void setFacility(FacilityContent facility) {
		this.facility = facility;
	}
	@Override
	public int compareTo(AvailabilityContent o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
