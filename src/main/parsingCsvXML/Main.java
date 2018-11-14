package parsingCsvXML;

public class Main {


    public static void main(String[] args) throws Exception {

        //read file and calculate results
        Service contest = new Service("results.csv");

        // to see what we have in our xml file
        System.out.println(contest.convertToXML());

        contest.saveConvertedXmlFile("results.xml");
    }

}
