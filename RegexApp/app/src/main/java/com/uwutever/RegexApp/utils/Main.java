import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegexController controller = RegexController.getController();
        while(true) {
            System.out.print("Regex pattern: ");
            String pattern = scanner.nextLine();
            if (pattern.equals("exit"))
                break;
            System.out.print("Test string: ");
            String testStr = scanner.nextLine();
            if (testStr.equals("exit"))
                break;
            List<FieldClassification.Match> matches = controller.getMatches(pattern, testStr);
            System.out.println("Matches: ");
            for (Match match : matches) {
                System.out.println(String.format("\t Start=%1$d, End=%2$d, Content=%3$s",
                        match.getStart(), match.getEnd(), match.getContent()));
            }
        }
    }
}
