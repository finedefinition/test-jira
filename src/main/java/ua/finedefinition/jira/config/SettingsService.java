package ua.finedefinition.jira.config;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import lombok.extern.slf4j.Slf4j;
import ua.finedefinition.jira.exception.ConfigSavingException;
import ua.finedefinition.jira.model.PluginConfig;
import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Field;

@Slf4j
@Named("settingsManager")
public class SettingsService {

    private final PluginSettings pluginSettings;

    @Inject
    public SettingsService(@ComponentImport PluginSettingsFactory pluginSettingsFactory) {
        pluginSettings = pluginSettingsFactory.createGlobalSettings();
    }

    public void updateConfig(PluginConfig pluginConfig) throws ConfigSavingException {
        try {
            Field[] declaredFields = PluginConfig.class.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object value = declaredField.get(pluginConfig);
                String fieldName = declaredField.getName();
                setProperty(fieldName, value);
            }
        } catch (IllegalAccessException e) {
            log.error("Error while saving plugin config", e);
            throw new ConfigSavingException("Error while saving plugin config");
        }
    }

    public PluginConfig getConfig() throws ConfigSavingException {
        try {
            PluginConfig pluginConfig = new PluginConfig();
            Field[] declaredFields = PluginConfig.class.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                String fieldName = declaredField.getName();
                Object property = getProperty(fieldName);
                declaredField.set(pluginConfig, property);
            }
            return pluginConfig;
        } catch (IllegalAccessException e) {
            log.error("Error while saving plugin config", e);
            throw new ConfigSavingException("Error while saving plugin config");
        }

    }

    private void setProperty(String propertyName, Object propertyValue) {
        pluginSettings.put(propertyName, propertyValue);
    }

    private Object getProperty(String propertyName) {
        return pluginSettings.get(propertyName);
    }
}
