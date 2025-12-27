package com.literise.data.mock;

import com.literise.models.User;
import com.literise.models.Student;
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

        // Initialize mock students linked to users
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

    /**
     * Mock login - checks if phone number and password match
     */
    public static User login(String phoneNumber, String password) {
        for (User user : mockUsers) {
            if (user.getPhoneNumber().equals(phoneNumber) &&
                user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Mock registration - adds new user to list
     */
    public static User register(String phoneNumber, String fullName, String childName, String password) {
        // Check if user already exists
        for (User user : mockUsers) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return null; // User already exists
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
            false // Needs OTP verification
        );
        mockUsers.add(newUser);

        // Create associated student
        int newStudentId = mockStudents.size() + 100;
        Student newStudent = new Student(
            newStudentId,
            childName,
            childName,
            "🦁", // Default avatar
            1,
            0,
            500,
            0,
            null, // No reading level yet
            0.0f,
            false, // Hasn't done placement
            false
        );
        mockStudents.add(newStudent);

        return newUser;
    }

    /**
     * Mock OTP verification
     */
    public static boolean verifyOTP(String phoneNumber, String otp) {
        // Mock: any 6-digit OTP is valid
        if (otp.length() == 6 && otp.matches("\\d+")) {
            // Mark user as verified
            for (User user : mockUsers) {
                if (user.getPhoneNumber().equals(phoneNumber)) {
                    user.setVerified(true);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get student by user ID (mock relationship)
     */
    public static Student getStudentByUserId(int userId) {
        // Simple mock: return first student if exists
        if (!mockStudents.isEmpty()) {
            return mockStudents.get(0);
        }
        return null;
    }

    /**
     * Generate mock OTP code
     */
    public static String generateOTP() {
        return "123456"; // Mock OTP for testing
    }
}
