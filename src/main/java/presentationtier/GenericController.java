package presentationtier;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import businesstier.ClientSettings;
import businesstier.transferobject.PaginatedContent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GenericController<T> extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected T facade = null;
	protected int page = 0;
	protected int size = 5;
	protected String lang = ClientSettings.getLang();
	protected String endpoint = null;
	
//	protected void setPage(HttpServletRequest request) {
//		if (request.getParameter("page") != null) {
//			this.page = Integer.parseInt(request.getParameter("page"));
//		}
//	}
//	
//	protected void setSize(HttpServletRequest request) {
//		if (request.getParameter("size") != null) {
//			this.size = Integer.parseInt(request.getParameter("size"));
//		}
//	}
	
	protected void setPageSizeEndpointLatLng(HttpServletRequest request) {
		if (request.getParameter("page") != null) {
			this.page = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameter("size") != null) {
			this.size = Integer.parseInt(request.getParameter("size"));
		}
		this.endpoint = getEndpointName(request.getServletPath(), request.getPathInfo());
		if (request.getParameter("lat") != null) {
			ClientSettings.setClientLatitude(Double.parseDouble(request.getParameter("lat")));
		}
		if (request.getParameter("lng") != null) {
			ClientSettings.setClientLatitude(Double.parseDouble(request.getParameter("lng")));
		}
		System.out.println(request.getQueryString());
	}
	
	
	
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
	
	protected <E> void responsePaginatedContent (HttpServletResponse response, PaginatedContent<E> contents) throws IOException {
		JSONObject jsonObject = new JSONObject(contents);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonObject.toString());
	}
	

	
	protected String getEndpointName(String servletRoot, String servletUri){
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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		setPageSizeEndpointLatLng(request);
	}
	
	
	
}
