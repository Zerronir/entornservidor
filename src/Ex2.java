import java.security.cert.CertificateParsingException;

public class Ex2 {
    public static void main(String[] args) {
        Singleton s1 = new Singleton();
        Singleton s2 = new Singleton();

        s1.setValor(5);
        s2.setValor(7);

        System.out.println(s1.getValor() + " " + s2.getValor());

    }
}

class Singleton {

    private int valor;

    private Singleton(){

    }

    public static Singleton getInstance(){
        return new Singleton();
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
