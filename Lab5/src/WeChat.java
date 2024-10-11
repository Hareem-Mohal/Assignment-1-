
public class WeChat {
    private String[][] receivers;
    private Message[][] messages;
    private int[] messageCounts;
    private int receiverCount = 0;
    private static int receiverIdCounter = 1;

    // Constructor
    public WeChat(int maxReceivers, int maxMessagesPerReceiver) {
        receivers = new String[maxReceivers][2];
        messages = new Message[maxReceivers][maxMessagesPerReceiver];
        messageCounts = new int[maxReceivers];

        // Instance initialization block to add default receivers and messages
        {
            addReceiver("Ali");
            addReceiver("Beerray");
            addReceiver("Hareem");

            // Sending default messages for the receivers
            sendMessage("Hello Ali", "Ali");
            sendMessage("Welcome", "Ali");
            sendMessage("Hi, how are you?", "Beerray");
            sendMessage("Good morning, Hareem!", "Hareem");
        }
    }

    // Method to add a new receiver
    public void addReceiver(String receiverName) {
        if (receiverCount < receivers.length) {
            String receiverId = generateReceiverId();
            receivers[receiverCount][0] = receiverId;
            receivers[receiverCount][1] = receiverName;
            System.out.println("Receiver added: " + receiverName + " with ID: " + receiverId);
            receiverCount++;
        } else {
            System.out.println("Cannot add more receivers.");
        }
    }

    private String generateReceiverId() {
        return String.format("R%06d", receiverIdCounter++);
    }

    private int findReceiverIndexByName(String receiverName) {
        for (int i = 0; i < receiverCount; i++) {
            if (receivers[i][1] != null && receivers[i][1].equalsIgnoreCase(receiverName)) {
                return i;
            }
        }
        return -1;
    }

    public void sendMessage(String messageContent, String receiverName) {
        int receiverIndex = findReceiverIndexByName(receiverName);
        if (receiverIndex == -1) {
            System.out.println("Receiver not found.");
            return;
        }

        String receiverId = receivers[receiverIndex][0];
        if (messageCounts[receiverIndex] < messages[receiverIndex].length) {
            Message newMessage = new Message(messageContent, receiverName, receiverId);
            messages[receiverIndex][messageCounts[receiverIndex]++] = newMessage;
            System.out.println("Message sent to " + receiverName + " (ID: " + receiverId + ")");
        } else {
            System.out.println("Message limit reached for " + receiverName);
        }
    }

    public void displayMessagesForReceiver(String receiverName) {
        int receiverIndex = findReceiverIndexByName(receiverName);
        if (receiverIndex == -1) {
            System.out.println("Receiver not found.");
            return;
        }

        System.out.println("Messages for " + receiverName + ":");
        for (int i = 0; i < messageCounts[receiverIndex]; i++) {
            System.out.println((i + 1) + ". " + messages[receiverIndex][i]);
        }
    }

    public void sortMessagesByTimestamp(String receiverName) {
        int receiverIndex = findReceiverIndexByName(receiverName);
        if (receiverIndex == -1) {
            System.out.println("Receiver not found.");
            return;
        }

        for (int i = 0; i < messageCounts[receiverIndex] - 1; i++) {
            for (int j = 0; j < messageCounts[receiverIndex] - i - 1; j++) {
                if (messages[receiverIndex][j].getTimestamp().isAfter(messages[receiverIndex][j + 1].getTimestamp())) {
                    Message temp = messages[receiverIndex][j];
                    messages[receiverIndex][j] = messages[receiverIndex][j + 1];
                    messages[receiverIndex][j + 1] = temp;
                }
            }
        }

        System.out.println("Messages for " + receiverName + " have been sorted by timestamp.");
    }

    public void searchMessage(String query) {
        boolean found = false;
        System.out.println("Searching for messages containing \"" + query + "\" or with message ID \"" + query + "\":");

        for (int i = 0; i < receiverCount; i++) {
            for (int j = 0; j < messageCounts[i]; j++) {
                Message msg = messages[i][j];
                if (msg.getContent().contains(query) || msg.getMessageId().equalsIgnoreCase(query)) {
                    System.out.println(msg);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No messages found matching the query.");
        }
    }

    public void markMessageAsSeen(String receiverName, int messageNumber) {
        int receiverIndex = findReceiverIndexByName(receiverName);
        if (receiverIndex == -1) {
            System.out.println("Receiver not found.");
            return;
        }

        if (messageNumber > 0 && messageNumber <= messageCounts[receiverIndex]) {
            Message message = messages[receiverIndex][messageNumber - 1];
            message.markAsSeen();
            System.out.println("Message " + messageNumber + " for " + receiverName + " has been marked as seen.");
        } else {
            System.out.println("Invalid message number.");
        }
    }


    public void deleteMessage (String receiverName,int messageNumber){
            int receiverIndex = findReceiverIndexByName(receiverName);
            if (receiverIndex == -1) {
                System.out.println("Receiver not found.");
                return;
            }

            if (messageNumber > 0 && messageNumber <= messageCounts[receiverIndex]) {
                for (int i = messageNumber - 1; i < messageCounts[receiverIndex] - 1; i++) {
                    messages[receiverIndex][i] = messages[receiverIndex][i + 1];
                }
                messages[receiverIndex][--messageCounts[receiverIndex]] = null;
                System.out.println("Message " + messageNumber + " deleted for " + receiverName);
            } else {
                System.out.println("Invalid message number.");
            }
        }

        public void displayAllReceivers() {
            System.out.println("Registered Receivers:");
            for (int i = 0; i < receiverCount; i++) {
                System.out.println("- " + receivers[i][1] + " (ID: " + receivers[i][0] + ")");
            }
        }

        public void displayAllMessages(){
            System.out.println("Displaying all messages for all receivers:");
            for (int i = 0; i < receiverCount; i++) {
                System.out.println("\nReceiver: " + receivers[i][1] + " (ID: " + receivers[i][0] + ")");
                for (int j = 0; j < messageCounts[i]; j++) {
                    System.out.println((j + 1) + ". " + messages[i][j]);
                }
            }
        }

    }
