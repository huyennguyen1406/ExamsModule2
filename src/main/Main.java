package main;

import manager.ContactManager;
import model.Contact;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();
            try {
                do {
                    System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ----");
                    System.out.println("Chọn chức năng:");
                    System.out.println("1. Xem danh sách");
                    System.out.println("2. Thêm mới danh bạ");
                    System.out.println("3. Cập nhật danh bạ");
                    System.out.println("4. Xóa ");
                    System.out.println("5. Tìm kiếm theo tên");
                    System.out.println("6. Đọc từ file");
                    System.out.println("7. Ghi vào file");
                    System.out.println("8. Thoát");
                    System.out.println("Chọn chức năng:");
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 8) {
                        System.out.println();
                        System.out.println("Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                        System.out.println("--------------------");
                    }
                    switch (choice) {
                        case 1:
                            contactManager.displayAll();
                            break;
                        case 2:
                            contactManager.addContact();
                            break;
                        case 3:
                            System.out.println("Nhập số điện thoại cần sửa:");
                            String phoneEdit = scanner.nextLine();
                            if (phoneEdit.equals("")) {

                            } else {
                                contactManager.updateContact(phoneEdit);
                            }
                            break;
                        case 4:
                            System.out.println("Nhập số điện thoại cần sửa:");
                            String phoneDelete = scanner.nextLine();
                            contactManager.deleteContact(phoneDelete);
                            break;

                        case 5:
                            System.out.println("Nhập tên:");
                            contactManager.findByName();
                            break;
                        case 6:
                            ArrayList<Contact> contactArrayList = contactManager.readFile(ContactManager.PATH_NAME);
                            contactArrayList.forEach(System.out::println);
                            System.out.println("Đọc file thành công");
                            break;
                        case 7:
                            contactManager.writeFile(contactManager.getContactList(), ContactManager.PATH_NAME);
                            break;
                        case 8:
                            System.exit(8);
                    }
                } while (true);
            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println();
                System.out.println("Thử lại!!!");
            }
    }
}
