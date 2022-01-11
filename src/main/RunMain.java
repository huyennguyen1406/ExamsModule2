package main;

import manager.ContactManager;
import model.Contact;

import java.util.ArrayList;
import java.util.Scanner;

public class RunMain {
    Scanner scanner = new Scanner(System.in);
    ContactManager contactManager = new ContactManager();
    int choice;

    public RunMain() {
    }

    public void menuOfSystem() {
        do {
            System.out.println();
            System.out.println("___CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ___");
            System.out.println("|1. Xem danh sách                |");
            System.out.println("|2. Thêm mới                     |");
            System.out.println("|3. Cập nhật                     |");
            System.out.println("|4. Xóa                          |");
            System.out.println("|5. Tìm kiếm                     |");
            System.out.println("|6. Ghi vào File                 |");
            System.out.println("|7. Đọc từ File                  |");
            System.out.println("|8. Thoát                        |");
            System.out.println("----------------------------------");
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    contactManager.displayContacts();
                    break;
                case 2:
                    contactManager.addContact();
                    break;
                case 3:
                    System.out.print("Nhập số điện thoại");
                    scanner.nextLine();
                    String s = scanner.nextLine();
                    contactManager.editContact(s);
                    System.out.println(s);
                    break;
                case 4:
                    System.out.print("Nhập số điện thoại : ");
                    scanner.nextLine();
                    String s1 = scanner.nextLine();
                    contactManager.deleteContact(s1);
                    System.out.println(s1);
                    break;
                case 5:
                    System.out.print("Nhập tên tìm kiếm: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    ArrayList<Contact> s2 = contactManager.searchContact(name);
                    System.out.println(s2);
                    break;
                case 6:
                    contactManager.writeFile(contactManager.getContactList(), contactManager.PATH_NAME);
                    break;
                case 7:
                    ArrayList<Contact> contacts = contactManager.readFile(contactManager.PATH_NAME);
                    contacts.forEach(System.out::println);
                    break;
                case 8:
                    System.exit(0);
            }
        } while (choice != 0);
    }
}
