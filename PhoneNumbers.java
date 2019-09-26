import java.util.*; 
import java.io.*;

/**
* Imports contacts from csv file into a hashtable. Prompts the user to search for a contact.
* If contact exists, prints contact's phone number and exists. If the contact does not exist,
* prompts the user to add new contacts until the limit of 20 is reached.
*
* @
* @1.0 
*/
public class PhoneNumbers
{
    public static void main()
    {
       Hashtable<String, String> PhoneNumbers = new Hashtable<String, String>();
       Scanner input = new Scanner(System.in); 
       importcontacts (PhoneNumbers);//stores contacts from csv file into PhoneNumbers Hashtable
       System.out.println("Enter name to search.");
       String search = input.nextLine();//name to search
       if (PhoneNumbers.containsKey(search))//checks hashtable for name, prints and exits if found
       {
           System.out.println(search + PhoneNumbers.get(search));//print contact name and number
           System.exit(0);
       }
       else
       {
           while (PhoneNumbers.size()!=20)//prompts to add new contact while # of contacts <20
           {
               System.out.println("Add new contact? Enter Y/N.");
               String Answer = input.nextLine();
               if (Answer.equalsIgnoreCase("Y"))
               {
                   addcontact(PhoneNumbers);
               }
               else if (Answer.equalsIgnoreCase("N"))
               {
                   System.exit(0);
               }
               else 
               {
                   while (!(Answer.equalsIgnoreCase("Y") || Answer.equalsIgnoreCase("N")))
                   {
                       System.out.println("Must enter Y or N");
                       Answer = input.nextLine();
                   }
               }
               if (PhoneNumbers.size()==20)
               {
                   System.out.println("Contact limit reached (20 contacts). Exiting.");
                   System.exit(0);
               }
           }   
       }
    }
    public static void addcontact(Hashtable<String, String> PhoneNumbers)//adds new contact, does not check for correct number format for phone number string
    {
       Scanner input = new Scanner(System.in);
       System.out.println("Enter Contact's Name");
       String name = input.nextLine();//stores input string as contact name
       System.out.println("Enter Contact's Number");
       String number = input.nextLine();//stores input string as contact number
       if (PhoneNumbers.containsKey(name) && PhoneNumbers.contains(number))//checks for duplicate contact.
       {
           System.out.println("Contact already exists.");
       }
       else
       {
           PhoneNumbers.put(name, number);//adds contact to hashtable as key/value pair
       }
    }
    public static void importcontacts(Hashtable<String, String> PhoneNumbers)//imports contacts from csv file
    {
        try
        {
            BufferedReader csvreader = new BufferedReader(new FileReader("PhoneNumbers.csv"));//file to read
            String row = new String();
            while ((row = csvreader.readLine()) != null)//reads each line in file while not null
            {
                String[] contacts = row.split(",");//splits row data into strings using , as delimitter
                PhoneNumbers.put(contacts[0], contacts[1]);//inserts row 1 (name) as key and row 2 (number ) as value
            }
            csvreader.close();
        }
        catch(Exception e)//catch filenotfound exception
        {
            e.printStackTrace();
        }
    }
}
