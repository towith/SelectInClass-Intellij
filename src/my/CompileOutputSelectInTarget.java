package my;

import com.intellij.ide.FileSelectInContext;
import com.intellij.ide.SelectInContext;
import com.intellij.ide.SelectInTarget;
import com.intellij.ide.impl.ProjectPaneSelectInTarget;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;

import java.io.File;

/**
 * Created by yinghao_niu on 2016/12/13 for freecmdSelectIn.
 *  根据java文件选择相应的class
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
        VirtualFile virtualFile = selectInContext.getVirtualFile();
        CompilerModuleExtension module = CompilerModuleExtension.getInstance(ModuleUtilCore.findModuleForFile(
                virtualFile,
                selectInContext.getProject()));
        String path = module.getCompilerOutputUrl();
        path = path.replaceFirst("file://", "");
        path = path.replaceAll("/", "\\\\");
        String qualifiedName = PsiClass.class.cast(selectInContext.getSelectorInFile()).getQualifiedName();
        path = path + File.separator + qualifiedName.replaceAll("\\.", "\\\\") + ".class";
        ProjectPaneSelectInTarget projectPaneSelectInTarget = new ProjectPaneSelectInTarget(selectInContext.getProject());
        FileSelectInContext fileSelectInContext = new FileSelectInContext(selectInContext.getProject(),
                LocalFileSystem.getInstance().findFileByIoFile(new File(path)));
        projectPaneSelectInTarget.selectIn(fileSelectInContext, true);
    }
}
