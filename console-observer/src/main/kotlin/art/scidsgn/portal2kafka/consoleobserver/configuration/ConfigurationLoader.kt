package art.scidsgn.portal2kafka.consoleobserver.configuration

import com.charleskorn.kaml.Yaml

class ConfigurationLoader {
    companion object {
        fun loadFrom(path: String): Configuration {
            val loader = ConfigurationLoader()

            return loader.deserializeConfiguration(
                loader.loadResourceContents(path)
            )
        }
    }

    fun loadResourceContents(path: String): String {
        val resource = ConfigurationLoader::class.java.getResource(path)

        return resource.readText()
    }

    fun deserializeConfiguration(data: String): Configuration {
        return Yaml.default.decodeFromString(Configuration.serializer(), data)
    }
}