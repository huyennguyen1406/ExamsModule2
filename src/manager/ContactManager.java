package manager;

import data.Validate;
import model.Contact;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
    public static final String PATH_NAME = "src/contacts.csv";
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Contact> contactList;
    private final Validate validate = new Validate();

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public ContactManager() {
        this.contactList = new ArrayList<>();
    }

    public void addContact() {
        String phone = enterPhoneNumber();
        System.out.print("Nhập nhóm danh bạ : ");
        String group = scanner.nextLine();
        System.out.print("Nhập Họ tên : ");
        String name = scanner.nextLine();
        System.out.print("Nhập giới tính : ");
        String gender = scanner.nextLine();
        System.out.print("Nhập địa chỉ : ");
        String address = scanner.nextLine();
        System.out.print("Nhập ngày sinh(dd-mm-yyyy):");
        String date = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        String email = enterEmail();
        contactList.add(new Contact(phone, group, name, gender, address, dateOfBirth, email));
        writeFile(contactList, PATH_NAME);
    }

    private String enterEmail() {
        String email;
        while (true) {
            System.out.print("Nhập Email : ");
            String inputEmail = scanner.nextLine();
            if (!validate.validateEmail(inputEmail)) {
                System.out.println("Email không hợp lệ !!!");
            } else {
                email = inputEmail;
                break;
            }
        }
        return email;
    }

    public String enterPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.print("Nhập số điện thoại : ");
            String phone = scanner.nextLine();
            if (!validate.validatePhone(phone)) {
                System.out.println("Số điện thoại không hợp lệ !!!");
            } else {
                phoneNumber = phone;
                break;
            }
        }
        return phoneNumber;
    }

    public void displayContacts() {
        for (Contact c : contactList) {
            System.out.println(c);
        }
    }

    public void editContact(String phone) {
        Contact contacts = null;
        for (Contact c : contactList) {
            if (c.getPhoneNumber().equals(phone)) {
                contacts = c;
            }
        }
        if (contacts != null) {
            int index = contactList.indexOf(contacts);
            System.out.print("Nhập số điện thoại : ");
            String phone1 = scanner.nextLine();
            contacts.setPhoneNumber(phone1);
            System.out.print("Nhập nhóm danh bạ: ");
            String group = scanner.nextLine();
            contacts.setGroup(group);
            System.out.print("Nhập Họ tên : ");
            String name = scanner.nextLine();
            contacts.setName(name);
            System.out.print("Nhập giới tính: ");
            String gender = scanner.nextLine();
            contacts.setGender(gender);
            System.out.print("Nhập địa chỉ: ");
            String address = scanner.nextLine();
            contacts.setAddress(address);
            System.out.print("Nhập ngày sinh (dd-mm-yyyy) : ");
            String date = scanner.nextLine();
            LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
            contacts.setBirthday(dateOfBirth);
            System.out.print("Nhập Email: ");
            String email = scanner.nextLine();
            contacts.setEmail(email);
            contactList.set(index, contacts);
            System.out.println("Sửa thành công !!!");
            writeFile(contactList, PATH_NAME);
        }
    }

    public void deleteContact(String phone) {
        Contact contacts = null;
        for (Contact c : contactList) {
            if (c.getPhoneNumber().equals(phone)) {
                contacts = c;
            }
        }
        contactList.remove(contacts);
        System.out.println("Xóa thành công !!!");
    }

    public ArrayList<Contact> searchContact(String name) {
        ArrayList<Contact> contacts1 = new ArrayList<>();
        for (Contact c : contactList) {
            if (c.getName().equals(name) || c.getPhoneNumber().equals(name)) {
                contacts1.add(c);
            }
        }
        return contacts1;
    }

    public void writeFile(ArrayList<Contact> contacts, String path) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (Contact contact : contacts) {
                bufferedWriter.write(contact.getPhoneNumber() + "," +
                        contact.getGroup() + "," + contact.getName() + "," + contact.getGender() + "," +
                        contact.getAddress() + "," + contact.getBirthday() + "," + contact.getEmail() + "\n");
            }
            bufferedWriter.close();
            System.out.println("Ghi File Thành Công !!!");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Contact> readFile(String path) {
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                contacts.add(new Contact(strings[0], strings[1], strings[2], strings[3], strings[4], LocalDate.parse(strings[5], DateTimeFormatter.ISO_LOCAL_DATE), strings[6]));
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return contacts;
    }
}
