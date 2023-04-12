import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
     Person person=new Person("bavasir",18);
        person.setIdentifyCode("1744027528");
        person.identifyCodeAdjustment("17744027528");
     AidsTypeExperiment aidsTypeExperiment=new AidsTypeExperiment(person);
     aidsTypeExperiment.checking();
     BloodTypeExperiment bloodTypeExperiment=new BloodTypeExperiment(person);
     AnemiaTypeExperiment anemiaTypeExperiment=new AnemiaTypeExperiment(person);
     ThyroidTypeExperiment thyroidTypeExperiment=new ThyroidTypeExperiment(person);
     thyroidTypeExperiment.runOperation();
     bloodTypeExperiment.checking();
     anemiaTypeExperiment.runOperation();
     anemiaTypeExperiment.gettingTheRequests();
     aidsTypeExperiment.makingPrivate();
     Request request=new Request(person,aidsTypeExperiment);
     Request request1=new Request(person,anemiaTypeExperiment);
     Request request2=new Request(person,thyroidTypeExperiment);
     thyroidTypeExperiment.gettingTheRequests();
     aidsTypeExperiment.gettingTheRequests();
     Laboratory.getLaboratory().getRequests().add(request1);
     Laboratory.getLaboratory().getRequests().add(request);
     for (Request request3:Laboratory.getLaboratory().getRequests())
     {
         System.out.println(request2.getExperiment().toString());
     }
        System.out.println("____________________________________________");
        System.out.println(person.showList());

    }
}

//-------------------------------------------------------------------------------------------------
class Person {
    private String identifyCode;
    private ArrayList<Experiment> experimentsList;
    private String disease;
    private int age;

