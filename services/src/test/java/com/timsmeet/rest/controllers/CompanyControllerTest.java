package com.timsmeet.rest.controllers;

import java.sql.Timestamp;
import java.util.Arrays;

import junit.framework.Assert;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.timsmeet.dto.Company;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.PhoneNumberType;
import com.timsmeet.persistance.enums.WeekDay;
import com.timsmeet.persistance.model.CompanyEntity;
import com.timsmeet.persistance.repositories.CompanyRepository;
import com.timsmeet.rest.RestTestUtil;
import com.timsmeet.services.CompanyService;


@DatabaseSetup({"ContactData.xml", "PhoneData.xml", "EmailData.xml", "WebUrlData.xml", "AddressData.xml", "CompanyData.xml", "WorkinghourData.xml", "VacationsData.xml"})
@DatabaseTearDown("CompanyControllerClear.xml")
public class CompanyControllerTest extends BaseControllerTest {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyRepository companyRepository;
	
  @Test
  @ExpectedDatabase(value="CompanyControllerNoChangesData.xml", table="tm_company")
  public void shouldFindAllCompanies() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
  }

  @Test
  @ExpectedDatabase(value="CompanyControllerNoChangesData.xml", table="tm_company")
  public void shouldFindCompanyById() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/companies/-1001"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(-1001)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Acme Corp")));
  }

  @Test
  @ExpectedDatabase(value="CompanyControllerNoChangesData.xml", table="tm_company")
  public void shouldErrorNotFoundWhenGetNotExistingCompany() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/companies/-3001"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  @ExpectedDatabase(value="CompanyControllerDeleteCompany.xml", table="tm_company")
  public void shouldDeleteExistingCompany() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.delete("/companies/-1001"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk());
  }
  
  
  @Test
  @ExpectedDatabase(value="CompanyControllerNoChangesData.xml", table="tm_company")
  public void shouldErrorNotFoundWhenDeleteNotExistingCompany() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.delete("/companies/-3001"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isNotFound())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", Matchers.hasSize(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].errorCode", Matchers.is("0001")))
              .andExpect(MockMvcResultMatchers.jsonPath("$..errorCode", Matchers.hasItem("0001")));
  }

  
  @Test
  @ExpectedDatabase(value="CompanyControllerNoChangesData.xml", table="tm_company")
  public void shouldFindByIdWithEmbededWorkingHours() throws Exception {
	  ResultActions resultActions = 
      mockMvc.perform(MockMvcRequestBuilders.get("/companies/-1001").param("embeded", "workingHours"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(-1001)));
	  
	  resultActions = andValidateReadComanyWorkingHours(resultActions);
  }
  
  private ResultActions andValidateReadComanyWorkingHours(ResultActions resultActions) throws Exception {
      return resultActions
    		  
    		  .andExpect(MockMvcResultMatchers.jsonPath("$.workingHours", Matchers.hasSize(6)))
    		  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1001)].weekDay", Matchers.hasItem(WeekDay.MONDAY.toString())))
    		  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1001)].startTime", Matchers.hasItem("2000-06-01T06:00:00.000+0000")))
    		  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1001)].endTime", Matchers.hasItem("2000-06-01T13:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1002)].weekDay", Matchers.hasItem(WeekDay.MONDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1002)].startTime", Matchers.hasItem("2000-06-01T14:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1002)].endTime", Matchers.hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1003)].weekDay", Matchers.hasItem(WeekDay.TUESDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1003)].startTime", Matchers.hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1003)].endTime", Matchers.hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1004)].weekDay", Matchers.hasItem(WeekDay.WEDNESDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1004)].startTime", Matchers.hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1004)].endTime", Matchers.hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1005)].weekDay", Matchers.hasItem(WeekDay.THURSDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1005)].startTime", Matchers.hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1005)].endTime", Matchers.hasItem("2000-06-01T16:00:00.000+0000")))

			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1006)].weekDay", Matchers.hasItem(WeekDay.FRIDAY.toString())))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1006)].startTime", Matchers.hasItem("2000-06-01T08:00:00.000+0000")))
			  .andExpect(MockMvcResultMatchers.jsonPath("$..workingHours[?(@.id==-1006)].endTime", Matchers.hasItem("2000-06-01T16:00:00.000+0000")));
  }

  @Test
  @ExpectedDatabase(value="CompanyControllerNoChangesData.xml", table="tm_company")
  public void shouldFindByIdWithEmbededVacations() throws Exception {
	  ResultActions resultActions = 
			  mockMvc.perform(MockMvcRequestBuilders.get("/companies/-1001").param("embeded", "vacations"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(-1001)));
	  resultActions = andValidateCompanyVacations(resultActions);
  }
  
  private ResultActions andValidateCompanyVacations(ResultActions resultActions) throws Exception {
	  return resultActions
              .andExpect(MockMvcResultMatchers.jsonPath("$.vacations", Matchers.hasSize(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==-1001)].startDay", Matchers.hasItem("2000-06-01T00:00:00.000+0000")))
              .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==-1001)].endDay", Matchers.hasItem("2000-06-05T00:00:00.000+0000")))

	  		  .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==-1002)].startDay", Matchers.hasItem("2000-08-01T00:00:00.000+0000")))
		      .andExpect(MockMvcResultMatchers.jsonPath("$..vacations[?(@.id==-1002)].endDay", Matchers.hasItem("2000-08-02T00:00:00.000+0000")));
  }
  
  @Test
  @ExpectedDatabase(value="CompanyControllerNoChangesData.xml", table="tm_company")
  public void shouldFindByIdWithEmbededContacts() throws Exception {
	  ResultActions resultActions =
			  mockMvc.perform(MockMvcRequestBuilders.get("/companies/-1001").param("embeded", "contact.emails", "contact.webUrls", "contact.phones"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(-1001)));
	  resultActions = andValidateCompanyContacts(resultActions);
  }
  
  private ResultActions andValidateCompanyContacts(ResultActions resultActions) throws Exception {
	  return resultActions
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact.emails", Matchers.hasSize(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1001)].status", Matchers.hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1001)].comment", Matchers.hasItem("Office email")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1001)].emailAddress", Matchers.hasItem("office@acme.corp.com")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1001)].displayIndex", Matchers.hasItem(0)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1002)].status", Matchers.hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1002)].comment", Matchers.hasItem("Support email")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1002)].emailAddress", Matchers.hasItem("support@acme.corp.com")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..emails[?(@.id==-1002)].displayIndex", Matchers.hasItem(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact.webUrls", Matchers.hasSize(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==-1001)].status", Matchers.hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==-1001)].webUrlAddress", Matchers.hasItem("http://acme.corp.com")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==-1001)].comment", Matchers.hasItem("Main web address")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..webUrls[?(@.id==-1001)].displayIndex", Matchers.hasItem(0)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact.phones", Matchers.hasSize(2)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].status", Matchers.hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].phone", Matchers.hasItem("508334321")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].phoneExt", Matchers.hasItem("1234")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].numberType", Matchers.hasItem(PhoneNumberType.MOBILE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].comment", Matchers.hasItem("Mobile phone to manager")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1001)].displayIndex", Matchers.hasItem(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].status", Matchers.hasItem(ActivityStatus.ACTIVE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].phone", Matchers.hasItem("345223421")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].numberType", Matchers.hasItem(PhoneNumberType.LANDLINE.toString())))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].comment", Matchers.hasItem("Company std phone")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.contact..phones[?(@.id==-1002)].displayIndex", Matchers.hasItem(0)));
  }

  
  @Test
  @ExpectedDatabase(value="CompanyControllerNoChangesData.xml", table="tm_company")
  public void shouldFindByIdWithMultiEmbededEntities() throws Exception {
	  ResultActions resultActions =
			  mockMvc.perform(MockMvcRequestBuilders.get("/companies/-1001").param("embeded", "workingHours", "vacations", "contact.emails", "contact.webUrls", "contact.phones"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(-1001)));
	  resultActions = andValidateReadComanyWorkingHours(resultActions);
	  resultActions = andValidateCompanyVacations(resultActions);
	  resultActions = andValidateCompanyContacts(resultActions);
  }

  
  @Test
  @Transactional
  public void save2Company() throws Exception {
	  
	  Company companyToSave = new Company.Builder("Added Company", ActivityStatus.ACTIVE)
	  	.vacations(Arrays.asList(new Vacation.Builder(new Timestamp(0), new Timestamp(0)).build()))
	  	.workingHours(Arrays.asList(new WorkingHour.Builder(WeekDay.MONDAY, new Timestamp(0), new Timestamp(0)).build()))
	  	.build();
	  
	  MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
			  .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
			  .content(RestTestUtil.convertObjectToJsonBytes(companyToSave)))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
              .andExpect(MockMvcResultMatchers.jsonPath("$.lastModificationId", Matchers.notNullValue()))
              .andReturn().getResponse();
	  
	  Company company = RestTestUtil.convertJsonBytesToObject(response.getContentAsByteArray(), Company.class);
	  
	  CompanyEntity insertedCompanyDb = companyRepository.findOne(company.getId());
	  Assert.assertEquals("Should have 1 vacation defined.", 1, insertedCompanyDb.getVacations().size());
	  Assert.assertEquals("Should have 1 workingHour defined.", 1, insertedCompanyDb.getWorkingHours().size());
  }

  @Test
  public void saveCompany() throws Exception {
	  
	  Company companyToSave = new Company();
	  companyToSave.setName("Added company");
	  companyToSave.setStatus(ActivityStatus.ACTIVE);
	  
	  MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/companies")
			  .contentType(RestTestUtil.APPLICATION_JSON_UTF8)
			  .content(RestTestUtil.convertObjectToJsonBytes(companyToSave)))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andReturn().getResponse();
	  
	  Company company = RestTestUtil.convertJsonBytesToObject(response.getContentAsByteArray(), Company.class);
	  
	  companyService.readCompany(company.getId(), new String[0]);
  }

}
