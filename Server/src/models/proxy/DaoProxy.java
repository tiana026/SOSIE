package models.proxy;

import Models.ClassBatch;
import Models.Professor;
import Models.Student;
import Models.proxy.IProxy;
import messages.models.*;
import utils.socket.IMessage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DaoProxy implements IProxy {
    private final HashMap<java.lang.Class, IMessageCallback> messagesCallback = new HashMap<>();
    public interface IMessageCallback {
        IMessage run(IMessage e);
    }

    public DaoProxy() {
        messagesCallback.put(UserAdditionalInfoRequest.class, onUserAdditionalInfoRequest);
        messagesCallback.put(ClassBatchProfessorInChargeRequest.class, onClassBatchProfessorInChargeRequest);
        messagesCallback.put(ClassBatchStudentsRequest.class, onClassBatchStudentsRequest);
        messagesCallback.put(ProfessorClassBatchesRequest.class, onProfessorClassBatchesRequest);
    }

    @Override
    public IMessage load(IMessage msg) {
        if (messagesCallback.containsKey(msg.getClass()))
            return messagesCallback.get(msg.getClass()).run(msg);
        System.err.println("[DaoProxy] Message not found: " + msg.getClass().getName());
        return null;
    }

    public IMessageCallback onUserAdditionalInfoRequest = data -> {
        UserAdditionalInfoRequest msg = (UserAdditionalInfoRequest) data;
        try {
            return new UserAdditionalInfoResponse("test@gmail.com", Files.readAllBytes(Paths.get("IHM_Utilisateur\\src\\Nicolas Cage.jpg")));
        } catch (Exception e) {
            e.printStackTrace();
            //TODO handle image fail
            return null;
        }
    };

    public IMessageCallback onClassBatchProfessorInChargeRequest = data -> {
        ClassBatchProfessorInChargeRequest msg = (ClassBatchProfessorInChargeRequest) data;
        return new ClassBatchProfessorInChargeResponse(new Professor(0, 0, "Marc", "Landers"));
    };

    public IMessageCallback onClassBatchStudentsRequest = data -> {
        ClassBatchStudentsRequest msg = (ClassBatchStudentsRequest) data;
        return new ClassBatchStudentsResponse(new ArrayList<>(Arrays.asList(new Student(1, 0, "toto", "titi"), new Student(2, 1, "aaaa", "bbbb"))));
    };

    public IMessageCallback onProfessorClassBatchesRequest = data -> {
        ProfessorClassBatchesRequest msg = (ProfessorClassBatchesRequest) data;
        return new ProfessorClassBatchesResponse(new ArrayList<>(Arrays.asList(new ClassBatch(0, 0, 0, 0), new ClassBatch(1, 1, 1, 0))));
    };
}