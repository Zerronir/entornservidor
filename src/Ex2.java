import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        // SINGLETON

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        s1.setValor(5);
        s2.setValor(7);

        System.out.printf("s1: %d s2: %d", s1.getValor(), s2.getValor());



        // CLONE

        RegistreEmpleat re = new RegistreEmpleat(1, "Pep", "Carrer patato", 20000.00);
        //...

        RegistreEmpleat re2 = (RegistreEmpleat) re.clone();
        re2.setId(re.getId() + 1);
        re2.setName("Raul");
        re2.setAddress("Calle 1");
        re2.setSalary(45000.00);

        System.out.printf("id: %d, name: %s, Address: %s, Salary: %f", re2.getId(), re2.getName(), re2.getAddress(), re2.getSalary());


        // DAO
        PersonaDAO dao = new PersonaDAOImplTest();
        List<Persona> perList = dao.all();
        perList.get(0).setId(1000);
        perList = dao.all();
        dao.update(new Persona(1, "Bill"));
        System.out.println(perList);


        //FAZADE
        InstrumentMaker im = new InstrumentMaker();

        im.playingDrums();

        //VIEWS
        Student tom = new Student("", "");
        tom.setName("Tom");
        tom.setCity("Palma");

        StudentView vista = new StudentView();
        StudentController controller = new StudentController(tom, vista);
        controller.updateView();

        // Construcció d'objectes
        MealBuilder mb = new MealBuilder();
        Meal forMary = mb.prepareVegMeal();

        System.out.println(forMary.toString());

        // FRONT CONTROLLER
        FrontController fc = new FrontController();
        fc.dispatchRequest("HOME");
        fc.dispatchRequest("INTERNAL");

        // FILTER O INTERCEPTOR
        /**
         *
         * Filtros para antes de que el FrontController haga la acción
         * Todo esto se debe hacer antes de pasar al controlador
         * Generará código de error o excepción o realizar la tarea correctamente
         *
         * */

        FilterManager fm = new FilterManager(new Target());
        fm.setFilter(new AuthenticationFilter());
        fm.setFilter(new DebugFilter());
        fm.setFilter(new LogFilter());

        Client client = new Client(fm);
        client.sendRequest("HOME");
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

// CONTROLLERS
class StudentView {
    void printStudentDetails(String name, String city){
        System.out.printf("Name: %s, City: %s", name, city);
    }
}

class StudentController {
    private Student model;
    private StudentView view;

    StudentController(Student model, StudentView view){
        this.model = model;
        this.view = view;
    }

    void updateView(){
        view.printStudentDetails(model.getName(), model.getCity());
    }

}

class Student {
    private String name;
    private String city;

    Student(String name, String city){
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

// CONSTRUCCIÓ D'OBJECTES
class MealBuilder {
    Meal prepareVegMeal(){
        Meal m = new Meal();
        m.addItem(new VegBurger());
        m.addItem(new Pepsi());
        return m;
    }
}

interface Pack {
    String pack();
}

interface Item {
    String getName();
    double getPrice();
    Pack packing();
}

class Meal {
    private List<Item> items = new ArrayList<>();

    void addItem(Item i){
        items.add(i);
    }

    double getCost(){
        /*double total = 0;
        for(Item i : items){
            total += i.getPrice();
        }
        return total;*/
        return items.stream().map(Item::getPrice).mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public String toString(){
        String s = "";
        for(Item i : items){
            s += String.format("Item: %s, Preu: %f, Packing: %s \n", i.getName(), i.getPrice(), i.packing());
        }

        s += String.format("Total cost: %f", this.getCost());

        return s;
    }
}

class Wrapper implements Pack{
    @Override
    public String pack() {
        return "Wrapper";
    }
}

class Bottle implements Pack {
    @Override
    public String pack() {
        return "Bottle";
    }
}

abstract class Burger implements Item {
    @Override
    public Pack packing() {
        return new Wrapper();
    }
}

class VegBurger extends Burger {
    @Override
    public String getName() {
        return "Hamburguesa vegana";
    }

    @Override
    public double getPrice() {
        return 4.50;
    }
}

abstract class Drink implements Item {
    @Override
    public Pack packing() {
        return new Bottle();
    }
}

class Pepsi extends Drink {
    @Override
    public String getName() {
        return "Pepsi";
    }

    @Override
    public double getPrice() {
        return 1.20;
    }
}

class Coke extends Drink {
    @Override
    public String getName() {
        return "Coke";
    }

    @Override
    public double getPrice() {
        return 1.60;
    }
}

// FRONT CONTROLLER -- > Dispatchers
class HomePageView {
    void show(){
        System.out.println("Home page");
    }
}

class InternalPageView {
    void show(){
        System.out.println("Internal page");
    }
}

class Dispatcher {
    private HomePageView hpv = new HomePageView();
    private InternalPageView ipv = new InternalPageView();

    void dispatch(String request) {
        if(request.equalsIgnoreCase("HOME")){
            hpv.show();
        }

        if(request.equalsIgnoreCase("INTERNAL")){
            ipv.show();
        }
    }

}

class FrontController {
    private Dispatcher dispatcher = new Dispatcher();

    void dispatchRequest(String request) {
        // Aquí podemos comprobar si el usuario está logueado
        dispatcher.dispatch(request);
    }


}

// FILTER // INTERCEPTOR
interface Filter {
    void execute(String request);
}

class AuthenticationFilter implements Filter {

    @Override
    public void execute(String request) {
        System.out.println("Authenticating user... " + request);
    }
}

class DebugFilter implements Filter {

    @Override
    public void execute(String request) {
        System.out.println("Debuggin... " + request);
    }
}

class LogFilter implements Filter {

    @Override
    public void execute(String request) {
        System.out.println("Loggin: " + request);
    }
}

class Target {
    void run (String request) {
        System.out.println("Running: " + request);
    }
}

class FilterChain {
    private List<Filter> filterList = new ArrayList<>();
    private Target target;

    void addFilter(Filter f) {
        filterList.add(f);
    }

    // Siempre se aplican a un controlador o a más de uno
    void setTarget(Target t) {
        this.target = t;
    }

    void execute(String request){
        filterList.forEach(f -> f.execute(request));
        target.run(request);
    }
}

class FilterManager {
    FilterChain fc;

    FilterManager(Target t){
        fc = new FilterChain();
        fc.setTarget(t);
    }

    void setFilter(Filter f) {
        fc.addFilter(f);
    }

    void filterRequest(String request){
        fc.execute(request);
    }

}

class Client {
    FilterManager fm;

    Client(FilterManager fma) {
        fm = fma;
    }

    void sendRequest(String req) {
        fm.filterRequest(req);
    }

}