package messages;

import utils.socket.IMessage;

@Deprecated
public class ProfessorClassRequest implements IMessage {
    public final int professorId;

    public ProfessorClassRequest(int professorId) {
        this.professorId = professorId;
    }

    public int getProfessorId() {
        return professorId;
    }
}
