package parsingCsvXML;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

// here we read file, parse data, calculate score, place and store it all in arrayList

public class Service {


    private int personId = 0;
    private ArrayList<Decathlete> list = new ArrayList<>();                   // here we store our decathletes data

    public Service() {                                                        //empty constructor for tests
    }

    public Service(String fileName) throws Exception {

        //load from file to string
        String dataFromFile = readFromFile(fileName);

        //parse data and fill arrayList
        storeDataToList(dataFromFile);

        // sort our list in reverse order by total score, using lambda expression
        list.sort(Comparator.comparing((Decathlete u) -> u.getTotalScore()).reversed());

        //arrange places and check if there are duplicate scores
        computePlace();
    }


    public String readFromFile(String filename) {

        try {
            FileInputStream fin = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));

            StringBuilder fromFile = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                fromFile.append(line);
                fromFile.append('\n');
            }
            return fromFile.toString();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }


    public void saveConvertedXmlFile(String filename) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            String text;
            text = String.valueOf(convertToXML());             // make our XML, convert StringBuilder to String
            bw.write(text);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // fill the list by created instances of Decathlete class with source values and total score
    protected void storeDataToList(String data) {
        try {
            String[] tempArray = data.split("\\n");
            for (String tempLine : tempArray) {
                addPersonToList(tempLine.split(";"));
            }
        } catch (Exception ex) {
            System.out.println("reading stopped");
        }
    }


    protected void addPersonToList(String[] temp) throws Exception {

        try {
            Decathlete person;
            person = new Decathlete(personId++,
                    temp[0].trim(),                         //     Decathlete's name        trim() just in case
                    Double.parseDouble(temp[1].trim()),     //1    100 meters
                    Double.parseDouble(temp[2].trim()),     //2    Long jump
                    Double.parseDouble(temp[3].trim()),     //3    Shot put
                    Double.parseDouble(temp[4].trim()),     //4    High jump
                    Double.parseDouble(temp[5].trim()),     //5    400 meters
                    Double.parseDouble(temp[6].trim()),     //6    110 meters hurdles
                    Double.parseDouble(temp[7].trim()),     //7    Discus throw
                    Double.parseDouble(temp[8].trim()),     //8    Pole vault
                    Double.parseDouble(temp[9].trim()),     //9    Javelin throw
                    temp[10].trim());                       //10   1500 meters

            // calculate and set total score
            person.setTotalScore(computeTotalScore(person));
            list.add(person);

        } catch (Exception ex) {
            System.out.println("probably wrong format of source data");
            throw new Exception();
        }
    }


    protected int computeTotalScore(Decathlete person) {

        // Calculate scores by formula
        int score1 = (int) Math.round(25.4347 * Math.pow((18 - person.getHundredMeters1()), 1.81));  //1   100 meters
        int score2 = (int) Math.round(90.5674 * Math.pow((person.getLongJump2() - 2.2), 1.4));       //2   Long jump
        int score3 = (int) Math.round(51.39 * Math.pow((person.getShotPut3() - 1.5), 1.05));         //3   Shot put
        int score4 = (int) Math.round(585.64 * Math.pow((person.getHighJump4() - 0.75), 1.42));      //4   High jump
        int score5 = (int) Math.round(1.53775 * Math.pow((82 - person.getFourHundred5()), 1.81));    //5   400 meters
        int score6 = (int) Math.round(5.74352 * Math.pow((28.5 - person.getHurdles6()), 1.92));      //6   110 meters hurdles
        int score7 = (int) Math.round(12.91 * Math.pow((person.getDiscusThrow7() - 4), 1.1));        //7   Discus throw
        int score8 = (int) Math.round(140.182 * Math.pow((person.getPoleVault8() - 1), 1.35));       //8   Pole vault
        int score9 = (int) Math.round(10.14 * Math.pow((person.getJavelinThrow9() - 7), 1.08));      //9   Javelin throw

        String tempScore = person.getThousHalf10();

        // separate minutes and seconds by first dot '.' and convert them into seconds
        double minutesToSec = 60 * (Double.parseDouble(tempScore.substring(0, tempScore.indexOf('.'))));
        double seconds = Double.parseDouble(tempScore.substring(tempScore.indexOf('.') + 1));

        int score10 = (int) Math.round(0.03768 * Math.pow((480 - (minutesToSec + seconds)), 1.85));  //10  1500 meters

        // Calculate total score
        int totalScore = score1 + score2 + score3 + score4 + score5 + score6 + score7
                + score8 + score9 + score10;

        return totalScore;
    }


    protected void computePlace() {

        int place = 1;
        for (Decathlete person : list) {
            person.setPlace(String.valueOf(place++));
        }

        // check if there are identical results
        for (int i = 0; i < list.size() - 1; i++) {

            if (Double.compare(list.get(i).getTotalScore(), list.get(i + 1).getTotalScore()) == 0) {

                list.get(i).setPlace(String.valueOf(i + 1) + "-" + String.valueOf(i + 2));
                list.get(i + 1).setPlace(String.valueOf(i + 1) + "-" + String.valueOf(i + 2));
            }
        }
    }


    public StringBuilder convertToXML() {

        StringBuilder xml = new StringBuilder();

        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<decathlon>\n");
        for (Decathlete person : list) {
            xml.append("  <decathlete>\n");
            xml.append("    <name>" + person.getName() + "</name>\n");
            xml.append("    <place>" + String.valueOf(person.getPlace()) + "</place>\n");
            xml.append("    <totalscore>" + String.valueOf(person.getTotalScore()) + "</totalscore>\n");
            xml.append("    <hundredmeters>" + String.valueOf(person.getHundredMeters1()) + "</hundredmeters>\n");
            xml.append("    <longjump>" + String.valueOf(person.getLongJump2()) + "</longjump>\n");
            xml.append("    <shotput>" + String.valueOf(person.getShotPut3()) + "</shotput>\n");
            xml.append("    <highjump>" + String.valueOf(person.getHighJump4()) + "</highjump>\n");
            xml.append("    <fourhundred>" + String.valueOf(person.getFourHundred5()) + "</fourhundred>\n");
            xml.append("    <hurdles>" + String.valueOf(person.getHurdles6()) + "</hurdles>\n");
            xml.append("    <discusthrow>" + String.valueOf(person.getDiscusThrow7()) + "</discusthrow>\n");
            xml.append("    <polevault>" + String.valueOf(person.getPoleVault8()) + "</polevault>\n");
            xml.append("    <javelinthrow>" + String.valueOf(person.getJavelinThrow9()) + "</javelinthrow>\n");
            xml.append("    <thousehalf>" + String.valueOf(person.getThousHalf10()) + "</thousehalf>\n");
            xml.append("  </decathlete>\n");
        }
        xml.append("</decathlon>");

        return xml;
    }


    // used for tests
    public ArrayList<Decathlete> getList() {
        return list;
    }


}
