package businesstier.transferobject;


public class FacilityContent extends Content<FacilityContent>{
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String email;
	private String phone;
	private String url;
	private AddressContent address;
	private double longitude;
	private double latitude;
	private double distance;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public AddressContent getAddress() {
		return address;
	}
	public void setAddress(AddressContent address) {
		this.address = address;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double clientLongitude, double clientLatitude) {
	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(clientLatitude - getLatitude());
	    double lonDistance = Math.toRadians(clientLongitude - getLongitude());
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(getLatitude())) * Math.cos(Math.toRadians(clientLatitude)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		this.distance = Math.round(R * c * 1000 * 100) / 100;
	}
	@Override
	public int compareTo(FacilityContent o) {
	
		return getTitle().compareTo(o.getTitle());
	}
	
	
}
