package my;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
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
    private WebEngine eng;

    private WebView webView;
    private Scene scene;


    public MyToolWindow() {
    }


    @Override
    public void init(ToolWindow window) {
    }

    private void createUIComponents() {
        JFXPanel1 = new JFXPanel();
        Platform.setImplicitExit(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                webView = new WebView();
                scene = new Scene(webView);
                eng = webView.getEngine();
                JFXPanel1.setScene(scene);

            }
        });
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindowContent, "", true);
        toolWindow.getContentManager().addContent(content);
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final String text = textField1.getText();
                    if (text != null) {
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                String url = text.trim();
                                if (url.startsWith("http://")) {
                                } else {
                                    url = "http://" + url;
                                }
                                eng.load(url);
                            }
                        };
                        Platform.runLater(runnable);
                    }
                }
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFXPanel1.setVisible(true);
            }
        });
    }


}
