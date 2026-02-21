# Dependency Injection (TP1)

This project demonstrates the implementation of fundamental software engineering principles to create flexible, maintainable, and scalable applications. The focus is on **Loose Coupling**, the **Open/Closed Principle (OCP)**, and **Inversion of Control (IoC)** via **Dependency Injection (DI)**.

---

## 1. Core Design Principle: Open/Closed Principle (OCP)

The "Open/Closed Principle" states that software entities should be **open for extension** but **closed for modification**. In this project, this principle is achieved through two key pillars:

### A. Loose Coupling (Interfaces vs. Classes)
Loose coupling involves reducing dependencies between classes by using **Interfaces** instead of concrete classes. This allows the application to be "open for extension" because we can add new behaviors without changing existing ones.

```java
// Example of loose coupling in MetierImpl
private IDao dao; // The object depends on the interface, not a specific class
```

### B. Dependency Injection (DI)
Dependency Injection is the mechanism that allows us to provide an object with its dependencies. It is the practical implementation of the Open/Closed Principle, as it allows us to swap implementations at runtime (or configuration time) without modifying the source code of the consumer.

---

## 2. Project Architecture

The project is structured into several layers:

*   **DAO Layer (Data Access Object)**: `IDao` interface and its implementations (`DaoImpl`, `DaoImplV2`).
*   **Business Logic Layer (Metier)**: `IMetier` interface and its implementation `MetierImpl`.
*   **Extension Layer**: New implementations added without modifying existing code.
*   **Presentation Layer**: 4 different approaches for assembly and Dependency Injection.

---

## 3. The 4 Dependency Injection Methods

### I. Static Injection (Manual Coupling)
Static injection involves linking components directly in the code. This can be done in two ways:

1.  **Via Constructor**: Passing the dependency when the object is created.
2.  **Via Setter**: Injecting the dependency after the object is instantiated using a setter method.

**Code ([Pres1.java](src/main/java/net/omaima/pres/Pres1.java)):**
```java
public class Pres1 {
    public static void main(String[] args) {
        DaoImplV2 dao = new DaoImplV2(); 
        
        // Option 1: Constructor Injection
        MetierImpl metier = new MetierImpl(dao); 
        
        // Option 2: Setter Injection
        // MetierImpl metier = new MetierImpl();
        // metier.setDao(dao);
        
        System.out.println(metier.calcul());
    }
}
```

### II. Dynamic Injection (Java Reflection)
Classes are loaded into memory based on their names (as strings) read from a configuration file (`config.txt`). This is the approach used under the hood by modern frameworks.

**Code ([Pres2.java](src/main/java/net/omaima/pres/Pres2.java)):**
```java
Scanner scanner = new Scanner(new File("config.txt"));
String daoClassName = scanner.nextLine();
Class cDao = Class.forName(daoClassName); // Loading the class
IDao dao = (IDao) cDao.newInstance(); // Dynamic instantiation

String metierClassName = scanner.nextLine();
Class cMetier = Class.forName(metierClassName);
IMetier metier = (IMetier) cMetier.getConstructor(IDao.class).newInstance(dao);
```
> [!NOTE]
> This method is rarely used in final application development but is essential for understanding how frameworks like Spring operate internally.

### III. Spring Framework: XML Version
Objects (Beans) are declared and wired in a `config.xml` file. Spring reads this file, creates the objects, and manages the injection.

**File [config.xml](src/main/resources/config.xml):**
```xml
<bean id="dao" class="net.omaima.ext.DaoImplV2"></bean>
<bean id="metier" class="net.omaima.metier.MetierImpl">
    <!-- Option 1: Constructor Injection -->
    <constructor-arg ref="dao"></constructor-arg>
    
    <!-- Option 2: Setter Injection (using <property>) -->
    <!-- <property name="dao" ref="dao" /> -->
</bean>
```

**Code ([PresSpringXML.java](src/main/java/net/omaima/pres/PresSpringXML.java)):**
```java
ApplicationContext springContext = new ClassPathXmlApplicationContext("config.xml");
IMetier metier = springContext.getBean(IMetier.class);
```

### IV. Spring Framework: Annotation Version
The modern approach. Spring scans packages to find annotated classes and performs injection automatically.

**Annotations Used:**
*   `@Component`: Generic annotation for any Spring bean.
*   `@Repository`: Specifically for classes in the DAO layer (Data Access).
*   `@Service`: Specifically for classes in the Business Logic layer (Metier).
*   `@Autowired`: Marks a dependency for automatic injection by Spring.
*   `@Qualifier`: Used to choose a specific implementation when multiple beans of the same type exist (e.g., injecting `dao2`).

**Code ([MetierImpl.java](src/main/java/net/omaima/metier/MetierImpl.java)):**
```java
@Service("metier")
public class MetierImpl implements IMetier {
    
    @Autowired // Auto-wires the dependency
    @Qualifier("dao2") // Selects a specific bean implementation
    private IDao dao;

    //using Constructor Injection
    /*
    public MetierImpl(@Qualifier("dao2") IDao dao) {
        this.dao = dao;
    }
    */
}
```

---

## 4. Inversion of Control (IoC)
We call this **Inversion of Control** because the developer no longer controls the instantiation of objects or memory management; instead, the **Spring Framework** does. Spring handles "Technical Aspects" and object lifecycles, allowing the developer to focus exclusively on "Business Aspects."

> [!WARNING]
> When using other Spring modules (Data, Security, etc.), precision in choosing annotations is critical as they trigger specific technical behaviors within the framework.





