//package be.jstack.ticketing.service;
//
//import be.jstack.ticketing.data.CompanyRepository;
//import be.jstack.ticketing.entity.Company;
//import be.jstack.ticketing.entity.Ticket;
//import be.jstack.ticketing.entity.User;
//import be.jstack.ticketing.entity.tickttypes.BugIssue;
//import be.jstack.ticketing.util.constants.TicketStatus;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.Date;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//public class CompanyServiceTest {
//
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @InjectMocks
//    private CompanyService companyService;
//
//    private Ticket ticket;
//    private User creator;
//    private Company company;
//
//
//    @Before
//    public void setUp() throws Exception {
//        creator = new User();
//        ticket = new BugIssue("123");
//        company = new Company();
//        creator.setUsername("ward");
//        creator.setPassword(new BCryptPasswordEncoder().encode("ward"));
//        creator.setEmail("wardpauwels@hotmail.be");
//        ticket.setTicketStatus(TicketStatus.NEW);
//        ticket.setCreationDate(new Date());
//        ticket.setCreator(creator);
//        company.setId("5afd4956a9f60759a8fd6337");
//        company.setName("Google");
//        company.add(creator);
//    }
//
//    @Test
//    public void addCompany() {
//    }
//
//    @Test
//    public void findCompanyById() {
////        when(companyRepository.findById("5afd4956a9f60759a8fd6337")).thenReturn(Optional.of(company));
////
////        Optional<Company> findCompany = companyService.findCompanyById("5afd4956a9f60759a8fd6337");
////
////        assertNotNull(findCompany);
//    }
//
//    @Test
//    public void getAllCompanies() {
//    }
//
//    @Test
//    public void addProjectToCompany() {
//    }
//
//    @Test
//    public void deleteProjectFromCompany() {
//    }
//
//    @Test
//    public void addUserToCompany() {
//    }
//
//    @Test
//    public void deleteUserFromCompany() {
//    }
//}