package my;

import com.intellij.ide.SelectInContext;
import com.intellij.ide.SelectInTarget;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.CompilerModuleExtension;

/**
 * Created by yinghao_niu on 2016/12/13 for freecmdSelectIn.
 */
public class CompileOutputSelectInTarget implements SelectInTarget {
    @Override
    public boolean canSelect(SelectInContext selectInContext) {
        return true;
    }

    @Override
    /**
     *    compile output dir
     *   CompilerModuleExtension.getInstance(ModuleUtilCore.findModuleForFile(anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE),
     anActionEvent.getProject()))
     .getCompilerOutputPath()
     */
    public void selectIn(SelectInContext selectInContext, boolean b) {
        CompilerModuleExtension module = CompilerModuleExtension.getInstance(ModuleUtilCore.findModuleForFile(
                selectInContext.getVirtualFile(),
                selectInContext.getProject()));
        String path = module.getCompilerOutputUrl();
        path = path.replaceFirst("file://", "");
        path = path.replaceAll("/", "\\");
    }
}
