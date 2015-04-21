package com.timsmeet.services.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.timsmeet.persistance.model.CompanyVacationEntity;
import com.timsmeet.persistance.model.CompanyWorkingHourEntity;
import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.persistance.model.EmployeeVacationEntity;
import com.timsmeet.persistance.model.EmployeeWorkingHourEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.persistance.spring.PersistenceJPAConfig;
import com.timsmeet.services.find.FindEntityWithIdAccessor;
import com.timsmeet.services.find.entity.EmailIdAccessor;
import com.timsmeet.services.find.entity.PhoneIdAccessor;
import com.timsmeet.services.find.entity.VacationIdAccessor;
import com.timsmeet.services.find.entity.WebUrlIdAccessor;
import com.timsmeet.services.find.entity.WorkingHourIdAccessor;

@Configuration
@ComponentScan(basePackages = {"com.timsmeet.services"})
@EnableTransactionManagement
@Import({PersistenceJPAConfig.class})
public class ServicesConfig {

	@Bean(name = "companyVacationFind")
	public FindEntityWithIdAccessor<CompanyVacationEntity> getCompanyVacationFind() {
		FindEntityWithIdAccessor<CompanyVacationEntity> companyVacationFind = new FindEntityWithIdAccessor<CompanyVacationEntity>();
		companyVacationFind.setIdAccessor(new VacationIdAccessor<CompanyVacationEntity>());
		return companyVacationFind;
	}
	
	@Bean(name = "companyWorkingHourFind")
	public FindEntityWithIdAccessor<CompanyWorkingHourEntity> getCompanyWorkingHourFind() {
		FindEntityWithIdAccessor<CompanyWorkingHourEntity> companyWorkingHourFind = new FindEntityWithIdAccessor<CompanyWorkingHourEntity>();
		companyWorkingHourFind.setIdAccessor(new WorkingHourIdAccessor<CompanyWorkingHourEntity>());
		return companyWorkingHourFind;
	}
	
	@Bean(name = "employeeVacationFind")
	public FindEntityWithIdAccessor<EmployeeVacationEntity> getEmployeeVacationFind() {
		FindEntityWithIdAccessor<EmployeeVacationEntity> employeeVacationFind = new FindEntityWithIdAccessor<EmployeeVacationEntity>();
		employeeVacationFind.setIdAccessor(new VacationIdAccessor<EmployeeVacationEntity>());
		return employeeVacationFind;
	}
	
	@Bean(name = "employeeWorkingHourFind")
	public FindEntityWithIdAccessor<EmployeeWorkingHourEntity> getEmployeeWorkingHourFind() {
		FindEntityWithIdAccessor<EmployeeWorkingHourEntity> employeeWorkingHourFind = new FindEntityWithIdAccessor<EmployeeWorkingHourEntity>();
		employeeWorkingHourFind.setIdAccessor(new WorkingHourIdAccessor<EmployeeWorkingHourEntity>());
		return employeeWorkingHourFind;
	}	
	
	@Bean(name = "emailFind")
	public FindEntityWithIdAccessor<EmailEntity> getEmailFind() {
		FindEntityWithIdAccessor<EmailEntity> emailFind = new FindEntityWithIdAccessor<EmailEntity>();
		emailFind.setIdAccessor(new EmailIdAccessor());
		return emailFind;
	}
	
	@Bean(name = "phoneFind")
	public FindEntityWithIdAccessor<PhoneEntity> getPhoneFind() {
		FindEntityWithIdAccessor<PhoneEntity> phoneFind = new FindEntityWithIdAccessor<PhoneEntity>();
		phoneFind.setIdAccessor(new PhoneIdAccessor());
		return phoneFind;
	}
	
	@Bean(name = "webUrlFind")
	public FindEntityWithIdAccessor<WebUrlEntity> getWebUrlFind() {
		FindEntityWithIdAccessor<WebUrlEntity> webUrlFind = new FindEntityWithIdAccessor<WebUrlEntity>();
		webUrlFind.setIdAccessor(new WebUrlIdAccessor());
		return webUrlFind;
	}
}