    public Person( String disease, int age) {
        this.disease = disease;
        this.age = age;
        this.experimentsList = new ArrayList<Experiment>();
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public ArrayList<Experiment> getExperimentsList() {
        return experimentsList;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public void setExperimentsList(ArrayList<Experiment> experimentsList) {
        this.experimentsList = experimentsList;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String identifyCodeAdjustment(String identifyCode1)
    {
        for (Person person:Laboratory.getLaboratory().getTestedPersons())
        {
            if (person.identifyCode.equals(identifyCode1))
            {   if (this.equals(person)==false)
            {return "repetitive identifyCode";}
            }
        }
        setIdentifyCode(identifyCode1);
        Laboratory.getLaboratory().getTestedPersons().add(this);
        return "identifyCode is ok";}

    @Override
    public String toString() {
        return "Person{" +
                "identifyCode='" + identifyCode + '\'' +
                ", disease='" + disease + '\'' +
                ", age=" + age +
                '}';
    }
    public String showList()
    {
        String result=" ";
        for (Experiment experiment:getExperimentsList())
        {
            result+=experiment.toString()+"\n";
        }
    return result;}
}

//-------------------------------------------------------------------------------------------------
class Laboratory {
    private static Laboratory laboratory = new Laboratory("Heshmat library");
    private String laboratoryName;
    private ArrayList<Experiment> doneTests = new ArrayList<>();
    private ArrayList<Person> testedPersons = new ArrayList<>();
   // private ArrayList<staticExperiments> staticTests = new ArrayList<>();
    private ArrayList<Request>requests=new ArrayList<>();

    private Laboratory(String libraryName) {
        this.laboratoryName = libraryName;
    }

    public static Laboratory getLaboratory() {
        return laboratory;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String libraryName) {
        this.laboratoryName = libraryName;
    }

    public ArrayList<Experiment> getDoneTests() {
        return doneTests;
    }

    public void setDoneTests(ArrayList<Experiment> doneTests) {
        this.doneTests = doneTests;
    }

    public ArrayList<Person> getTestedPersons() {
        return testedPersons;
    }

    public void setTestedPersons(ArrayList<Person> testedPersons) {
        this.testedPersons = testedPersons;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    //    public ArrayList<staticExperiments> getStaticTests() {
//        return staticTests;
//    }
//
//    public void setStaticTests(ArrayList<staticExperiments> staticTests) {
//        this.staticTests = staticTests;
//    }
    public void addToLists(Experiment experiment)
    {
        doneTests.add(experiment);
        for (Experiment experiment1:doneTests)
        {
            if (testedPersons.contains(experiment1.getPerson())==false)
            {
                testedPersons.add(experiment1.getPerson());
            }
        }
    }
}
//-------------------------------------------------------------------------------------------------

abstract class  Experiment {
    private Person person;
    private boolean personTest;
    private static int testID = 0;
    private int testId;
    private int CBC;
    private int BMP;
    private int bloodPressure;

    public Experiment(Person person) {
        testID++;
        this.testId=testID;
        this.person = person;
        Laboratory.getLaboratory().addToLists(this);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isPersonTest() {
        return personTest;
    }

    public void setPersonTest(boolean personTest) {
        this.personTest = personTest;
    }

    public static int getTestID() {
        return testID;
    }

    public static void setTestID(int testID) {
        Experiment.testID = testID;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getCBC() {
        return CBC;
    }

    public void setCBC(int CBC) {
        this.CBC = CBC;
    }

    public int getBMP() {
        return BMP;
    }

    public void setBMP(int BMP) {
        this.BMP = BMP;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public abstract void runOperation();
    public abstract void sendResult();

    @Override
    public String toString() {
        return "Experiment{" +
                "person=" + person.toString() +
                ", personTest=" + personTest +
                ", testId=" + testId +
                ", CBC=" + CBC +
                ", BMP=" + BMP +
                ", bloodPressure=" + bloodPressure +
                '}';
    }
}
//-------------------------------------------------------------------------------------------------
class BloodTypeExperiment extends Experiment implements Cloneable,ICheckAnswer
{   private boolean laboratoryAgreement=false;
    private int testCounter=-1;
    public BloodTypeExperiment(Person person) {
        super(person);
    }

    public int getTestCounter() {
        return testCounter;
    }

    @Override
    public void runOperation() {
        setCBC(getRandomNumber(20,170));
        setBMP(getRandomNumber(35,220));
        setBloodPressure(getRandomNumber(4,110));
        testCounter++;
    }

    public boolean isLaboratoryAgreement() {
        return laboratoryAgreement;
    }

    public void setLaboratoryAgreement(boolean laboratoryAgreement) {
        this.laboratoryAgreement = laboratoryAgreement;
    }

    public void setTestCounter(int testCounter) {
        this.testCounter = testCounter;
    }

    @Override
    public Object clone() {
        BloodTypeExperiment bloodTypeExperiment=new BloodTypeExperiment(this.getPerson());
        bloodTypeExperiment.setBMP(this.getBMP());
        bloodTypeExperiment.setBloodPressure(this.getBloodPressure());
        bloodTypeExperiment.setTestId(this.getTestId());
        bloodTypeExperiment.setPersonTest(this.isPersonTest());
        bloodTypeExperiment.setCBC(getCBC());
        bloodTypeExperiment.setTestCounter(this.getTestCounter());
        bloodTypeExperiment.setLaboratoryAgreement(this.isLaboratoryAgreement());

    return bloodTypeExperiment;}

    @Override
    public void sendResult() {
        this.getPerson().getExperimentsList().add((BloodTypeExperiment)clone());
        Laboratory.getLaboratory().addToLists(this);
    }
    public boolean checking() {
        runOperation();
        if ((getCBC() >= BloodTypeRange.CBC.getRange_one() && getCBC() < BloodTypeRange.CBC.getRange_two()) && (getBloodPressure() >= BloodTypeRange.BLOOD_PRESSURE.getRange_one() && getBloodPressure() < BloodTypeRange.BLOOD_PRESSURE.getRange_two()) && (getBMP() >= BloodTypeRange.BMP.getRange_one() && getBMP() < BloodTypeRange.BMP.getRange_two())) {
                    setLaboratoryAgreement(true);
                    sendResult();

        }
            if (isLaboratoryAgreement()==false)
            {wrongAnswer();}
        return laboratoryAgreement;}

    @Override
    public void wrongAnswer() {
        if (testCounter<=2)
        {  if(this.isLaboratoryAgreement()==false)
        {checking();}
        }
        else {
            sendResult();
        }
    }

    @Override
    public String toString() {
        return super.toString()+" Blood{" +
                "laboratoryAgreement=" + this.laboratoryAgreement +
                ", testCounter=" + testCounter +
                '}';
    }
}
//-------------------------------------------------------------------------------------------------

class AidsTypeExperiment extends Experiment implements Cloneable,ICheckAnswer,IMakingPrivate,IRequest
{
    private boolean laboratoryAgreement=false;
    private String heartDisease;
    private String [] array={"HEALTHY" ,"CORONARY" ,"STROKE" ,"PERIPHERAL_ARTERIAL" ,"AORTIC" ,"NOT","FINDFAILD","ERORR"};
    private int testCounter=-1;
    public AidsTypeExperiment(Person person) {
        super(person);
    }

    public boolean isLaboratoryAgreement() {
        return laboratoryAgreement;
    }

    public void setLaboratoryAgreement(boolean laboratoryAgreement) {
        this.laboratoryAgreement = laboratoryAgreement;
    }

    public String getHeartDisease() {
        return heartDisease;
    }

    public String[] getArray() {
        return array;
    }

    public int getTestCounter() {
        return testCounter;
    }

    public void setHeartDisease(String heartDisease) {
        this.heartDisease = heartDisease;
    }

    public void setTestCounter(int testCounter) {
        this.testCounter = testCounter;
    }

    @Override
    public void runOperation() {
        setCBC(getRandomNumber(20,190));
        setBMP(getRandomNumber(35,350));
        setBloodPressure(getRandomNumber(4,160));
        int index=getRandomNumber(0,8);
        this.setHeartDisease(array[index]);
        testCounter++;
    }

    @Override
    public Object clone() {
        AidsTypeExperiment aidsTypeExperiment=new AidsTypeExperiment(this.getPerson());
        aidsTypeExperiment.setLaboratoryAgreement(this.isLaboratoryAgreement());
        aidsTypeExperiment.setBMP(this.getBMP());
        aidsTypeExperiment.setCBC(this.getCBC());
        aidsTypeExperiment.setBloodPressure(this.getBloodPressure());
        aidsTypeExperiment.setTestId(this.getTestId());
        aidsTypeExperiment.setPersonTest(this.isPersonTest());
        aidsTypeExperiment.setHeartDisease(this.getHeartDisease());
        aidsTypeExperiment.setTestCounter(this.getTestCounter());
        return aidsTypeExperiment;}
    public void sendResult() {
        this.getPerson().getExperimentsList().add( makingPrivate());
        Laboratory.getLaboratory().addToLists(this);
    }

    @Override
    public boolean checking() {
            runOperation();
            if ((getCBC() >= AIDSRange.CBC.getRange_one() && getCBC() < AIDSRange.CBC.getRange_two()) && (getBloodPressure() >= AIDSRange.BLOOD_PRESSURE.getRange_one() && getBloodPressure() < AIDSRange.BLOOD_PRESSURE.getRange_two()) && (getBMP() >= AIDSRange.BMP.getRange_one() && getBMP() < AIDSRange.BMP.getRange_two())) {
                for (String string : AIDSRange.getDiseaseList()) {
                    if (string.equals(heartDisease)) {
                        setLaboratoryAgreement(true);
                        sendResult();
                    }
                }
            }
            if (isLaboratoryAgreement()==false)
            {wrongAnswer();}

    return laboratoryAgreement;}

    @Override
    public void wrongAnswer() {
     if (laboratoryAgreement==false && testCounter<2)
     {
     checking();
     }
     else if (laboratoryAgreement==true || testCounter>=2){
       sendResult();
     }
    }

    @Override
    public Experiment makingPrivate() {
        AidsTypeExperiment aidsTypeExperiment=(AidsTypeExperiment) this.clone();
        aidsTypeExperiment.setBMP(-1000);
        aidsTypeExperiment.setBloodPressure(-1000);
        return aidsTypeExperiment;
    }

    @Override
    public void gettingTheRequests() {
              if (this.getPerson().getDisease()==null && this.getPerson().getAge()>=18)
              {
                  for (Experiment experiment:this.getPerson().getExperimentsList())
                  {
                      if (experiment.getBMP()==-1000 && experiment.getBloodPressure()==-1000)
                      {
                          this.getPerson().getExperimentsList().remove(experiment);
                          break;
                      }
                  }
                  this.getPerson().getExperimentsList().add((AidsTypeExperiment)clone());
              }
    }

    @Override
    public String toString() {
        return super.toString()+" AidsTypeExperiment{" +
                "laboratoryAgreement=" + laboratoryAgreement +
                ", heartDisease='" + heartDisease + '\'' +
                ", testCounter=" + testCounter +
                '}';
    }
}
//-------------------------------------------------------------------------------------------------
class ThyroidTypeExperiment extends Experiment implements Cloneable,IRequest,IMakingPrivate
{
    private double heartPressure;

    public ThyroidTypeExperiment(Person person) {
        super(person);

    }

    public double getHeartPressure() {
        return heartPressure;
    }

    public void setHeartPressure(double heartPressure) {
        this.heartPressure = heartPressure;
    }

    @Override
    public void runOperation() {
        int CbC=getRandomNumber(3,7);
        int fact=1;
        for (int counter=2 ; counter<=CbC ;counter++)
        {
            fact=fact*counter;
        }
        setCBC(fact*10);
        setBMP(getCBC()-CbC);
        setBloodPressure(getCBC()/getBMP());
        setHeartPressure(getRandomNumber(120,129));
        sendResult();
    }

    @Override
    public Object clone() {
        ThyroidTypeExperiment thyroidTypeExperiment=new ThyroidTypeExperiment(this.getPerson());
        thyroidTypeExperiment.setBMP(this.getBMP());
        thyroidTypeExperiment.setBloodPressure(this.getBloodPressure());
        thyroidTypeExperiment.setTestId(this.getTestId());
        thyroidTypeExperiment.setPersonTest(this.isPersonTest());
        thyroidTypeExperiment.setCBC(getCBC());
        thyroidTypeExperiment.setHeartPressure(this.getHeartPressure());
    return thyroidTypeExperiment;}
    public void sendResult() {
        this.getPerson().getExperimentsList().add(makingPrivate());
        Laboratory.getLaboratory().addToLists(this);
    }
    public Experiment makingPrivate() {
        ThyroidTypeExperiment thyroidTypeExperiment=(ThyroidTypeExperiment) this.clone();
        thyroidTypeExperiment.setBMP(-500);
        thyroidTypeExperiment.setBloodPressure(-500);
        return thyroidTypeExperiment;
    }

    @Override
    public void gettingTheRequests() {
        if (this.getPerson().getDisease()==null)
        {
            for (Experiment experiment:this.getPerson().getExperimentsList())
            {
                if (experiment.getBMP()==-500 && experiment.getBloodPressure()==-500)
                {
                    this.getPerson().getExperimentsList().remove(experiment);
                    break;
                }
            }
            this.getPerson().getExperimentsList().add((ThyroidTypeExperiment)clone());
        }
    }


    @Override
    public String toString() {
        return super.toString()+" ThyroidTypeExperiment{" +
                "heartPressure=" + heartPressure +
                '}';
    }
}
//-------------------------------------------------------------------------------------------------
class AnemiaTypeExperiment extends Experiment implements Cloneable,IRequest,IMakingPrivate
{   int testCounter=-1;
    public AnemiaTypeExperiment(Person person) {
        super(person);

    }

    @Override
    public void runOperation() {
        int CbC=(int) Math.sqrt(getRandomNumber(1,50));
        setCBC(CbC);
        setBMP(getCBC()/2);
        setBloodPressure(getCBC()/getBMP());
        testCounter++;
        sendResult();
    }

    public int getTestCounter() {
        return testCounter;
    }

    public void setTestCounter(int testCounter) {
        this.testCounter = testCounter;
    }

    @Override
    public Object clone() {
        AnemiaTypeExperiment anemiaTypeExperiment=new AnemiaTypeExperiment(this.getPerson());
        anemiaTypeExperiment.setBMP(this.getBMP());
        anemiaTypeExperiment.setBloodPressure(this.getBloodPressure());
        anemiaTypeExperiment.setTestId(this.getTestId());
        anemiaTypeExperiment.setPersonTest(this.isPersonTest());
        anemiaTypeExperiment.setCBC(getCBC());
        anemiaTypeExperiment.setTestCounter(this.getTestCounter());
        return anemiaTypeExperiment;
    }
    public void sendResult() {
        this.getPerson().getExperimentsList().add(this.makingPrivate());
        Laboratory.getLaboratory().addToLists(this);
    }
    public Experiment makingPrivate() {
        AnemiaTypeExperiment anemiaTypeExperiment=(AnemiaTypeExperiment) this.clone();
        anemiaTypeExperiment.setBMP(-50);
        anemiaTypeExperiment.setBloodPressure(-50);
        return anemiaTypeExperiment;
    }

    @Override
    public void gettingTheRequests() {
        if (this.getPerson().getAge()>=18)
        {
            for (Experiment experiment:this.getPerson().getExperimentsList())
            {
                if (experiment.getBMP()==-50 && experiment.getBloodPressure()==-50)
                {
                    this.getPerson().getExperimentsList().remove(experiment);
                    break;
                }
            }
            this.getPerson().getExperimentsList().add((AnemiaTypeExperiment)clone());
        }
    }

    @Override
    public String toString() {
        return "AnemiaType "+super.toString()+" testCounter:"+testCounter;
    }
}
//-------------------------------------------------------------------------------------------------
interface ICheckAnswer
{
    boolean checking();
    void wrongAnswer();
}
//-------------------------------------------------------------------------------------------------
enum  AIDSRange{
    CBC(20 , 180),
    BLOOD_PRESSURE(4 , 150),
    BMP(35 , 340);
    private enum HeartDisease{
        HEALTHY,
        CORONARY,
        STROKE,
        PERIPHERAL_ARTERIAL,
        AORTIC ;
    }

    private static String []DiseaseList={"HEALTHY","CORONARY","STROKE","PERIPHERAL_ARTERIAL","AORTIC"};
    private int range_one ;
    private int range_two;
    AIDSRange(int range_one, int range_two) {
        this.range_one = range_one;
        this.range_two = range_two;
    }
    public int getRange_one() {
        return range_one;
    }
    public int getRange_two() {
        return range_two;
    }

    public static String[] getDiseaseList() {
        return DiseaseList;
    }
}
//-------------------------------------------------------------------------------------------------
enum  BloodTypeRange{
    CBC(20 , 160),
    BLOOD_PRESSURE(4 , 100),
    BMP(35 , 210);

    private int range_one ;
    private int range_two;

    BloodTypeRange(int range_one, int range_two) {
        this.range_one = range_one;
        this.range_two = range_two;
    }
    public int getRange_one() {
        return range_one;
    }
    public int getRange_two() {
        return range_two;
    }

}
//-------------------------------------------------------------------------------------------------
interface IMakingPrivate
{
Experiment makingPrivate();
}
//-------------------------------------------------------------------------------------------------
class Request
{
    Person person;
    Experiment experiment;

    public Request(Person person, Experiment experiment) {
        this.person = person;
        this.experiment = experiment;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }
}
//-------------------------------------------------------------------------------------------------
interface IRequest
{
    void gettingTheRequests();
}
//-------------------------------------------------------------------------------------------------