package presentationtier;

import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;
import java.nio.charset.StandardCharsets;

public final class Endpoints {
	public static final Map<String, Map<String, String>> endpoints = readEndpointsFromFile();
	
	private static Map<String, Map<String, String>> readEndpointsFromFile()  {
		Map<String, Map<String, String>> endpoints = new HashMap<String, Map<String, String>>();
		try {
			JSONObject jo = new Endpoints().getJSONFromResourceAsStream("Endpoints");
			Iterator<String> servlets = jo.keys();
			while(servlets.hasNext()) {
				String servlet = (String)servlets.next();
				JSONObject endpointsJSON = jo.getJSONObject(servlet);
				Iterator<String> endpointsIt = endpointsJSON.keys();
				Map<String, String> uris = new HashMap<String, String>();
				while(endpointsIt.hasNext()) {
					String endpoint = (String) endpointsIt.next();
					String name = (String) endpointsJSON.getString(endpoint);
					uris.put(endpoint, name);
					
				}
				endpoints.put(servlet, uris);
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return endpoints;
	
	}
    private JSONObject getJSONFromResourceAsStream(String fileName) throws IOException {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        StringBuilder strBuilder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
        	strBuilder.append(line);
        }
        JSONObject jsonObject = new JSONObject(strBuilder.toString());
        
        return jsonObject;

    }
	
//	private static Map<String, Map<String, String>> getendpoints (){
//		Map<String, String> typeendpoints = new HashMap<String, String>() {
//			private static final long serialVersionUID = 1L;
//			{
//				put("/", "GetAllTypes");
//				put("", "GetAllTypes");
//				put("^?sort=popular$", "GetAllTypesSortByPopular");
//				put("^/\\d+/facilities/?$", "GetFacilitiesWithType");
//				put("^/\\d+/activities/?$", "GetActivitiesWithType");
//				put("^/\\d+/activities?sort=time", "GetActivitesWithTypeSortByTime");
//				put("^/\\d+/activities?sort=distance", "GetActivitesWithTypeSortByDistance");
//			}
//
//		};
//		
//		Map<String, String> activityendpoints = new HashMap<String, String>() {
//
//			private static final long serialVersionUID = 1L;
//			{
//				put("/", "GetAll");
//				put("^/\\d+/?", "GetWithId");
//			}
//		};
//		
//		Map<String, String> categoryendpoints = new HashMap<String, String>() {
//
//			private static final long serialVersionUID = 1L;
//			{
//				put("/", "GetAll");
//			}
//		};
//		
//		Map<String, String> facilityendpoints = new HashMap<String, String>() {
//
//			private static final long serialVersionUID = 1L;
//			{
//				put("/", "GetAll");
//				put("^/\\d+/?$", "GetWithId");
//				put("^/\\d+/types/\\d+/?$", "GetWithType");
//				put("^/\\d+/types/?$", "GetAllTypesWithId");
//				put("^/\\d+/types?page=\\d+&size=\\d+$", "GetAllTypesWithIdPagination");
//				put("^/\\d+/types/\\d+/activities/?$", "GetActivitiesWithFacilityAndType");
//			}
//		};
//		
//		Map<String, Map<String, String>> endpoints = new HashMap<String, Map<String, String>>() {
//
//			private static final long serialVersionUID = 1L;
//			{
//				put("/types", typeendpoints);
//				put("/activities", activityendpoints);
//				put("/categories", categoryendpoints);
//				put("/facilities", facilityendpoints);
//				
//			}
//			
//		};
//		
//		return endpoints;
//		
//	}
}
