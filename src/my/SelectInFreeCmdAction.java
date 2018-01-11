package my;

import com.intellij.ide.ClipboardSynchronizer;
import com.intellij.ide.actions.CopyReferenceAction;
import com.intellij.ide.ui.EditorOptionsTopHitProvider;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.util.regex.Pattern;

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
        CopyReferenceAction a = (CopyReferenceAction) ActionManager.getInstance().getAction("CopyReference");
        a.actionPerformed(anActionEvent);
//        StringSelection selection = new StringSelection(theString);
//        clipboard.setContents(selection, selection);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String clipboardData = null;
        Transferable t = clipboard.getContents(null);
        try {
            if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                Object o = t.getTransferData(DataFlavor.stringFlavor);
                clipboardData = (String) t.getTransferData(DataFlavor.stringFlavor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

/*      ActionManager.getInstance().tryToExecute(a,ActionCommand.getInputEvent("CopyRefrence"),
                null,"",true);
                CommandProcessor.getInstance().executeCommand(selectInContext.getProject(), new Runnable() {
            @Override
            public void run() {
            }
        },"",null);*/
        int j = countDot(clipboardData);

        if (j > 1) {
            clipboardData = clipboardData.replaceAll("\\.", "/");
        }
        clipboardData = clipboardData.replaceAll("\\.java", "\\.class");
        String path2 = MyFileManager.prefixPath + clipboardData;
        ProcessBuilder freecmd = new ProcessBuilder(MyFileManager.toolPath, path, path2);
        try {
            freecmd.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public int countDot(String clipboardData) {
        int i = 0;
        int j = 0;
        int k = 0;
        while ((k = clipboardData.indexOf(".", i)) != -1) {
            j++;
            i=k+1;
        }
        return j;
    }
}
