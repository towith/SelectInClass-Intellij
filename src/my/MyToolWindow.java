package my;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by niuyinghao on 2017/1/12 for freecmdSelectIn.
 */
public class MyToolWindow implements ToolWindowFactory {
    private JPanel myToolWindowContent;
    private JFXPanel JFXPanel1;
    private JTextField textField1;
    ToolWindow toolWindow;
    static WebEngine eng;
    static Scene scene;
    static Group root;
    static VBox box;
    static WebView webView;

    public MyToolWindow() {

    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        this.toolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
        drawContent();
    }

    private void drawContent() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                webView = new WebView();
                eng = webView.getEngine();
                root = new Group();
                root.getChildren().add(webView);
                scene = new Scene(root);
                JFXPanel1.setScene(scene);
                JFXPanel1.show();
//                Stage stage = new Stage(StageStyle.UTILITY);
//                stage.setScene(scene);
//                stage.show();

            }
        });

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    JFXPanel1.revalidate();
                    JFXPanel1.show();
                    JFXPanel1.revalidate();

                    final String text = textField1.getText();
                    if (text != null) {
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                eng.load(text.trim());
                            }
                        };
                        Platform.runLater(runnable);
                    }
                }
            }
        });


    }

}
