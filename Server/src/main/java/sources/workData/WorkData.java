package sources.workData;


import sources.helpData.WorkFile;
import sources.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HP ProBook on 23.03.2017.
 */
public class WorkData {

    ArrayList<User> users;

    public WorkData() {
    }


    public ArrayList<User> users() {
        try {
            users = WorkFile.toJavaObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<User> titleFind(String title) {
        try {
            users = WorkFile.toJavaObject();
            if (users == null) return null;
            for (User user : users) {
                if (!user.getTitle().equals(title)) {
                    users.remove(user);
                }
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public User idFind(long id) {
        try {
            users = WorkFile.toJavaObject();
            if (users == null) return null;
            for (User user : users) {
                if (user.getId() == id) {
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public ArrayList<User> deposotorFind(String depositor) {
        try {
            users = WorkFile.toJavaObject();
            if (users == null) return null;
            for (User user : users) {
                if (user.getDepositor() != depositor) {
                    users.remove(user);
                }
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public ArrayList<User> typeFind(String type) {
        try {
            users = WorkFile.toJavaObject();
            if (users == null) return null;
            for (User user : users) {
                if (user.getType() != type) {
                    users.remove(user);
                }
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



    public String add(User user) {
        if (user.getSum() <= 0)
            return ("Amount on deposit < 0");

        if (user.getProcent() <= 0)
            return ("Profitability < 0");

        if (user.getDate().getTime() - new Date().getTime() <= 0)
            return ("Profitability < 0");
        if (users != null) {
            for (User num : users) {
                if (user.getId() == num.getId())
                    return ("This id account exist");
            }
        }
        try {
            WorkFile.toJSON(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK";
    }



    public String delete(long id) {
        try {
            boolean check = false;
            users = WorkFile.toJavaObject();
            for (User user : users) {
                if (user.getId() == id) {
                    users.remove(user);
                    check = true;
                }
            }
            if (check) {
                WorkFile.toJSON(users);
                return "User delete";
            } else {
                return "User not found";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "User not found";

    }

    public int sum() {
        int sum = 0;
        try {
            users = WorkFile.toJavaObject();
            if (users != null) {
                for (User user : users) {
                    sum += user.getSum();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;

    }

    public int count() {
        int i = 0;
        try {
            users = WorkFile.toJavaObject();
            if (users != null) {
                return users.size();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }
}


