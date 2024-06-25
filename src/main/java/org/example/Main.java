//package org.example;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Main {
//
//    private static final String BASE_URL = "https://feed.b-elect.com/fm1";
//    private static final ObjectMapper mapper = new ObjectMapper();
//    private static final HttpClient httpClient = HttpClients.createDefault();
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        System.out.println("Welcome to Task Manager System!");
//
//        while (true) {
//            printMenu();
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline character
//
//            switch (choice) {
//                case 1:
//                    registerUser();
//                    break;
//                case 2:
//                    getTasksForUser();
//                    break;
//                case 3:
//                    addTaskForUser();
//                    break;
//                case 4:
//                    markTaskAsDone();
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please choose again.");
//            }
//
//            System.out.println();
//            System.out.println("Do you want to continue? (y/n)");
//            String continueChoice = scanner.nextLine().trim();
//            if (!continueChoice.equalsIgnoreCase("y")) {
//                break;
//            }
//        }
//
//        System.out.println("Exiting Task Manager. Goodbye!");
//    }
//
//    private static void printMenu() {
//        System.out.println("Please choose an option:");
//        System.out.println("1 - Register to the system");
//        System.out.println("2 - Get a list of tasks saved for a specific user");
//        System.out.println("3 - Add a task to a specific user");
//        System.out.println("4 - Mark a task as done");
//    }
//
//    private static void registerUser() {
//        try {
//            System.out.println("Enter your ID:");
//            String id = scanner.nextLine().trim();
//
//            String url = BASE_URL + "/register";
//            HttpPost request = new HttpPost(url);
//            request.setHeader("Content-Type", "application/json");
//
//            StringEntity params = new StringEntity("{\"id\":\"" + id + "\"}");
//            request.setEntity(params);
//
//            HttpResponse response = httpClient.execute(request);
//            handleResponse(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void getTasksForUser() {
//        try {
//            System.out.println("Enter your ID:");
//            String id = scanner.nextLine().trim();
//
//            String url = BASE_URL + "/get-tasks?id=" + id;
//            HttpGet request = new HttpGet(url);
//
//            HttpResponse response = httpClient.execute(request);
//            handleResponse(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void addTaskForUser() {
//        try {
//            System.out.println("Enter your ID:");
//            String id = scanner.nextLine().trim();
//
//            System.out.println("Enter task description:");
//            String text = scanner.nextLine().trim();
//
//            String url = BASE_URL + "/add-task";
//            HttpPost request = new HttpPost(url);
//            request.setHeader("Content-Type", "application/json");
//
//            StringEntity params = new StringEntity("{\"id\":\"" + id + "\",\"text\":\"" + text + "\"}");
//            request.setEntity(params);
//
//            HttpResponse response = httpClient.execute(request);
//            handleResponse(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void markTaskAsDone() {
//        try {
//            System.out.println("Enter your ID:");
//            String id = scanner.nextLine().trim();
//
//            System.out.println("Enter task description:");
//            String text = scanner.nextLine().trim();
//
//            String url = BASE_URL + "/set-task-done";
//            HttpPost request = new HttpPost(url);
//            request.setHeader("Content-Type", "application/json");
//
//            StringEntity params = new StringEntity("{\"id\":\"" + id + "\",\"text\":\"" + text + "\"}");
//            request.setEntity(params);
//
//            HttpResponse response = httpClient.execute(request);
//            handleResponse(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void handleResponse(HttpResponse response) throws IOException {
//        int statusCode = response.getStatusLine().getStatusCode();
//        String responseBody = EntityUtils.toString(response.getEntity());
//
//        System.out.println("Response status code: " + statusCode);
//        System.out.println("Response body:");
//        System.out.println(responseBody);
//    }
//}
