import com.ideas2it.employee.view.EmployeeView;
import com.ideas2it.employee.view.ProjectView;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;

/**
 * Here we initialize the programm for creating employee database
 * display and make changes for these employee details.
 * @version 1.8 13-09-2022.
 * @author  Naveenkumar R
 */
public class EmployeeManagementSystem {

    static Logger logger = LogManager.getLogger(EmployeeManagementSystem.class);

    /**
     * Used to choose operation between employee and project
     * management systems.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int operations = 0;

        do {

            try {
                System.out.println("Enter the opertaion to be done \n\n"
                                   + "1.EMPLOYEE MANAGEMENT \n2.PROJECT MANAGEMENT"
                                   + "\n3.EXIT");
                operations = Integer.valueOf(scanner.nextLine());

                switch (operations) {
                        case 1:
                            EmployeeView employeeView = new EmployeeView();
                            employeeView.chooseOperation();
                            break;

                        case 2:
                            ProjectView projectView = new ProjectView();
                            projectView.chooseOperation();
                            break;

                        case 3:
                            System.out.println("Thank you");
                            break;

                        default:
                            System.out.println("Invalid choice ... Try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice ... Try again");
                logger.error(e.getMessage());
            }
        } while (3 != operations);
    }

}       