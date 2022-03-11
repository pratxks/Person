import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.*;

public class PersonReader
{
    static ArrayList<Person> PersonRecordArray = new ArrayList<Person>();

    public static void ReadFileDataToArrayList()
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        PersonRecordArray.clear();

        try
        {
            // uses a fixed known path:
            //  Path file = Paths.get("c:\\My Documents\\data.txt");

            // use the toolkit to get the current working directory of the IDE
            // Not sure if the toolkit is thread safe...
            File workingDirectory = new File(System.getProperty("user.dir"));

            // Typiacally, we want the user to pick the file so we use a file chooser
            // kind of ugly code to make the chooser work with NIO.
            // Because the chooser is part of Swing it should be thread safe.
            chooser.setCurrentDirectory(workingDirectory);
            // Using the chooser adds some complexity to the code.
            // we have to code the complete program within the conditional return of
            // the filechooser because the user can close it without picking a file

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                // Finally we can read the file LOL!
                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();

                    String [] CSVArray = rec.split(",");

                    if(CSVArray.length == 5)
                    {
                        //Person Record is created here and CSV array information is set to Person Object
                        Person PersonRecord = new Person();

                        PersonRecord.setM_ID(CSVArray[0]);
                        PersonRecord.setM_firstName(CSVArray[1]);
                        PersonRecord.setM_lastName(CSVArray[2]);
                        PersonRecord.setM_title(CSVArray[3]);
                        PersonRecord.setM_yearOfBirth(Integer.parseInt(CSVArray[4].trim()));

                        //Person record is added to Person Array here
                        PersonRecordArray.add(PersonRecord);
                        line++;
                        // echo to screen
                        System.out.printf("\nLine %4d %-60s ", line, PersonRecord.toCSVDataRecord());
                    }
                }

                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void DisplayPersonRecords()
    {
        //ID#           Firstname     Lastname       Title    YOB
        String strDisplayHeader = String.format("%-11s","ID#");
        strDisplayHeader += String.format("%-20s", "Firstname");
        strDisplayHeader += String.format("%-20s", "Lastname");
        strDisplayHeader += String.format("%-10s", "Title");
        strDisplayHeader += String.format("%-10s", "YOB");

        System.out.println(strDisplayHeader);

        String strDisplayUnderLine = String.format("%71s", " ").replace(' ', '=');
        System.out.println(strDisplayUnderLine);

        //Person record array is traversed here for display of Person information on screen
        for(Person PersonRecord : PersonRecordArray)
        {
            String strDisplayRec = "";

            strDisplayRec = String.format("%8s", PersonRecord.getM_ID()).replace(' ', '0');
            strDisplayRec += String.format("%2s", " ");

            strDisplayRec += String.format("%-20s", PersonRecord.getM_firstName());

            strDisplayRec += String.format("%-20s", PersonRecord.getM_lastName());

            strDisplayRec += String.format("%-10s", PersonRecord.getM_title());

            strDisplayRec += String.format("%-10s", PersonRecord.getM_yearOfBirth());

            System.out.println(strDisplayRec);
        }
    }

    public static void main(String[] args)
    {
        ReadFileDataToArrayList();

        DisplayPersonRecords();
    }
}
