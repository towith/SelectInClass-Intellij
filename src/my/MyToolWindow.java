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
    WebEngine eng;
    WebView webView;

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
        JFXPanel1.show();
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final String text = textField1.getText();
                        webView = new WebView();
                        JFXPanel1.setScene(new Scene(webView));
                        eng = webView.getEngine();
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
