import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

object Utils {

    val inCI: Boolean
        get() = System.getenv("CI") == true.toString()

    val onLinux: Boolean
        get() = os().contains("linux", ignoreCase = true)

    val onMac: Boolean
        get() = os().contains("mac", ignoreCase = true)

    val onWindows: Boolean
        get() = os().contains("windows", ignoreCase = true)

    private fun os(): String = System.getProperty("os.name")

    fun Provider<PluginDependency>.pluginId(): String = get().pluginId
}
