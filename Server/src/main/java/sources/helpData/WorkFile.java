package sources.helpData;

import com.fasterxml.jackson.databind.ObjectMapper;
import sources.model.User;


import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

public class WorkFile {

    private final static String baseFile = "user.json";

    public static void toJSON(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = toJavaObject();
        users = users == null? new ArrayList<>():users;
        users.add(user);
        mapper.writeValue(new File(baseFile), users);
    }

    public static void toJSON(ArrayList<User> user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), user);
    }

    public static ArrayList<User> toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(baseFile);
        if (!file.exists())
            return null;
        return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class,User.class));
    }
}
