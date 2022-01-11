package manager;

import data.Validate;
import model.Contact;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
    public static ArrayList<Contact> contactList;
    public static Scanner scanner = new Scanner(System.in);
    public static Validate validate = new Validate();
    public static final String PATH_NAME = "src/data/contacts.csv";

    public ContactManager() {
        if(new File(PATH_NAME).length() == 0) {
            this.contactList = new ArrayList<>();
        } else {
            this.contactList = readFile(PATH_NAME);
        }
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void addContact() {
        System.out.println("Nhập thông tin: ");
        System.out.println("_________________");
        String phoneNumber = inputPhoneNumber();
        System.out.println("Nhập tên nhóm: ");
        String group = scanner.nextLine();
        System.out.println("Nhập họ tên: ");
        String name = scanner.nextLine();
        System.out.println("Nhập giới tính:");
        System.out.println("1. Male");
        System.out.println("2. Female");
        String gender = scanner.nextLine();
        System.out.println("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.println("Nhập ngày sinh: ");
        String date = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(date);
        String email = inputEmail();

        for (Contact phone : contactList) {
            if (phone.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Số điện thoại bị trùng, mời kiểm tra lại !");
                System.out.println("--------------------");
                return;
            }
        }

        Contact contact = new Contact(phoneNumber, group, name, gender, address, dateOfBirth, email);
        contactList.add(contact);
        System.out.println("Thêm " + phoneNumber + " vào danh bạ thành công !");
        System.out.println("--------------------");
    }

    public void updateContact(String phoneNumber) {
        Contact editContact = null;
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                editContact = contact;
            }
        }
        if (editContact != null) {
            int index = contactList.indexOf(editContact);
            System.out.println("Nhập tên nhóm mới:");
            editContact.setGroup(scanner.nextLine());
            System.out.println("Nhập Họ tên mới:");
            editContact.setName(scanner.nextLine());
            System.out.println("Nhập giới tính mới:");

            int gender = Integer.parseInt(scanner.nextLine());
            editContact.setGender(scanner.nextLine());
            System.out.println("▹ Nhập địa chỉ mới:");
            editContact.setAddress(scanner.nextLine());
            System.out.println("Nhập ngày sinh mới:");
            String date = scanner.nextLine();
            LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
            editContact.setDateOfBirth(dateOfBirth);
            editContact.setEmail(inputEmail());
            contactList.set(index, editContact);
            System.out.println("Sửa " + phoneNumber + " thành công !");
            System.out.println("--------------------");
        } else {
            System.out.println("Không tìm được số điện thoại trên !");
            System.out.println("--------------------");
        }
    }

    public void deleteContact(String phoneNumber) {
        Contact deleteContact = null;
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                deleteContact = contact;
            }
        }
        if (deleteContact != null) {
            String confirm = scanner.next();
            contactList.remove(deleteContact);
            System.out.println("Xóa " + phoneNumber + " thành công !");
        } else {
            System.out.println("Không tìm thấy số điện thoại trên !");
            System.out.println("--------------------");
        }
    }

    public void searchContactByNameOrPhone(String keyword) {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : contactList) {
            if (validate.validatePhoneOrName(keyword, contact.getPhoneNumber()) || validate.validatePhoneOrName(keyword, contact.getName())) {
                contacts.add(contact);
            }
        }
        if (contacts.isEmpty()) {
            System.out.println("Không tìm thấy danh bạ với từ khóa trên !");
            System.out.println("--------------------");
        } else {
            System.out.println("Danh bạ cần tìm:");
            contacts.forEach(System.out::println);
            System.out.println("--------------------");
        }
    }

    public void displayAll() {
        for (Contact contact : contactList) {
            System.out.printf("| %-15s| %-10s| %-15s| %-10s| %-10s|\n", contact.getPhoneNumber(), contact.getGroup(), contact.getName(), contact.getGender(), contact.getAddress());
            System.out.println("-----------------------------------------------------------------------");
        }
    }

    public String inputEmail() {
        String email;
        while (true) {
            System.out.println("Nhập email: ");
            String inputEmail = scanner.nextLine();
            if (!validate.validateEmail(inputEmail)) {
                System.out.println("Email hợp lệ!!!");
            } else {
                email = inputEmail;
                break;
            }
        } return email;
    }

    public String inputPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.println("Nhập số điện thoại: ");
            String phone = scanner.nextLine();
            if (!validate.validatePhone(phone)) {
                System.out.println("Số điện thoại không hợp lệ!!!");
            } else {
                phoneNumber = phone;
                break;
            }
        } return phoneNumber;
    }

    public void writeFile(ArrayList<Contact> contactList, String path) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (Contact contact : contactList) {
                bufferedWriter.write(contact.getPhoneNumber() + "," + contact.getGroup() + "," + contact.getName() + "," + contact.getGender() + ","
                        + contact.getAddress() + "," + contact.getDateOfBirth() + "," + contact.getEmail() + "\n");
            }
            bufferedWriter.close();
            System.out.println("⛔ Write file successfully !");
            System.out.println("--------------------");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public ArrayList<Contact> readFile(String path) {
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(", ");
                contacts.add(new Contact(strings[0], strings[1], strings[2], strings[3], strings[4], LocalDate.parse(strings[5], DateTimeFormatter.ISO_LOCAL_DATE), strings[6]));

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return contacts;
    }
}
