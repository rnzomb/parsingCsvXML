package parsingCsvXML;


public class Decathlete {

    private int id;                      // there is no ID field by the task conditions, but generally must be
    private String name;
    private double hundredMeters1;       //1    100 meters
    private double longJump2;            //2    Long jump
    private double shotPut3;             //3    Shot put
    private double highJump4;            //4    High jump
    private double fourHundred5;         //5    400 meters
    private double hurdles6;             //6    110 meters hurdles
    private double discusThrow7;         //7    Discus throw
    private double poleVault8;           //8    Pole vault
    private double javelinThrow9;        //9    Javelin throw
    private String thousHalf10;          //10   1500 meters    String, because points need to be converted into seconds
    private int totalScore;
    private String place;


    public Decathlete(int id, String name, double hundredMeters1, double longJump2, double shotPut3,
                      double highJump4, double fourHundred5, double hurdles6, double discusThrow7,
                      double poleVault8, double javelinThrow9, String thousHalf10) {
        this.id = id;
        this.name = name;
        this.hundredMeters1 = hundredMeters1;
        this.longJump2 = longJump2;
        this.shotPut3 = shotPut3;
        this.highJump4 = highJump4;
        this.fourHundred5 = fourHundred5;
        this.hurdles6 = hurdles6;
        this.discusThrow7 = discusThrow7;
        this.poleVault8 = poleVault8;
        this.javelinThrow9 = javelinThrow9;
        this.thousHalf10 = thousHalf10;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getHundredMeters1() {
        return hundredMeters1;
    }

    public double getLongJump2() {
        return longJump2;
    }

    public double getShotPut3() {
        return shotPut3;
    }

    public double getHighJump4() {
        return highJump4;
    }

    public double getFourHundred5() {
        return fourHundred5;
    }

    public double getHurdles6() {
        return hurdles6;
    }

    public double getDiscusThrow7() {
        return discusThrow7;
    }

    public double getPoleVault8() {
        return poleVault8;
    }

    public double getJavelinThrow9() {
        return javelinThrow9;
    }

    public String getThousHalf10() {
        return thousHalf10;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getPlace() {
        return place;
    }


    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
