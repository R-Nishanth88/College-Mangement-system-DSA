import java.util.*;

class Student {
    int rollNo;
    String name;
    String course;
    float fees=100000;

    public Student(int rollNo, String name, String course, float fees) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.fees =this.fees - fees;
    }
}


class CollegeManagementSystem {
    public static LinkedList<Student> students = new LinkedList<>();
    static ArrayList<Integer> roll=new ArrayList<>();
    public static int[] fees = {10000, 20000, 30000};
    public static String[] courses = {"CSE", "ECE", "MECH","AI&DS"};
    static ArrayList<String> coursess=new ArrayList<>(Arrays.asList(courses));

    public static String[] subjects = {"Maths", "Physics", "Chemistry", "English", "Computer Science"};
    public static int[][] studentMarks = new int[100][5];
    public static int numStudents = 0;

    static Scanner sc = new Scanner(System.in);

    public void addStudent(int rollNo, String name, String course, float fees) {
        Student student = new Student(rollNo, name, course, fees);
        students.add(student);
        studentMarks[numStudents] = new int[5];
        numStudents++;
    }

    public void displayStudents() {
        System.out.println("Roll No\tName\tCourse\tFees");
        for (Student student : students) {
            System.out.println(student.rollNo + "\t" + student.name + "\t" + student.course + "\t" + student.fees);
        }
    }

    public void displayCourses() {
        System.out.println("Courses offered:");
        for (String course : courses) {
            System.out.println(course);
        }
    }

    public void displayFees() {
        System.out.println("Enter roll number of student");
        int rollNo = sc.nextInt();
        Student cstudent = null;
        for(Student a:students){
            if(a.rollNo == rollNo)
            cstudent=a;
        }
        if (cstudent == null)
        System.out.println("No such student registered");
        else{
            System.out.println("Fees to be paid = "+cstudent.fees);
            System.out.println("Enter the amount you want to pay");
            float amount = sc.nextFloat();
            if(amount>cstudent.fees){
                System.out.println("Excess amount paid\nRefund = "+(amount-cstudent.fees));
                cstudent.fees=0;
            }
            else{
                cstudent.fees-=amount;
                System.out.println("Amount paid successfully\nRemaining fees = "+cstudent.fees);
            }
        }
    }

    public void enterMarks(int rollNo, int marks[]) {
        for (int i = 0; i < 5; i++) {
            studentMarks[rollNo - 1][i] = marks[i];
        }
    }

    public void displayMarks(int rollNo) {
        System.out.print("Roll No ");
        int total = 0;
        System.out.println(rollNo);
        for (int i = 0; i < 5; i++) {
            total += studentMarks[rollNo - 1][i];
            System.out.println(subjects[i]+" " + studentMarks[rollNo - 1][i]);
        }
        System.out.println();
        System.out.println("Total: " + total);
    }

    public void calculatePercentage(int rollNo) {
        int total = 0;
        for (int i = 0; i < 5; i++) {
            total += studentMarks[rollNo - 1][i];
        }
        float percentage = (float) total / 500 * 100;
        System.out.println("Percentage: " + percentage + "%");
    }

    public static void main(String[] args) {
        CollegeManagementSystem cms = new CollegeManagementSystem();


        int choice;
        do {
            System.out.println("\nCollege Management System\n1. Add Student\n2. Display Students\n3. Display Courses\n4. Display Fees\n5. Enter Marks\n6. Display Marks\n7. Calculate Percentage\n8. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter roll number: ");
                    int rollNo = sc.nextInt();
                    while(roll.contains(rollNo) == true){
                        System.out.println("Roll no already exist\nEnter different Roll Number");
                        rollNo = sc.nextInt();
                    }
                    roll.add(rollNo);
                    System.out.print("Enter name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Enter course: ");
                    String Ccourse;int chc;
                    do{
                        System.out.println("\nChoose one between this ");
                        for (int i=0;i<coursess.size();i++){
                            System.out.println((i+1)+" for "+coursess.get(i));
                        }
                        System.out.println();
                        chc = sc.nextInt();
                        chc-=1;
                    }while(!(chc>=0 && chc<=3));
                    Ccourse = coursess.get(chc);
                    System.out.print("Enter fees: ");
                    chc=0;
                    do{
                        System.out.println("\nChoose one between this ");
                        for (int i=0;i<fees.length;i++){
                            System.out.println((i+1)+" for "+fees[i]);
                        }
                        System.out.println();
                        chc = sc.nextInt();
                        chc-=1;
                    }while(!(chc>=0 && chc<=2));
                    float fee = fees[chc];
                    cms.addStudent(rollNo, name, Ccourse, fee);
                    break;
                case 2:
                    cms.displayStudents();
                    break;
                case 3:
                    cms.displayCourses();
                    break;
                case 4:
                    cms.displayFees();
                    break;
                case 5:
                    System.out.print("Enter roll number: ");
                    int rollNoToEnterMarks = sc.nextInt();
                    System.out.println("Enter marks for 5 subjects:\n");
                    int marks[] = new int[5];
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Enter marks for "+subjects[i]);
                        marks[i] = sc.nextInt();
                        System.out.println();
                    }
                    cms.enterMarks(rollNoToEnterMarks, marks);
                    break;
                case 6:
                    System.out.print("Enter roll number: ");
                    int rollNoToDisplayMarks = sc.nextInt();
                    cms.displayMarks(rollNoToDisplayMarks);
                    break;
                case 7:
                    System.out.print("Enter roll number: ");
                    int rollNoToCalculatePercentage = sc.nextInt();
                    cms.calculatePercentage(rollNoToCalculatePercentage);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }
}

