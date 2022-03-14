import java.util.Calendar;

public class Person
{
    private String m_ID;
    private String m_firstName;
    private String m_lastName;
    private String m_title;
    private int m_yearOfBirth;

    Person()
    {
        m_ID = "";
        m_firstName = "";
        m_lastName = "";
        m_title = "";
        m_yearOfBirth = 0;
    }

    Person(String ID, String firstName, String lastName, String title, int yearOfBirth)
    {
        m_ID = ID;
        m_firstName = firstName;
        m_lastName = lastName;
        m_title = title;
        m_yearOfBirth = yearOfBirth;
    }

    public String getM_ID() {
        return m_ID;
    }

    public String getM_firstName() {
        return m_firstName;
    }

    public String getM_lastName() {
        return m_lastName;
    }

    public String getM_title() {
        return m_title;
    }

    public int getM_yearOfBirth() {
        return m_yearOfBirth;
    }

    public void setM_ID(String m_ID) {
        this.m_ID = m_ID;
    }

    public void setM_firstName(String m_firstName) {
        this.m_firstName = m_firstName;
    }

    public void setM_lastName(String m_lastName) {
        this.m_lastName = m_lastName;
    }

    public void setM_title(String m_title) {
        this.m_title = m_title;
    }

    public void setM_yearOfBirth(int m_yearOfBirth) {
        this.m_yearOfBirth = m_yearOfBirth;
    }

    public String fullName()
    {
        return m_firstName + " " + m_lastName;
    }

    public String formalName()
    {
        return m_title + " " + fullName();
    }

    public String getAge()
    {
        Calendar cal = Calendar.getInstance();

        return String.valueOf(cal.get(Calendar.YEAR) - m_yearOfBirth);
    }

    public String getAge(int year)
    {
        if(year < m_yearOfBirth) return 0 + "";

        return String.valueOf(year - m_yearOfBirth);
    }

    public String toCSVDataRecord()
    {
        String CSVDataRecord = "";

        CSVDataRecord = String.format("%s, %s, %s, %s, %d", m_ID, m_firstName, m_lastName, m_title, m_yearOfBirth);

        return CSVDataRecord;
    }

    public String toString()
    {
        return "Person{ID=" + m_ID + " First Name=" + m_firstName +
                " Last Name=" + m_lastName + " Title=" + m_title +
                  " Year of Birth=" + m_yearOfBirth + "}";
    }
}
