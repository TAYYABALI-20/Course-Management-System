package cms;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import loaders.Loading;
import javafx.scene.control.Button;

import javax.swing.*;

import authentication.AdminLoginForm;
import authentication.StudentLoginForm;

import java.awt.*;
import java.io.File;

public class CourseManagementSystem {

    private static JFrame frame;
    private static JPanel loadingPanel;
    private static Loading loadingAnimation;
    private static MediaPlayer mediaPlayer;

    public static void start() {
        frame = new JFrame("Course Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(400, 300));
        frame.setLocationRelativeTo(null);

        loadingPanel = new JPanel(new GridBagLayout());
        loadingPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        loadingAnimation = new Loading();
        loadingAnimation.setPreferredSize(new Dimension(100, 100));

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setOpaque(false);

        GridBagConstraints containerGbc = new GridBagConstraints();
        containerGbc.gridx = 0;
        containerGbc.gridy = 0;
        containerGbc.anchor = GridBagConstraints.CENTER;
        containerPanel.add(loadingAnimation, containerGbc);

        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        containerGbc.gridy = 1;
        containerGbc.insets = new Insets(10, 0, 0, 0);
        containerPanel.add(loadingLabel, containerGbc);

        loadingPanel.add(containerPanel, gbc);

        frame.add(loadingPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        final JFXPanel fxPanel = new JFXPanel();
        Platform.runLater(() -> {
            initFX(fxPanel);
        });
    }

    private static void initFX(JFXPanel fxPanel) {
        String videoPath = "C:/Users/TAYYAB/OneDrive/Desktop/ITSE Project - ( Course Management System )/assets/bg-video.mp4";
        File videoFile = new File(videoPath);

        if (!videoFile.exists()) {
            System.err.println("Video file not found at: " + videoPath);
            return;
        }

        Media media = new Media(videoFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnReady(() -> {
            SwingUtilities.invokeLater(() -> {
                frame.remove(loadingPanel);
                frame.add(fxPanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();

                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
            });
        });

        MediaView mediaView = new MediaView(mediaPlayer);

        StackPane videoRoot = new StackPane();
        videoRoot.getChildren().add(mediaView);

        mediaView.fitWidthProperty().bind(videoRoot.widthProperty());
        mediaView.fitHeightProperty().bind(videoRoot.heightProperty());
        mediaView.setPreserveRatio(false);

        StackPane buttonStack = new StackPane();
        buttonStack.setAlignment(Pos.CENTER);

        HBox buttonPanel = new HBox(20);
        buttonPanel.setAlignment(Pos.CENTER);

        Button adminButton = createStyledJavaFXButton("Admin", javafx.scene.paint.Color.rgb(240, 240, 240, 0.8));
        Button studentButton = createStyledJavaFXButton("Student", javafx.scene.paint.Color.rgb(240, 240, 240, 0.8));

        adminButton.setOnAction(e -> {
            logout();
            frame.dispose();
            AdminLoginForm.showLoginForm(frame);
        });

        studentButton.setOnAction(e -> {
            logout();
            frame.dispose();
            StudentLoginForm.showLoginForm(frame);
        });

        buttonPanel.getChildren().addAll(adminButton, studentButton);

        buttonStack.getChildren().add(buttonPanel);

        StackPane root = new StackPane();
        root.getChildren().addAll(videoRoot, buttonStack);

        Scene scene = new Scene(root);
        fxPanel.setScene(scene);
    }

    private static Button createStyledJavaFXButton(String text, javafx.scene.paint.Color borderColor) {
        Button button = new Button(text);
        button.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 30));
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: " + toHex(borderColor) + "; " +
                "-fx-border-width: 2px; " +
                "-fx-padding: 10px 20px;");

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); " +
                "-fx-border-color: " + toHex(borderColor) + "; " +
                "-fx-border-width: 2px; " +
                "-fx-padding: 10px 20px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: " + toHex(borderColor) + "; " +
                "-fx-border-width: 2px; " +
                "-fx-padding: 10px 20px;"));

        return button;
    }

    private static String toHex(javafx.scene.paint.Color color) {
        return String.format("#%02x%02x%02x", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private static void logout() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        frame.dispose();
        start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CourseManagementSystem::start);
    }
    
}