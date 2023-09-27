package intergrationtier.dao;

import businesstier.transferobject.LanguageTranslation;

public interface LanguageTranslationDao {
	LanguageTranslation getLanguageTranslationByDes(String des);
}
