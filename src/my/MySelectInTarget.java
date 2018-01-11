package my;

import com.intellij.ide.SelectInContext;
import com.intellij.ide.SelectInTarget;
import com.intellij.ide.actions.CopyReferenceAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang3.StringUtils;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.ui.playback.commands.ActionCommand;

import java.io.IOException;

/**
 * Created by yinghao_niu on 2016/12/13 for freecmdSelectIn.
 */
public class MySelectInTarget implements SelectInTarget {
    @Override
    public boolean canSelect(SelectInContext selectInContext) {
        return true;
    }

    @Override
    public void selectIn(final SelectInContext selectInContext, boolean b) {
        String path = selectInContext.getVirtualFile().getPath();
        ProcessBuilder freecmd = new ProcessBuilder(MyFileManager.toolPath, path, StringUtils.isEmpty(MyFileManager.prefixPath)?""
                :MyFileManager.prefixPath);
        try {
            freecmd.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MySelectInTarget-FreeCommand";
    }
}
