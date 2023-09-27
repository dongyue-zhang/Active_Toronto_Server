package businesstier.transferobject;
// Generated May 25, 2023, 11:51:25 p.m. by Hibernate Tools 6.1.7.Final

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class Type implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Category category;
	private LanguageTranslation languagetranslation;
	private Timestamp lastUpdated;
	private Translation translation;
	private Set<Activity> activities = new HashSet<Activity>(0);

	public Type() {
	}

	public Type(Category category, LanguageTranslation languagetranslation) {
		this.category = category;
		this.languagetranslation = languagetranslation;
	}

	public Type(Category category, LanguageTranslation languagetranslation, Timestamp lastUpdated, Set<Activity> activities) {
		this.category = category;
		this.languagetranslation = languagetranslation;
		this.lastUpdated = lastUpdated;
		this.activities = activities;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LanguageTranslation getLanguageTranslation() {
		return this.languagetranslation;
	}

	public void setTranslation(LanguageTranslation languagetranslation) {
		this.languagetranslation = languagetranslation;
	}

	public Timestamp getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Translation getTranslation() {
		return translation;
	}

	public void setTranslation(Translation translation) {
		this.translation = translation;
	}
	
	

}
