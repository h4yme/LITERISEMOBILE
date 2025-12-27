package com.example.literisemobile.data.mock;

import com.example.literisemobile.models.User;
import com.example.literisemobile.models.Student;
import java.util.ArrayList;
import java.util.List;

public class MockAuthData {

    private static List<User> mockUsers = new ArrayList<>();
    private static List<Student> mockStudents = new ArrayList<>();

    static {
        // Initialize mock users
        mockUsers.add(new User(
                1,
                "628123456789",
                "Maria Santos",
                "password123",
                "parent",
                true
        ));

        mockUsers.add(new User(
                2,
                "628987654321",
                "Juan Dela Cruz",
                "password123",
                "parent",
                true
        ));

        // Initialize mock students
        mockStudents.add(new Student(
                456,
                "Emma",
                "Emma Garcia",
                "🦁",
                3,
                350,
                500,
                5,
                "DEVELOPING",
                0.45f,
                true,
                false
        ));
    }

    public static User login(String phoneNumber, String password) {
        for (User user : mockUsers) {
            if (user.getPhoneNumber().equals(phoneNumber) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static User register(String phoneNumber, String fullName, String childName, String password) {
        // Check if user already exists
        for (User user : mockUsers) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return null;
            }
        }

        // Create new user
        int newUserId = mockUsers.size() + 1;
        User newUser = new User(
                newUserId,
                phoneNumber,
                fullName,
                password,
                "parent",
                true // Auto-verified
        );
        mockUsers.add(newUser);

        // Create associated student
        int newStudentId = mockStudents.size() + 100;
        Student newStudent = new Student(
                newStudentId,
                childName,
                childName,
                "🦁",
                1,
                0,
                500,
                0,
                null,
                0.0f,
                false,
                false
        );
        mockStudents.add(newStudent);

        return newUser;
    }

    public static Student getStudentByUserId(int userId) {
        if (!mockStudents.isEmpty()) {
            return mockStudents.get(0);
        }
        return null;
    }
}
