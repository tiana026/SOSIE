package messages;

import utils.socket.IMessage;

public class ModelProxyReverseIdRequest implements IMessage {
    private final Class<?> target;
    private final Class<?> source;
    private final int id;

    public ModelProxyReverseIdRequest(Class<?> target, Class<?> source, int id) {
        this.target = target;
        this.source = source;
        this.id = id;
    }

    public Class<?> getTarget() {
        return target;
    }

    public Class<?> getSource() {
        return source;
    }

    public int getId() {
        return id;
    }
}
