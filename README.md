1. Content negotiation
2. Transactional
3. Scheduler (CRON JOB)
4. Async
5. Cacheable 
6. Controller vs RestController
7. Qualifier and Primary
8. Locks, Synchronised and ReentrantLock
9. Spring AOP and Custom Annotations -
    Use @Before when you need to do something before a method executes (e.g., logging, security checks).
    Use @AfterReturning to work with the result of a method.
    Use @AfterThrowing to handle exceptions specifically.
    Use @After for cleanup activities that should run regardless of method success or failure.
    Use @Around for full control over method execution, to modify arguments or return values, or to implement complex conditional behavior around the method call.
10. Testing -
    Repository Integration Testing - Use @DataJpaTest instead of @SpringBootTest and Autowired repository and TestEntityManager for persisting data. 
    Controller Integration Testing (@SpringBootTest and @AutoConfigureMockMvc) - Use @SpringBootTest and @AutoConfigureMockMvc , specify port and then use restTemplate or mockMvc to test.
    Controller Unit Testing (@WebMvcTest(OrderFulfillmentController.class))- Use @WebMvcTest(Controller.class),( without ExtendsWith ) and mockMvc to test without specifying ports and all and use @MockBean 
    only and mockMvc to call controller api.
    Service Unit Testing - Use @Mock and @InjectMocks for unit testing.
    Service Integration Testing - Use @Autowired and TestEntityManager for integration testing.
11. Flyway Migration
12. Profile annotation for switching between dev and prod profiles.   
13. Usage of OneToMany annotation.
    

