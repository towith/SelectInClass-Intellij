package my;

import com.intellij.ide.SelectInContext;
import com.intellij.ide.SelectInTarget;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang3.StringUtils;

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
    public void selectIn(SelectInContext selectInContext, boolean b) {
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
