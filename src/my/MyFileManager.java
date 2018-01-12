package my;

import com.intellij.ide.SelectInManager;
import com.intellij.ide.SelectInTarget;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.impl.ProjectManagerImpl;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by yinghao_niu on 2016/12/12 for freecmdSelectIn.
 */
public class MyFileManager implements
        ApplicationComponent,
        ProjectComponent, Configurable {
    static String toolPath;
    static String prefixPath;
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private final Storage storage = ServiceManager.getService(Storage.class);


    public MyFileManager() {

    }

    @Override
    public void initComponent() {
        toolPath = storage.getFmPath();
        prefixPath = storage.getPrefixPath();
        createComponent();
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        textField1.setText(toolPath == null ? "FreeCommander.exe" : toolPath);
        textField2.setText(prefixPath);
        return panel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        toolPath = textField1.getText();
        storage.setFmPath(toolPath);
        prefixPath = textField2.getText();
        storage.setPrefixPath(prefixPath);
    }

    @Override
    public void reset() {
        //todo_need
    }

    @Override
    public void projectOpened() {
        Project[] openProjects = ProjectManagerImpl.getInstanceEx().getOpenProjects();
        for (Project openProject : openProjects) {
            if (openProject.isOpen()) {
                SelectInManager selectInManager = SelectInManager.getInstance(openProject);
                MySelectInTarget target = new MySelectInTarget();
                selectInManager.addTarget(target);
                if (existTarget(selectInManager, target)) {
                } else {
                    selectInManager.addTarget(target);
                }
                CompileOutputSelectInTarget compileOutputSelectInTarget = new CompileOutputSelectInTarget();
                if (existTarget(selectInManager, compileOutputSelectInTarget)) {
                } else {
                    selectInManager.addTarget(compileOutputSelectInTarget);
                }
            }
        }
    }

    private boolean existTarget(SelectInManager selectInManager, SelectInTarget target) {
        SelectInTarget[] targets = selectInManager.getTargets();
        for (SelectInTarget selectInTarget : targets) {
            if (selectInTarget.toString().equals(target.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void projectClosed() {
        //todo_need
    }


    @Override
    @NotNull
    public String getComponentName() {
        return "My FileManager Component";
    }


    @Nls
    @Override
    public String getDisplayName() {
        return "My FileManager";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "My FileManager HelpTopic";
    }

}
