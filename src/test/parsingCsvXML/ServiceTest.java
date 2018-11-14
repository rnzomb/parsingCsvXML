package parsingCsvXML;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void readFromFileTest() throws Exception {
        Service serviceTest = new Service();
        String str = serviceTest.readFromFile("results.csv");
        assertNotNull("file not found", str);
    }

    @Test
    public void saveConvertedXmlFileTest() {
        Service serviceTest = new Service();
        serviceTest.saveConvertedXmlFile("test.xml");
        File file = new File("test.xml");
        assertTrue(file.exists());

    }

    @Test

    // parse and store several (at least 2) records divided by line break
    public void storeDataToListTest() throws Exception {

        Service serviceTest = new Service();
        String str = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72 \n" +
                "Jane Doe;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6.50.76";

        serviceTest.storeDataToList(str);
        assertTrue(serviceTest.getList().size() == 2);
    }

    @Test

    //instance of Decathlete class must be created and added to list
    public void addPersonToListNotNullTest() throws Exception {
        Service serviceTest = new Service();
        String[] str = {"Wild Cat", "10.395",
                "7.76", "18.4", "2.21", "46.17", "13.8",
                "56.17", "5.28", "77.19", "3.53.79"};
        try {
            serviceTest.addPersonToList(str);
        } catch (Exception ex) {
            System.out.println("threw higher exception, no or wrong format values");
        }
        assertNotEquals(0, serviceTest.getList().size());
    }

    @Test
    public void computeTotalScoreTest() {                         // try to get 10001 points with these values
        Service serviceTest = new Service();
        Decathlete personTest = new Decathlete(0, "Wild Cat", 10.395,
                7.76, 18.4, 2.21, 46.17, 13.8,
                56.17, 5.28, 77.19, "3.53.79");

        int score = serviceTest.computeTotalScore(personTest);
        assertEquals(10001, score);
    }

    @Test
    public void computePlaceTest() {                    //check if 2 results are equals and place looks like "1-2"
        Service serviceTest = new Service();
        String str = "James Strong; 10.395 ; 7.76 ; 18.4 ; 2.20 ; 46.17 ; 13.8 ; 56.17 ; 5.28 ; 77.19 ; 3.53.79\n" +
                "Bill Armstrong; 10.395 ; 7.76 ; 18.4 ; 2.20 ; 46.17 ; 13.8 ; 56.17 ; 5.28 ; 77.19 ; 3.53.79";

        serviceTest.storeDataToList(str);
        serviceTest.computePlace();
        assertEquals("1-2",serviceTest.getList().get(0).getPlace());
    }

    @Test
    public void convertToXMLTest() {                                   //XML must contain a certain string and tags
        Service serviceTest = new Service();
        StringBuilder str = serviceTest.convertToXML();
        assertTrue(String.valueOf(str).contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<decathlon>\n" +
                "</decathlon>"));
    }
}