import java.security.cert.CertificateParsingException;

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
