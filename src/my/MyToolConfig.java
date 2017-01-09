package my;

import com.intellij.ide.SelectInManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.project.impl.ProjectManagerImpl;
import com.intellij.openapi.roots.ProjectRootManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by yinghao_niu on 2016/12/12 for freecmdSelectIn.
 */
public class MyToolConfig implements
        ApplicationComponent,
        ProjectComponent, Configurable {
    static String toolPath;
    ToolSetting settingPanel;
    private final Storage storage = ServiceManager.getService(Storage.class);

    public MyToolConfig() {
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
        this.settingPanel = new ToolSetting();
        settingPanel.add(new JLabel("path:"));
        JTextField textField1 = new JTextField();
        textField1.setText(storage.getPath());
        this.settingPanel.setTextField1(textField1);
        settingPanel.add(this.settingPanel.getTextField1());
        toolPath = textField1.getText();
        return settingPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        toolPath = this.settingPanel.getTextField1().getText();
        storage.setPath(toolPath);
    }

    @Override
    public void reset() {
        //todo_need
    }

    @Override
    /**
     *    compile output dir
     *   CompilerModuleExtension.getInstance(ModuleUtilCore.findModuleForFile(anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE),
     anActionEvent.getProject()))
     .getCompilerOutputPath()
     */
    public void projectOpened() {
        Project[] openProjects = ProjectManagerImpl.getInstanceEx().getOpenProjects();
        for (Project openProject : openProjects) {
            if (openProject.isOpen()) {
                SelectInManager selectInManager = SelectInManager.getInstance(openProject);
                MySelectInTarget target = new MySelectInTarget();
                selectInManager.addTarget(target);
            }
        }
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
        MyToolConfig.toolPath = toolPath;
    }
}
