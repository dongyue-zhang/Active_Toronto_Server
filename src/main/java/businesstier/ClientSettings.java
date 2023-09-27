package businesstier;

public class ClientSettings {
	private static double clientLongitude = -79.384219;
	private static double clientLatitude = 43.654686;
	private static String lang = "En";
	
	public static String getLang() {
		return lang;
	}
	public static void setLang(String lang) {
		ClientSettings.lang = lang;
	}
	public static double getClientLongitude() {
		return clientLongitude;
	}
	public static void setClientLongitude(double longitude) {
		clientLongitude = longitude;
	}
	public static double getClientLatitude() {
		return clientLatitude;
	}
	public static void setClientLatitude(double latitude) {
		clientLatitude = latitude;
	}

	
	

}
