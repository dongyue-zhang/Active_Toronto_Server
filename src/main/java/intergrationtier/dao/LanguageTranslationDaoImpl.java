package intergrationtier.dao;

import java.util.List;

import org.hibernate.query.Query;

import businesstier.transferobject.LanguageTranslation;

@org.hibernate.annotations.NamedQueries({
	@org.hibernate.annotations.NamedQuery(name = "LanguageTranslation_FindByDes",
			query = "from LanguageTranslation where description = :description",
			timeout = 1,
			fetchSize = 10),
//	@org.hibernate.annotations.NamedQuery(name = "Type_FindByTranslationId"),
//			query = "from Language_Translation where "
})
public class LanguageTranslationDaoImpl extends GenericDaoImpl<LanguageTranslation> implements LanguageTranslationDao {
	
	public LanguageTranslationDaoImpl() {
		super(LanguageTranslation.class);
	}

	@Override
	public LanguageTranslation getLanguageTranslationByDes(String des) {
		Query<LanguageTranslation> query = super.getSession().createNamedQuery("LanguageTranslation_FindByDes", LanguageTranslation.class);
		return null;
	}

	@Override
	public LanguageTranslation findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LanguageTranslation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LanguageTranslation> findByContaining(String search) {
		// TODO Auto-generated method stub
		return null;
	}

}
