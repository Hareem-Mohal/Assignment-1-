import java.util.Scanner;
import java.time.LocalDateTime;
public class Message {
    private static int idCounter = 1;
    private String messageId;
    private String content;
    private boolean isSeen;
    private String receiverName;
    private String receiverId;
    private LocalDateTime timestamp;

    public Message(String content, String receiverName, String receiverId) {
        this.content = content;
        this.receiverName = receiverName;
        this.receiverId = receiverId;
        this.isSeen = false;
        this.timestamp = LocalDateTime.now();
        this.messageId = String.format("%03d", idCounter++);
    }

    public String getContent() {
        return content;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMessageId() {
        return messageId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void markAsSeen() {
        this.isSeen = true;
    }

    @Override
    public String toString() {
        String status = isSeen ? "Seen" : "Delivered";
        return "Message ID: " + messageId + " \n| Content: " + content + " \n| Status: " + status +
                " \n| Receiver: " + receiverName + " \n|ID: " + receiverId + " \n| Time: " + timestamp;
    }
}
