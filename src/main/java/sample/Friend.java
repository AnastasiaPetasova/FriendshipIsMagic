package sample;

import com.vk.api.sdk.objects.base.Sex;

public class Friend implements Comparable<Friend> {
    int id;
    String name;
    String lastName;
    Sex sex;
    String books;
    String interests;

    @Override
    public int hashCode() {
        return id;
    }

    public Friend(int id, String name, Sex sex, String lastName, String books, String interests) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.lastName = lastName;
        this.books = books;
        this.interests = interests;
    }

    @Override
    public boolean equals(Object obj) {
        if(hashCode() == obj.hashCode())return true;
        return false;
    }

    @Override
    public int compareTo(Friend o) {
        return this.id - o.id;
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Sex getSex() {
        return sex;
    }

    //    public String getSex() {
//        switch (sex) {
//            case MALE: return "Мужской";
//            case FEMALE: return "Женский";
//        }
//
//        return "";
//    }

    public String getBooks() {
        return books;
    }

    public String getInterests() {
        return interests;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", books='" + books + '\'' +
                ", interests='" + interests + '\'' +
                '}';
    }
}
