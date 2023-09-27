package intergrationtier.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import businesstier.transferobject.Activity;
import businesstier.transferobject.Availability;

public class ActivityDaoImpl extends GenericDaoImpl<Activity> implements ActivityDao{

	public ActivityDaoImpl() {
		super(Activity.class);
	}

	@Override
	public List<Activity> findByFacility(String facility) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("facility", facility);
		return super.findResults("Activity_FindByFacility", parameter);
	}

	@Override
	public List<Activity> findByType(String type) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("type", type);
		return super.findResults("Activity_FindByType", parameter);
	}

	@Override
	public List<Activity> findByFacilityAndType(String facility, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity findById(int id) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("id", id);
		return super.findResult("Activity_FindById", parameter);
	}

	@Override
	public List<Activity> findAll() {
		List<Activity> list = super.findResults("Activity_FindAll", null);
//		try {
//			Timestamp ts = Timestamp.valueOf("2023-06-01 00:00:00");
			//Timestamp ts = new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-06-01 00:00:00").getTime());
//			list.forEach(
//					activity -> activity.getAvailabilities().removeIf(i -> i.getStartTime().after(ts)));
//						List<Availability> filteredList = activity.getAvailabilities()
//						.stream()
//						.filter(avai -> avai.getStartTime().after(ts))
//						.collect(Collectors.toList());
//						
//						activity.setAvailabilities(new HashSet<Availability>(filteredList));
//					}
//							
//					);
//			list.stream()
//				.filter(activity -> activity)
//			list.stream()
//				.collect(Collectors.groupingBy(activity -> activity.getAvailabilities()))
//				.flatMap(activity -> activity.getAvailabilities().stream())
//				.filter(availability -> availability.getStartTime().after(ts))
//				.collect()
//				;
//			return list.stream()
//				.map(activity -> {
//						Set<Availability> availabilities = activity.getAvailabilities().stream().filter(availability -> availability.getStartTime().after(ts)).collect(Collectors.toSet());
//						activity.setAvailabilities(availabilities);
//						return activity;
//					}
//				)
//				.collect(Collectors.toList());
			
//			for (Activity activity:list) {
//				activity.getAvailabilities().forEach(i -> System.out.println(i.getStartTime().toString()));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return list;
		
	}

	@Override
	public List<Activity> findByContaining(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> findByTypeId(int typeId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("typeId", typeId);
		return super.findResults("Activity_FindByTypeId", parameter);
	}

	@Override
	public List<Activity> findByFacilityIdAndTypeId(int facilityId, int typeId) {
		List<Activity> activities = findByTypeId(typeId);
		
		return activities.stream()
				.filter(
						activity -> activity.getFacilityActivities().stream().anyMatch(
								fa -> fa.getFacility().getId() == facilityId 
								)
						)
				.collect(Collectors.toList());
	}

}
