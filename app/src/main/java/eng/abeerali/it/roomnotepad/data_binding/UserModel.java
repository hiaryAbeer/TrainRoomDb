package eng.abeerali.it.roomnotepad.data_binding;

import java.util.Arrays;
import java.util.List;

public class UserModel {

    private String name, imageUrl;
    private List<String> hobbies;
    private int age;

    public static UserModel getValidStub() {
        UserModel userModel = new UserModel();
        userModel.setName("Bob Vance");
        userModel.setAge(35);
        userModel.setImageUrl("https://randomuser.me/api/portraits/men/28.jpg");
        userModel.setHobbies(Arrays.asList(new String[]{"Reading books","Travelling","Listening music"}));

        return userModel;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
