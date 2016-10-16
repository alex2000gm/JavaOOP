/**
 * Created by alex2000 on 09.10.16.
 */
public class ContactMain {
    public static void main(String[] args) {
        Contact alexey = new Contact("Alexey","Dudchenko","99999999999");
        Contact petrov = new Contact("Ivan","Petrov","77777777");

        System.out.println(alexey.getName());
        alexey.setName("Alexander");
        petrov.setPhone("3333333333");
        System.out.println(alexey.getName());
        alexey.print();
        petrov.print();
    }
}
