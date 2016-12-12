package my;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created by yinghao_niu on 2016/12/12 for freecmdSelectIn.
 */

@State(name = "MySettings",
        storages = {@com.intellij.openapi.components.Storage(
                file = StoragePathMacros.APP_CONFIG + "/my_settings.xml")})
public class Storage implements PersistentStateComponent<Storage> {
    @Nullable
    @Override
    public Storage getState() {
        return this;
    }

    @Override
    public void loadState(Storage state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
