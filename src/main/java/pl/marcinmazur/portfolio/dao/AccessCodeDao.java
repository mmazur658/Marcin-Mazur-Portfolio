package pl.marcinmazur.portfolio.dao;

import java.util.List;

import pl.marcinmazur.portfolio.entity.AccessCode;

public interface AccessCodeDao {

	void addNewAccessCode(AccessCode accessCode);
	
	List<AccessCode> getListOfAccessCodes();
	
	AccessCode getAccessCodeById(int accessCodeId);
	
	List<AccessCode> getListOfAccessCodeByValue(String accessCodeValue);

	List<AccessCode> isAccessCodeCorrect(String accessCodeValue);
	
	AccessCode getSingleAccessCodeByValue(String accessCodeValue);

	List<String> getListOfAccessCodeValues();

	String getAccessCodeOwenerByGivenAccessCodeValue(String accessCodeValue);



}
