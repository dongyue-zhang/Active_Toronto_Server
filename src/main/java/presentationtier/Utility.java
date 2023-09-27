package presentationtier;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import businesstier.transferobject.PaginatedContent;
import presentationtier.Endpoints;
import jakarta.servlet.http.HttpServletResponse;

public class Utility {
	public static <T> void responseJSON (HttpServletResponse response, List<T> contents) throws IOException {
		JSONArray jsonArray = new JSONArray(contents);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonArray.toString());
	}
	
	public static <T> void responseJSON (HttpServletResponse response, T content) throws IOException {
		JSONObject jsonObject = new JSONObject(content);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonObject.toString());
	}
	
	public static <T> void responsePaginatedContent (HttpServletResponse response, PaginatedContent<T> contents) throws IOException {
		JSONObject jsonObject = new JSONObject(contents);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonObject.toString());
	}
	
	public static <T extends Comparable<T>> void sortByDefault(List<T> list) {
		Collections.sort(list);
	}
	
	public static String getEndpointName(String servletRoot, String servletUri){
		if (servletUri == null) {
			servletUri = "";
		}
		Map<String, Map<String, String>> endpoints = Endpoints.endpoints;
		Map<String, String> uris = endpoints.get(servletRoot);
		for (String uri:uris.keySet()) {
			if (servletUri.matches(uri)) {
				return uris.get(uri);
			}
		}
		
		return null;
	}


}
