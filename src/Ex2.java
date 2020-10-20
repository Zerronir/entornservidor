import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        /* SINGLETON

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        s1.setValor(5);
        s2.setValor(7);

        System.out.printf("s1: %d s2: %d", s1.getValor(), s2.getValor());
        */


        /* CLONE

        RegistreEmpleat re = new RegistreEmpleat(1, "Pep", "Carrer patato", 20000.00);
        //...

        RegistreEmpleat re2 = (RegistreEmpleat) re.clone();
        re2.setId(re.getId() + 1);
        re2.setName("Raul");
        re2.setAddress("Calle 1");
        re2.setSalary(45000.00);

        System.out.printf("id: %d, name: %s, Address: %s, Salary: %f", re2.getId(), re2.getName(), re2.getAddress(), re2.getSalary());*/


        /* DAO
        PersonaDAO dao = new PersonaDAOImplTest();
        List<Persona> perList = dao.all();
        perList.get(0).setId(1000);
        perList = dao.all();
        dao.update(new Persona(1, "Bill"));
        System.out.println(perList);*/

        //FAZADE
        InstrumentMaker im = new InstrumentMaker();

        im.playingDrums();


    }
}

// Clase Singleton
class Singleton {

    private int valor;
    private static Singleton singleton;

    private Singleton(){}

    //syncronized --> Significa que se sincroniza con los procesadores del ordenador

    public synchronized static Singleton getInstance(){
        if(singleton == null){
            singleton =  new Singleton();
        }
        return singleton;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}

// Clase
class RegistreEmpleat implements Cloneable{
    private int id;
    private String name, address;
    private double salary;

    RegistreEmpleat(int id, String name, String address, double salary){
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new RegistreEmpleat(this.id, this.name, this.address, this.salary);
    }
}

// DAO --> Data Access Object
interface PersonaDAO {
    List<Persona> all();
    void update(Persona p);
    void delete(Persona p);
    void create(Persona p);



}

class PersonaDAOImplTest implements PersonaDAO{
    List<Persona> personaList = new ArrayList<>();

    PersonaDAOImplTest(){
        personaList.add(new Persona(1, "Bill Gates"));
        personaList.add(new Persona(2, "James Bond"));
        personaList.add(new Persona(3, "Marian"));
        personaList.add(new Persona(4, "Maria Jhones"));
        personaList.add(new Persona(5, "Anna Smith"));
    }

    @Override
    public List<Persona> all() {
        return this.personaList.stream()
                .map(p -> new Persona(p.getId(), p.getName()))
                .collect(Collectors.toList());

    }

    @Override
    public void update(Persona p) {
        //all().stream().map(per -> );
        this.personaList.set(p.getId(), p);

    }

    @Override
    public void delete(Persona persona) {
        personaList.removeIf(p -> p.getId() == persona.getId());
    }

    @Override
    public void create(Persona p) {
        this.personaList.add(p);
    }
}

class Persona {
    private int id;
    private String name;

    Persona(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("id: %d, Name: %s", this.id, this.name);
    }
}

// FACER

class InstrumentMaker {
    private Violin violin = new Violin();
    private Drums drums = new Drums();
    private Guitar guitar = new Guitar();

    void playingViolin(){
        this.violin.play();
    }

    void playingDrums(){
        this.drums.play();
    }

    void playingGuitar(){
        this.guitar.play();
    }
}

interface Instrument {
    void play();
}

class Violin implements Instrument {

    @Override
    public void play() {
        System.out.println("Playing violin");
    }
}


class Drums implements Instrument{
    @Override
    public void play() {
        System.out.println("Playing drums");
    }
}

class Guitar implements Instrument {
    @Override
    public void play() {
        System.out.println("Playing guitar");
    }
}