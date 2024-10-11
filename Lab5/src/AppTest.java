import java.util.Scanner;
public class AppTest {

    public static void main(String[] args) {

        WeChat weChat = new WeChat(5, 10);  // Maximum 5 receivers, 10 messages per receiver
        Scanner scanner = new Scanner(System.in);

        // Main loop for user interaction
        while (true) {
            System.out.println("\n--- WeChat Menu ---");
            System.out.println("1. Add Receiver");
            System.out.println("2. Send Message");
            System.out.println("3. Display Messages for a Receiver");
            System.out.println("4. Sort Messages by Timestamp");
            System.out.println("5. Delete a Message");
            System.out.println("6. Search Message");
            System.out.println("7. Mark a Message as Seen");
            System.out.println("8. Display All Messages");
            System.out.println("9. Display All Receivers");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new receiver name: ");
                    String receiverName = scanner.nextLine();
                    weChat.addReceiver(receiverName);
                    break;

                case 2:
                    System.out.print("Enter receiver name: ");
                    receiverName = scanner.nextLine();
                    System.out.print("Enter message content: ");
                    String messageContent = scanner.nextLine();
                    weChat.sendMessage(messageContent, receiverName);
                    break;

                case 3:
                    System.out.print("Enter receiver name to display messages: ");
                    receiverName = scanner.nextLine();
                    weChat.displayMessagesForReceiver(receiverName);
                    break;

                case 4:
                    System.out.print("Enter receiver name to sort messages: ");
                    receiverName = scanner.nextLine();
                    weChat.sortMessagesByTimestamp(receiverName);
                    break;

                case 5:
                    System.out.print("Enter receiver name: ");
                    receiverName = scanner.nextLine();
                    System.out.print("Enter message number to delete: ");
                    int messageNumber = scanner.nextInt();
                    weChat.deleteMessage (receiverName,messageNumber);
                    break;

                case 6:
                    System.out.print("Enter content or message ID to search: ");
                    String query = scanner.nextLine();
                    weChat.searchMessage(query);
                    break;

                case 7:
                    System.out.print("Enter receiver name: ");
                    receiverName = scanner.nextLine();
                    System.out.print("Enter message number to mark as seen: ");
                    messageNumber = scanner.nextInt();
                    weChat.markMessageAsSeen(receiverName, messageNumber);
                    break;
                case 8:
                    weChat.displayAllMessages();
                    break;

                case 9:
                    weChat.displayAllReceivers();
                    break;

                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
