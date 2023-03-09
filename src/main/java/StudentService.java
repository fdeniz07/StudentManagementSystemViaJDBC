import java.util.List;
import java.util.Scanner;

//3-Sttudents ile ilgili metotlar
public class StudentService {

    Scanner input = new Scanner(System.in);

    //reponun metodlarını kullanmak için
    StudentRepository repository = new StudentRepository();

    //9-Tablo olusturmak icin metot
    public void createTable() {
        repository.createTable();
    }

    //11-ögrenci kaydetme
    public void saveStudent() {

        System.out.println("Ad: ");
        String name = input.nextLine();

        System.out.println("Soyad: ");
        String lastName = input.nextLine();

        System.out.println("Sehir: ");
        String city = input.nextLine();

        System.out.println("Yas: ");
        int age = input.nextInt();
        input.nextLine();
        Student newStudent = new Student(name, lastName, city, age);
        repository.save(newStudent);
    }

    //12-tüm ögrencileri listeleme
    public void getAllStudents() {
        repository.findAll();
    }

    //15-ögrenci silme islemi
    public void deleteStudent(int id) {
        repository.delete(id);
    }

    //17-id ye göre ögrenci bulma
    public Student getStudentById(int id) {
        return repository.findStudentById(id);
    }

    //19-Ögrenciyi güncelleme
    public void updateStudent(int id) {
        //best practice olarak isler business da yazilmali
        Student student = getStudentById(id);
        if (student == null) {
            System.out.println("Ögrenci bulunamadi!");
        } else {
            System.out.println("Ad: ");
            String name = input.nextLine().trim();
            System.out.println("Soyad: ");
            String lastname = input.nextLine().trim();
            System.out.println("Sehir: ");
            String city = input.nextLine().trim();
            System.out.println("Yas: ");
            int age = input.nextInt();
            input.nextLine();
            //yeni degerler ile fieldlari güncelle
            student.setName(name);
            student.setLastname(lastname);
            student.setCity(city);
            student.setAge(age);
            repository.update(student);
        }
    }

    //21-Girilen ad veya soyad bilgisini iceren kayitlari getirme
    //kelime= act--> ad: soyad:React....
    public void listStudentsByNameOrLastname(){
        System.out.println("Ad veya soyad: ");
        String nameOrLastname=input.nextLine();
        //birden fazla kayıt dönebilir
        List<Student> studentList=repository.findStudentByNameOrLastName(nameOrLastname);
        //listedeki öğrencileri yazdıralım
        //liste boşsa?
        if(studentList.size()==0){
            System.out.println("Öğrenci bulunamadı.");
        }else {
            studentList.forEach(System.out::println);
        }
    }

}
