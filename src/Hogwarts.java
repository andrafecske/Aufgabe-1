import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Hogwarts {
    static class Student{
        private Integer ID;
        private String name;
        private Houses house;
        private String teacher;
        private int points;

        Student(Integer ID, String student, Houses house, String teacher, int points) {
            this.ID = ID;
            this.name = student;
            this.house = house;
            this.teacher = teacher;
            this.points = points;

        }

    }

    public static void main(String[] args) throws IOException {
        List<Student> students = lesePunkteAusDatei("punkte.txt");

        Scanner scanner = new Scanner(System.in);
        String userChoice;

        do {
            // Display the menu
            System.out.println("\nWählen Sie eine Option:");
            System.out.println("1. Zeige Studierende, deren Namen mit einem Großbuchstaben beginnen");
            System.out.println("2. Zeige Gryffindor Studierende alphabetisch sortiert");
            System.out.println("3. Berechne und speichere Hauspokal Ergebnis");
            System.out.println("4. Zeige wie viele Punkte ein Lehrer abgezogen/gegeben hat");
            System.out.println("5. Zeige wie viele Punkte ein Lehrer abgezogen/gegeben hat TOTAL");
            System.out.println("0. Beenden");

            // Get user's choice
            userChoice = scanner.nextLine();

            switch (userChoice) {

                case "0":
                    // Exit the program
                    System.out.println("Das Programm wird beendet.");
                    break;

                case "1":
                    // Prompt the user for a letter and show students starting with that letter
                    System.out.println("Geben Sie einen Buchstaben: ");
                    String letter = scanner.nextLine();
                    studentWithLetter(students, letter);
                    break;

                case "2":
                    // Show Gryffindor students sorted by name
                    studentsSortedName(students);
                    break;

                case "3":
                    // Calculate and save house cup results to a file
                    resultHauspokal("ergebnis.txt", students);
                    break;


                case "4":
                    System.out.println("Geben Sie ein Name eines Lehrers: ");
                    String name = scanner.nextLine();
                    filterByTeacher(name, students);
                    break;

                case "5":
                    System.out.println("Geben Sie ein Name eines Lehrers: ");
                    String name1 = scanner.nextLine();
                    filterByTeacherTotal(name1, students);
                    break;

                default:
                    // Handle invalid input
                    System.out.println("Ungültige Eingabe. Bitte wählen Sie eine gültige Option.");
                    break;
            }

        } while (!userChoice.equals("0"));  // Keep running until the user chooses to exit
    }

    //a
    public static List<Student> lesePunkteAusDatei(String dateiname) throws IOException {
        List<Student> studenten = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(dateiname));

        for (String line : lines) {
            String[] teile = line.split("&");
            int id = Integer.parseInt(teile[0]);
            String name = teile[1];
            Houses haus = Houses.valueOf(teile[2].toUpperCase());
            String teacher = teile[3];
            int punkte = Integer.parseInt(teile[4]);

            studenten.add(new Student(id, name, haus, teacher, punkte));
        }

        return studenten;
    }

    //b
    public static void studentWithLetter(List<Student> students, String letter) {
        System.out.println("Studierende mit dem Buchstaben " + letter + ":");
        students.stream()
                .filter(student -> student.name.charAt(0) == letter.toUpperCase().charAt(0))
                .map(student -> student.name)
                .distinct()
                .forEach(System.out::println);
    }

    //c
    public static void studentsSortedName(List<Student> studenten) {
        System.out.println("Sorted by name:");
        studenten.stream()
                .filter(student -> student.house == Houses.GRYFFINDOR)
                .sorted(Comparator.comparing(student -> student.name))
                .map(student -> student.name)
                .distinct()
                .forEach(System.out::println);
    }

    //d
    public static void resultHauspokal(String filename, List<Student> students) throws IOException {
        HashMap<Houses, Integer> points = new HashMap<>();
        for (Student student : students) {
            points.put(student.house, points.getOrDefault(student.house, 0) + student.points);
        }

        List<Map.Entry<Houses, Integer>> hausListe = new ArrayList<>(points.entrySet());
        hausListe.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
        for (Map.Entry<Houses, Integer> entry : hausListe) {
            writer.write(entry.getKey() + "#" + entry.getValue());
            writer.newLine();
        }
        writer.close();
        System.out.println("\nErgebnis des Hauspokals wurde in 'ergebnis.txt' gespeichert.");

    }

    public static void filterByTeacher(String teacherName, List<Student> students) {
        students.stream()
                .filter(student -> student.teacher.equals(teacherName))
                .map(student ->
                        String.format(
                                "Student: %-20s  Points: %-4d",
                                student.name,
                                student.points
                        )
                )

                .distinct()
                .forEach(System.out::println);
    }

    public static void filterByTeacherTotal(String teacherName, List<Student> students) {
        HashMap<String, Integer> studentPoints = new HashMap<>();

       students.stream()
               .filter(student -> student.teacher.equals(teacherName))
               .forEach(student ->
                       studentPoints.put(student.name, studentPoints.getOrDefault(student.name, 0) + student.points)
               );

        studentPoints.forEach((name, points) ->
                System.out.printf("Student: %-20s  Total Points: %-4d%n", name, points)
        );
    }
}
