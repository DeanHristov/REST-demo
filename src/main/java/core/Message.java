package core;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
    private String message;

    public Message (String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
