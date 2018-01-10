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
public class MyFileManager extends JPanel implements
        ApplicationComponent,
        ProjectComponent, Configurable {
    static String toolPath;
    static String prefixPath;
    private JTextField textField1;
    private JTextField textField2;
    private final Storage storage = ServiceManager.getService(Storage.class);

    public JTextField getTextField2() {
        return textField2;
    }

    public MyFileManager() {

    }

    @Override
    public void initComponent() {
        createComponent();
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        textField1.setText(storage.getFmPath() == null ? "FreeCommander.exe" : storage.getFmPath());
        textField2.setText(storage.getPrefixPath());

        toolPath = textField1.getText();
        prefixPath = textField2.getText();
        return this;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        toolPath = textField1.getText();
        storage.setFmPath(toolPath);
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
                }else{
                    selectInManager.addTarget(target);
                }
                CompileOutputSelectInTarget compileOutputSelectInTarget = new CompileOutputSelectInTarget();
                if (existTarget(selectInManager, compileOutputSelectInTarget)) {
                }else{
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

    public static String getToolPath() {
        return toolPath;
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "XXX";
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "FFF";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "YYY";
    }

    public static void setToolPath(String toolPath) {
        MyFileManager.toolPath = toolPath;
    }
}
