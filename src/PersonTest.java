import static org.junit.jupiter.api.Assertions.*;

class PersonTest
{
    Person p1, p2;

    @org.junit.jupiter.api.BeforeEach
     void setUp()
    {
        p1 = new Person("000001", "Pratik", "Chaudhari", "Mr.", 2002);

        p2 = new Person("000002", "Bilbo", "Baggins", "Dr.", 1972);
    }

    @org.junit.jupiter.api.Test
    void setM_ID() {
        p1.setM_ID("000001");
        assertEquals("000002", p1.getM_ID());
    }

    @org.junit.jupiter.api.Test
    void setM_firstName() {
        p1.setM_ID("Pratik");
        assertEquals("Bilbo", p1.getM_firstName());
    }

    @org.junit.jupiter.api.Test
    void setM_lastName() {
        p1.setM_lastName("Chaudhari");
        assertEquals("Baggins", p1.getM_lastName());
    }

    @org.junit.jupiter.api.Test
    void setM_title() {
        p1.setM_title("Mr.");
        assertEquals("Dr.", p1.getM_title());
    }

    @org.junit.jupiter.api.Test
    void setM_yearOfBirth() {
        p1.setM_yearOfBirth(2002);
        assertEquals(1972, p1.getM_yearOfBirth());
    }

    @org.junit.jupiter.api.Test
    void fullName() {
        assertEquals("Bilbo Baggins", p1.fullName());
    }

    @org.junit.jupiter.api.Test
    void formalName() {
        assertEquals("Dr. Bilbo Baggins", p1.formalName());
    }

    @org.junit.jupiter.api.Test
    void getAge() {
        assertEquals("50", p1.getAge());
    }

    @org.junit.jupiter.api.Test
    void testGetAge() {
        assertEquals("60", p1.getAge(2032));
    }

    @org.junit.jupiter.api.Test
    void toCSVDataRecord() {
        assertEquals("000002, Bilbo, Baggins, Dr., 1972", p1.toCSVDataRecord());
    }
}