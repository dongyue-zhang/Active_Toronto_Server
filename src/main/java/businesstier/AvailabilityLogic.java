package businesstier;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import businesstier.transferobject.Availability;
import businesstier.transferobject.AvailabilityContent;
import intergrationtier.dao.AvailabilityDaoImpl;

public class AvailabilityLogic extends GenericLogic<AvailabilityContent, Availability, AvailabilityDaoImpl>{
	private static AvailabilityLogic availabilityLogic = null;

	private AvailabilityLogic() {
		super(new AvailabilityDaoImpl());
	}
	
	public static AvailabilityLogic getInstance() {
		if (availabilityLogic == null) {
			availabilityLogic = new AvailabilityLogic();
		}
		return availabilityLogic;
	}

	@Override
	public List<Availability> getAll() {
		return get(() -> dao().findAll());
	}

	@Override
	public Availability getWithId(int id) {
		return get(() -> dao().findById(id));
	}
	
	public List<Availability> getWithType(String type){
		return get(() -> dao().findByType(type));
	}
	
	public List<Availability> getWithFacilityAndType(String facility, String type){
		return get(() -> dao().findByFacilityAndType(facility, type));
	}

	@Override
	public List<Availability> search(String search) {
		return get((() -> dao().findByContaining(search)));
	}

	public List<AvailabilityContent> getAllContents(List<Availability> list, String lang) {
		List<AvailabilityContent> availabilityContents = new ArrayList<>();
		for (Availability availability : list) {
			AvailabilityContent content = getContent(availability, lang);
			availabilityContents.add(content);
		}
		return availabilityContents;
	}

	public AvailabilityContent getContentWithId(int id, String lang) {
		return getContent(getWithId(id), lang);
	}

	public List<AvailabilityContent> getContents(List<Availability> collection, String lang) {
		List<AvailabilityContent> contents = new ArrayList<>();
		for (Availability availability : collection) {
			contents.add(getContent(availability, lang));
		}
		return contents;
	}

	public AvailabilityContent getContent(Availability full, String lang) {
		AvailabilityContent availabilityContent = new AvailabilityContent();
		availabilityContent.setId(full.getId());
		full.getActivity().getTranslation().getLanguageTranslations().forEach(i -> {
			if (i.getLanguage().getId().equals(lang)) {
				availabilityContent.setTitle(i.getDescription());
			}
		});
		full.getActivity().getType().getTranslation().getLanguageTranslations().forEach(i -> {
			if (i.getLanguage().getId().equals(lang)) {
				availabilityContent.setType(i.getDescription());
			}
		});
		full.getActivity().getType().getCategory().getTranslation().getLanguageTranslations().forEach(i -> {
			if (i.getLanguage().getId().equals(lang)) {
				availabilityContent.setCategory(i.getDescription());
			}
		});
		availabilityContent.setReservationURL(full.getFacility().getUrl());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		availabilityContent.setEndTime(dateFormat.format(full.getEndTime()));
		availabilityContent.setStartTime(dateFormat.format(full.getStartTime()));
		if (full.getMaxAge() != null) {
			availabilityContent.setMaxAge(full.getMaxAge());
		}
		if (full.getMinAge() != null) {
			availabilityContent.setMinAge(full.getMinAge());
		}
		availabilityContent.setLastUpdated(dateFormat.format(full.getLastUpdated()));
		availabilityContent.setFacility(FacilityLogic.getInstance().getContent(full.getFacility(), lang));
		return availabilityContent;
	}

}
