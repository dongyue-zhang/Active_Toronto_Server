package businesstier;

import java.util.List;

import businesstier.transferobject.Address;
import businesstier.transferobject.AddressContent;
import intergrationtier.dao.AddressDaoImpl;

public class AddressLogic extends GenericLogic<AddressContent, Address, AddressDaoImpl>{
	private static AddressLogic addressLogic = null;

	private AddressLogic() {
		super(new AddressDaoImpl());
	}
	
	public static AddressLogic getInstance() {
		if (addressLogic == null) {
			addressLogic = new AddressLogic();
		} 
		return addressLogic;
	}

	@Override
	public List<Address> getAll() {
		return get(()->dao().findAll());
	}

	@Override
	public Address getWithId(int id) {
		return get(() -> dao().findById(id));
	}
	
	public Address getWithFacility(String facility) {
		return get(() -> dao().findByFacility(facility));
	}

	@Override
	public List<Address> search(String search) {
		return get(() -> dao().findByContaining(search));
	}


	public List<AddressContent> getAllContents(String lang) {
		// TODO Auto-generated method stub
		return null;
	}


	public AddressContent getContentWithId(int id, String lang) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<AddressContent> getContents(List<Address> collection, String lang) {
		// TODO Auto-generated method stub
		return null;
	}


	public AddressContent getContent(Address full, String lang) {
		AddressContent addressContent = new AddressContent();
		addressContent.setId(full.getId());
		addressContent.setCity(full.getCity());
		addressContent.setCountry(full.getCountry());
		addressContent.setPostalCode(full.getPostalCode());
		addressContent.setProvince(full.getProvince());
		full.getTranslation().getLanguageTranslations().forEach(i -> {
			if (i.getLanguage().getId().equals(lang)) {
				addressContent.setStreet(i.getDescription());
			}
		});
		return addressContent;
	}

}
