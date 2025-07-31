import java.awt.*;
import java.io.*;
import javax.swing.*;

public class TodoGUIApp extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private static final String FILE_NAME = "tasks.txt";

    public TodoGUIApp() {
        setTitle("📝 To-Do List App");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        taskInput = new JTextField();
        JButton addButton = new JButton("Add Task");

        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Task list
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton deleteButton = new JButton("❌ Delete Selected");
        JButton markDoneButton = new JButton("✅ Mark as Done");
        JButton clearCompletedButton = new JButton("🧹 Clear Completed");

        buttonPanel.add(markDoneButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearCompletedButton);

        // Add components
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load tasks from file
        loadTasksFromFile();

        // Add task
        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement("[ ] " + task);
                taskInput.setText("");
                saveTasksToFile();
            }
        });

        // Delete task
        deleteButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                listModel.remove(index);
                saveTasksToFile();
            }
        });

        // Mark task as done
        markDoneButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                String task = listModel.get(index);
                if (task.startsWith("[ ]")) {
                    listModel.set(index, task.replaceFirst("\\[ \\]", "[✓]"));
                    saveTasksToFile();
                }
            }
        });

        // Clear all completed
        clearCompletedButton.addActionListener(e -> {
            for (int i = listModel.getSize() - 1; i >= 0; i--) {
                if (listModel.get(i).startsWith("[✓]")) {
                    listModel.remove(i);
                }
            }
            saveTasksToFile();
        });

        setVisible(true);
    }

    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String task;
            while ((task = reader.readLine()) != null) {
                listModel.addElement(task);
            }
        } catch (IOException e) {
            System.out.println("No previous tasks found.");
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < listModel.getSize(); i++) {
                writer.write(listModel.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoGUIApp());
    }
}
