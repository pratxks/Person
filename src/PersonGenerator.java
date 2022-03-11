import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

public class PersonGenerator
{
    static ArrayList<Person> PersonRecordArray = new ArrayList<Person>();

    public static void InputPersonData()
    {
        boolean newPerson;

        Scanner inStream = new Scanner(System.in);

        String ID, firstName, lastName, title;
        int yearOfBirth;

        PersonRecordArray.clear();

        do
        {
            ID = "";
            firstName = "";
            lastName = "";
            title = "";
            yearOfBirth = 0;
            newPerson = false;

            ID = SafeInput.getNonZeroLenString(inStream, "Enter Person ID (a string)");
            firstName = SafeInput.getNonZeroLenString(inStream, "Enter Person first name");
            lastName = SafeInput.getNonZeroLenString(inStream, "Enter Person last name");
            title = SafeInput.getNonZeroLenString(inStream, "Enter Title of Person (a string like Mr., Mrs., Ms., Dr., etc.)");
            yearOfBirth = SafeInput.getRangedInt(inStream, "Enter Person's Birth Year", 1940, 2000);

            //Person object created from imput information
            Person PersonRecord = new Person(ID, firstName, lastName, title, yearOfBirth);

            System.out.println(PersonRecord.toCSVDataRecord());

            //Person objects are added to Person Array
            PersonRecordArray.add(PersonRecord);

            if(!newPerson)
            {
                newPerson = SafeInput.getYNConfirm(inStream, "\n\nDo you want to input new person details (Y/N): ");
            }

        }while (newPerson);
    }

    public static void WritePersonDataToFile()//ArrayList<String> perRecArray)
    {
        // use the toolkit to get the current working directory of the IDE
        // will create the file within the Netbeans project src folder
        // (may need to adjust for other IDE)
        // Not sure if the toolkit is thread safe...
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "/src/PersonTestData.txt");

        System.out.println(file);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            //Person array is traversed for writing CSV records to text file
            for(Person PersonRecord : PersonRecordArray)
            {
                String CSVRecord = PersonRecord.toCSVDataRecord();

                writer.write(CSVRecord, 0, CSVRecord.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line
            }

            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        InputPersonData();

        for(int i=0; i<PersonRecordArray.size(); i++)
        {
            System.out.println(PersonRecordArray.get(i).toCSVDataRecord());
        }

        WritePersonDataToFile();
    }
}
