package businesstier;

import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import businesstier.transferobject.Activity;
import businesstier.transferobject.ActivityContent;
import businesstier.transferobject.Availability;
import businesstier.transferobject.AvailabilityContent;
import businesstier.transferobject.Facility;
import businesstier.transferobject.LanguageTranslation;
import intergrationtier.dao.ActivityDaoImpl;

public class ActivityLogic extends GenericLogic <ActivityContent, Activity, ActivityDaoImpl>{
	private static ActivityLogic activityLogic = null;

	private ActivityLogic() {
		super(new ActivityDaoImpl());
	}
	
	public static ActivityLogic getInstance() {
		if (activityLogic == null) {
			activityLogic = new ActivityLogic();
		} 
		return activityLogic;
	}

	@Override
	public List<Activity> getAll() {
		return get(() -> dao().findAll());
	}


	public List<AvailabilityContent> getAllContents(String lang) {
		return getContents(getAll(), lang);
	}

	@Override
	public Activity getWithId(int id) {
		Activity act = get(() -> dao().findById(id));
		return act;
	}


	public List<AvailabilityContent> getContentWithId(int id, String lang) {
		return getContent(getWithId(id), lang);
	}
	
	public List<Activity> getWithFacility(String facility) {
		return get(() -> dao().findByFacility(facility));
	}
	
	public List<Activity> getWithTypeId(int typeId) {
		return get(() -> dao().findByTypeId(typeId));
	}
	
	public List<AvailabilityContent> getContentsWithTypeId(int typeId, String lang) {
		return getContents(getWithTypeId(typeId), lang);
	}
	
	public List<Activity> getWithFacilityIdAndTypeId(int facilityId, int typeId){
		return get(() -> dao().findByFacilityIdAndTypeId(facilityId, typeId));
	}
	
	public List<AvailabilityContent> getContentsWithFacilityIdAndTypeId(int facilityId, int typeId, String lang) {
		return getContents(getWithFacilityIdAndTypeId(facilityId, typeId), lang);
	}

	@Override
	public List<Activity> search(String search) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<AvailabilityContent> getContents(List<Activity> collection, String lang) {
		List<AvailabilityContent> contents = new ArrayList<>();
		for (Activity activity : collection) {
			contents.addAll(getContent(activity, lang));
		}
		return contents; 
	}

	public List<AvailabilityContent> getContent(Activity full, String lang) {
		List<AvailabilityContent> contents = new ArrayList<>();
//		String title = null;
//		Iterator<LanguageTranslation> it = full.getTranslation().getLanguageTranslations().iterator();
//		while (it.hasNext()) {
//			LanguageTranslation lt = it.next();
//			if (lt.getLanguage().getId().equals(lang)) {
//				title = lt.getDescription();
//			}
//		}
//
//		String type = null;
//		it = full.getType().getTranslation().getLanguageTranslations().iterator();
//		while (it.hasNext()) {
//			LanguageTranslation lt = it.next();
//			if (lt.getLanguage().getId().equals(lang)) {
//				type = lt.getDescription();
//			}
//		}
//		String category = null;
//		it = full.getType().getCategory().getTranslation().getLanguageTranslations().iterator();
//		while (it.hasNext()) {
//			LanguageTranslation lt = it.next();
//			if (lt.getLanguage().getId().equals(lang)) {
//				category = lt.getDescription();
//			}
//		}
		
		for (Availability ava : full.getAvailabilities()) {
			Timestamp ts = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT));
			if (ava.getStartTime().after(ts)) {
				AvailabilityContent activityContent = AvailabilityLogic.getInstance().getContent(ava, lang);
				contents.add(activityContent);
			}
			
//			activityContent.setId(ava.getId());
//			activityContent.setTitle(title);
//			activityContent.setType(type);
//			activityContent.setCategory(category);
//			activityContent.setReservationURL(ava.getFacility().getUrl());
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//			activityContent.setSatartTime(dateFormat.format(ava.getStartTime()));
//			activityContent.setEndTime(dateFormat.format(ava.getEndTime()));
//			if (ava.getMaxAge() != null) {
//				activityContent.setMaxAge(ava.getMaxAge());
//			}
//			if (ava.getMinAge() != null) {
//				activityContent.setMinAge(ava.getMinAge());
//			}
//			activityContent.setLastUpdated(dateFormat.format(ava.getLastUpdated()));
//			activityContent.setFacility(FacilityLogic.getInstance().getContent(ava.getFacility(), lang));
			
		}

		return contents;
	}

	public List<AvailabilityContent> getAllAvailabilitiesContents(String lang) {
		
		return AvailabilityLogic.getInstance().getAllContents(AvailabilityLogic.getInstance().getAll(), lang);
	}

}
