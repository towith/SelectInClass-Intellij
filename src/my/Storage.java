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

    String fmPath;
    String prefixPath;

    public String getPrefixPath() {
        return prefixPath;
    }

    public void setPrefixPath(String prefixPath) {
        this.prefixPath = prefixPath;
    }

    public String getFmPath() {
        return fmPath;
    }

    public void setFmPath(String fmPath) {
        this.fmPath = fmPath;
    }
}
