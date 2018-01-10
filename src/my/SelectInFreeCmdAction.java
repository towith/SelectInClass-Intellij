package my;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;

import java.io.IOException;

/**
 * Created by yinghao_niu on 2016/12/11 for freecmdSelectIn.
 */
public class SelectInFreeCmdAction extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(MyFileManager.toolPath != null && !MyFileManager.toolPath.equals(""));
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        String path = anActionEvent.getDataContext().getData(CommonDataKeys.VIRTUAL_FILE).getPath();
        ProcessBuilder freecmd = new ProcessBuilder(MyFileManager.toolPath, path);
        try {
            freecmd.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
