public class EventListener extends Thread {

    private String messageToListenFor;
    private String messageToReplyWith;
    private Tracker eventTracker;

    public EventListener(String message, String reply) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = EventTracker.getInstance();
    }

    public EventListener(String message, String reply, Tracker tracker) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = tracker;
    }

    public void run() {
        while (!readyToQuit()) {
            if(shouldReply()) {
                eventTracker.handle(messageToListenFor, () -> {
                    System.out.println(messageToReplyWith);
                });

            }
        }
    }

    public Boolean readyToQuit() {
        return eventTracker.has("quit");
    }

    public Boolean shouldReply() {
      return eventTracker.has(messageToReplyWith);
    }


    public void reply() {
        eventTracker.handle(messageToListenFor, () -> {
            System.out.println(messageToReplyWith);
        });

    }
}